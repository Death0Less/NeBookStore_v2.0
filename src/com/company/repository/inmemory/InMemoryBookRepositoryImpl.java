package com.company.repository.inmemory;

import com.company.entity.Author;
import com.company.entity.Book;
import com.company.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBookRepositoryImpl implements BookRepository {

    private static int incId;
    private static InMemoryBookRepositoryImpl instance;
    private List<Book> bookList = new ArrayList<>();

    public InMemoryBookRepositoryImpl() {
    }

    public static InMemoryBookRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new InMemoryBookRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void addBook(Book book) {
        book.setId(incId++);
        bookList.add(book);
    }

    @Override
    public Book findById(int id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                bookList.remove(book);
                return;
            }
        }
    }

    @Override
    public void deleteByTitle(String title) {
        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                bookList.remove(book);
                return;
            }
        }
    }

    @Override
    public List<Book> findByAll() {
        return bookList;
    }

    @Override
    public List<Book> findByAuthorName(Author author) {
        List<Book> kniggaList = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getAuthor().getNickname().equals(author.getNickname())) {
                kniggaList.add(book);
                return kniggaList;
            }
        }
        return null;
    }
}
