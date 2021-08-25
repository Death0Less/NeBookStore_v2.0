package com.company.repository;

import com.company.entity.Author;

import java.util.List;

public interface AuthorRepository {
    void addAuthor(Author author);

    void deleteAuthor(int id);

    Author findByName(String author);

    Author findById(int id);

    List<Author> findAll();

}
