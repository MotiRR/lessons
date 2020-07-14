package org.vtb.lesson12.entity;

import org.vtb.lesson12.annotation.DbColumn;
import org.vtb.lesson12.annotation.DbId;
import org.vtb.lesson12.annotation.DbTable;

@DbTable(name = "Client")
public class Client {
    @DbId
    private Long id;
    @DbColumn
    private String name;
    @DbColumn
    private String email;
    @DbColumn
    private int phone;

    public Client() {
    }

    public Client(String name, String email, int phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
