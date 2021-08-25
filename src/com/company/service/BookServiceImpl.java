package com.company.service;

import com.company.entity.Author;
import com.company.entity.Book;
import com.company.repository.BookRepository;
import com.company.repository.database.DbBookRepository;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository = new DbBookRepository();

    @Override
    public void addBook(Book book) {
        bookRepository.addBook(book);
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteByTitle(String title) {
        bookRepository.deleteByTitle(title);
    }

    @Override
    public List<Book> findByAll() {
        return bookRepository.findByAll();
    }

    @Override
    public List<Book> findByAuthorName(Author author) {
        return bookRepository.findByAuthorName(author);
    }
}
