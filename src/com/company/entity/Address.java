package com.company.entity;

import java.io.Serializable;

public class Address implements Serializable { //Маркерный интерфейс
    private int id;
    private String address;

    public Address(String address) {
        this.address = address;
    }

    public Address(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
