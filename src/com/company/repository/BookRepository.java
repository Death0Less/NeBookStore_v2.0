package com.company.repository;

import com.company.entity.Author;
import com.company.entity.Book;

import java.util.List;

public interface BookRepository {
    void addBook(Book book);

    Book findById(int id);

    Book findByTitle(String title);

    void deleteById(int id);

    void deleteByTitle(String title);

    List<Book> findByAll();

    List<Book> findByAuthorName(Author author);


}
