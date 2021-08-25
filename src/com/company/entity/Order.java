package com.company.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Order implements Serializable {
    private int id;
    private Book[] books;
    private User user;
    private Address address;
    private boolean isDelivery;
    private Store store;


    public Order(Book[] books, User user, Address address, boolean isDelivery) {
        this.books = books;
        this.user = user;
        this.address = address;
        this.isDelivery = isDelivery;
    }

    public Order(Book[] books, User user, boolean isDelivery, Store store) {
        this.books = books;
        this.user = user;
        this.isDelivery = isDelivery;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", books=" + Arrays.toString(books) +
                ", user=" + user +
                ", address=" + address +
                ", isDelivery=" + isDelivery +
                ", store=" + store +
                '}';
    }
}
