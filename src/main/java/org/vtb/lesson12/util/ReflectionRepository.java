package org.vtb.lesson12.util;

import java.sql.SQLException;
import java.util.List;

public class ReflectionRepository<T> {


    private static Connector connector;
    private Class<T> myClass;
    private Reflection<T> reflection = new Reflection();


    public ReflectionRepository(Class<T> myClass, Connector con) {
        this.myClass = myClass;
        connector = con;
    }

    public void save(T object) {
        try {
            reflection.createQuerySave(object, connector);
        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(Long id) {
        try {
            reflection.createQueryDelete(id, myClass, connector);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteAll() {
        try {
            reflection.createQueryDeleteAll(myClass, connector);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public T findById(Long id) {
        try {
            return reflection.createQueryFindById(id, myClass, connector);
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> findAll() {
        try {
            return reflection.createQueryFindAll(myClass, connector);
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
