package com.company.entity;

import java.io.Serializable;

public class Store implements Serializable {
    private int id;
    private String storeName;
    private Address address;
    private City city;

    public Store(int id, String storeName, Address address, City city) {
        this.id = id;
        this.storeName = storeName;
        this.address = address;
        this.city = city;
    }

    public Store(String storeName, Address address, City city) {
        this.storeName = storeName;
        this.address = address;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", address=" + address +
                ", city=" + city +
                '}';
    }
}
