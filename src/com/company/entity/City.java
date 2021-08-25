package com.company.entity;

import java.io.Serializable;

public class City implements Serializable {
    private int id;
    private String city;

    public City(int id, String city) {
        this.id = id;
        this.city = city;
    }

    public City(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", city='" + city + '\'' +
                '}';
    }
}
