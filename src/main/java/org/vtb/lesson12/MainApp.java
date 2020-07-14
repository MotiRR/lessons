package org.vtb.lesson12;

import org.vtb.lesson12.entity.Client;
import org.vtb.lesson12.util.Connector;
import org.vtb.lesson12.util.ReflectionRepository;

import java.sql.SQLException;

public class MainApp {

    /*
    CREATE TABLE Client (
    id    INTEGER PRIMARY KEY AUTOINCREMENT
                  UNIQUE,
    Name  VARCHAR,
    email VARCHAR,
    phone INTEGER
    );
    */

    public static void main(String[] args) {

        Connector connector = new Connector();
        try {
            connector.connect();
        } catch (ClassNotFoundException | SQLException e) {
            connector.disconnect();
            e.printStackTrace();
        }

        Client client = new Client("name", "email", 89111);
        ReflectionRepository<Client> rr = new ReflectionRepository<>(Client.class, connector);
        rr.save(client);
//        rr.delete(2L);
//        rr.deleteAll();
//
//        Object o = rr.findById(11L);
//        System.out.println(o.toString());
//
//        List<Client> list = rr.findAll();
//        for(Client clie : list)
//            System.out.println(clie.toString());

        connector.disconnect();
    }

}
