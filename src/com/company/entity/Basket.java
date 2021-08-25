package com.company.entity;

import java.io.Serializable;
import java.util.Arrays;

public class Basket implements Serializable {
    private int id;
    private Book[] books;

    public Basket(Book[] books) {
        this.books = books;
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

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", books=" + Arrays.toString(books) +
                '}';
    }
}
