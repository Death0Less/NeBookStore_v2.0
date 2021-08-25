package com.company.service;

import com.company.entity.Author;

import java.util.List;

public interface AuthorService {
    void addAuthor(Author author);

    void deleteAuthor(int id);

    Author findByName(String author);

    Author findById(int id);

    List<Author> findAll();
}
