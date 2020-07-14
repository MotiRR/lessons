package org.vtb.lesson12.util;

import org.vtb.lesson12.annotation.DbColumn;
import org.vtb.lesson12.annotation.DbId;
import org.vtb.lesson12.annotation.DbTable;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reflection<T> {

    public void createQuerySave(T object, Connector con) throws SQLException, IllegalAccessException, NoSuchFieldException {
        Class c = object.getClass();
        if (!c.isAnnotationPresent(DbTable.class)) {
            throw new RuntimeException("Unable to save objects for class " + c.getSimpleName());
        }

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO ");
        // 'INSERT INTO '
        queryBuilder.append(((DbTable) c.getAnnotation(DbTable.class)).name());
        // 'INSERT INTO name_class'
        queryBuilder.append(" (");
        // 'INSERT INTO name_class ('
        boolean isDbId = false;
        Field id = null;
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.isAnnotationPresent(DbId.class)) {
                id = f;
                isDbId = true;
            }
            if (f.isAnnotationPresent(DbColumn.class)) {
                queryBuilder
                        .append(f.getName())
                        .append(", ");
            }
        }
        List<T> startRows = null;
        if (!isDbId)
            throw new RuntimeException("The table should contain a field with annotation DbId");
        else {
            startRows = find(id.getName(), c, con);
        }
        // 'INSERT INTO name_class (column1, column2, '
        queryBuilder.setLength(queryBuilder.length() - 2);
        // 'INSERT INTO name_class (column1, column2'
        queryBuilder.append(") VALUES (");
        // 'INSERT INTO name_class (column1, column2) VALUES ('
        for (Field f : fields) {
            if (f.isAnnotationPresent(DbColumn.class)) {
                queryBuilder.append("?, ");
            }
        }
        // 'INSERT INTO name_class (column1, column2) VALUES (?, ?, '
        queryBuilder.setLength(queryBuilder.length() - 2);
        // 'INSERT INTO name_class (column1, column2) VALUES (?, ?'
        queryBuilder.append(");");
        // 'INSERT INTO name_class (column1, column2) VALUES (?, ?);'
        PreparedStatement ps = con.getConnection().prepareStatement(queryBuilder.toString());
        int index = 1;
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.isAnnotationPresent(DbColumn.class)) {
                ps.setObject(index, f.get(object));
                index++;
            }
        }
        if (ps.executeUpdate() > 0) {
            boolean isNewId = false;
            Object num = null;
            List<T> endRows = find(id.getName(), c, con);
            for (T end : endRows) {
                Object idEnd = id.get(end);
                for (T st : startRows) {
                    Object idStart = id.get(st);
                    if (idEnd == idStart) {
                        isNewId = true;
                        break;
                    }
                }
                if (!isNewId) {
                    num = idEnd;
                    break;
                }
                isNewId = false;
            }
            System.out.println(String.format("Object of class %s saved with id = %s", c.getSimpleName(), num));
        }
    }

    private List<T> find(String id, Class<T> c, Connector con) throws SQLException, NoSuchFieldException, IllegalAccessException {
        StringBuilder queryBuilderSelect = new StringBuilder();
        queryBuilderSelect.append("SELECT ");
        queryBuilderSelect.append(id);
        queryBuilderSelect.append(" From ");
        queryBuilderSelect.append(((DbTable) c.getAnnotation(DbTable.class)).name());
        PreparedStatement ps = con.getConnection().prepareStatement(queryBuilderSelect.toString());
        return processRows(ps, c);
    }

    public void createQueryDelete(Long id, Class<T> c, Connector con) throws SQLException {
        if (!c.isAnnotationPresent(DbTable.class)) {
            throw new RuntimeException("Unable to save objects for class " + c.getSimpleName());
        }

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("DELETE FROM ");
        // 'DELETE FROM '
        queryBuilder.append(((DbTable) c.getAnnotation(DbTable.class)).name());
        // 'DELETE FROM name_class'
        queryBuilder.append(" where id = ?");
        PreparedStatement ps = con.getConnection().prepareStatement(queryBuilder.toString());
        ps.setLong(1, id);
        if (ps.executeUpdate() == 1)
            System.out.println(String.format("Record with id = %d was deleted for object of class %s saved", id, c.getSimpleName()));
    }

    public void createQueryDeleteAll(Class<T> c, Connector con) throws SQLException {
        if (!c.isAnnotationPresent(DbTable.class)) {
            throw new RuntimeException("Unable to save objects for class " + c.getSimpleName());
        }

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("DELETE FROM ");
        // 'DELETE FROM '
        queryBuilder.append(((DbTable) c.getAnnotation(DbTable.class)).name());
        // 'DELETE FROM name_class'
        Statement stmt = con.getStatement();
        if (stmt.executeUpdate(queryBuilder.toString()) > 0)
            System.out.println("Deleted all records for object of class");
    }

    public T createQueryFindById(Long id, Class<T> c, Connector con) throws SQLException, NoSuchFieldException, IllegalAccessException {
        if (!c.isAnnotationPresent(DbTable.class)) {
            throw new RuntimeException("Unable to save objects for class " + c.getSimpleName());
        }


        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM ");
        // 'SELECT FROM '
        queryBuilder.append(((DbTable) c.getAnnotation(DbTable.class)).name());
        // 'SELECT FROM name_class'
        queryBuilder.append(" where id = ?");
        PreparedStatement ps = con.getConnection().prepareStatement(queryBuilder.toString());
        ps.setLong(1, id);
        List<T> oneRow = processRows(ps, c);
        if (!oneRow.isEmpty())
            return oneRow.get(0);
        return null;
    }

    private List<T> processRows(PreparedStatement ps, Class<T> c) throws SQLException, NoSuchFieldException, IllegalAccessException {

        List<T> list = new ArrayList<>();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                T obj = null;
                try {
                    obj = c.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String name = rsmd.getColumnName(i).toLowerCase();
                    Field field = c.getDeclaredField(name);
                    field.setAccessible(true);
                    defineType(obj, field, field.getType().getSimpleName(), rs.getString(name).toLowerCase());
                }

                list.add(obj);
            }

        }
        return list;
    }

    private void defineType(T obj, Field field, String type, String par) throws IllegalAccessException {
        if (type.equalsIgnoreCase("Long"))
            field.set(obj, Long.parseLong(par));
        if (type.equalsIgnoreCase("Integer") || type.equalsIgnoreCase("int"))
            field.set(obj, Integer.parseInt(par));
        if (type.equalsIgnoreCase("String"))
            field.set(obj, par);
    }

    public List<T> createQueryFindAll(Class<T> c, Connector con) throws SQLException, NoSuchFieldException, IllegalAccessException {
        if (!c.isAnnotationPresent(DbTable.class)) {
            throw new RuntimeException("Unable to save objects for class " + c.getSimpleName());
        }

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT * FROM ");
        // 'SELECT FROM '
        queryBuilder.append(((DbTable) c.getAnnotation(DbTable.class)).name());
        // 'SELECT FROM name_class'
        PreparedStatement ps = con.getConnection().prepareStatement(queryBuilder.toString());
        List<T> rows = processRows(ps, c);
        if (!rows.isEmpty())
            return rows;
        return null;
    }

}
