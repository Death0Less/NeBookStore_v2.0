package com.company.service;

import com.company.entity.Author;
import com.company.entity.Book;

import java.util.List;

public interface BookService {

    void addBook(Book book);

    Book findById(int id);

    Book findByTitle(String title);

    void deleteById(int id);

    void deleteByTitle(String title);

    List<Book> findByAll();

    List<Book> findByAuthorName(Author author);
}
