package com.company.repository.file;

import com.company.entity.Author;
import com.company.entity.Book;
import com.company.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class FileBookRepository implements BookRepository {

    private DataBase dataBase = new FileDataBaseImpl();
    private List<Book> temp;

    {
        temp = dataBase.read(Book.class);
    }

    @Override
    public void addBook(Book book) {
        int lastBookId = dataBase.getId(Book.class);
        book.setId(++lastBookId);
        dataBase.setId(Book.class, lastBookId);
        temp.add(book);
        dataBase.write(temp, Book.class);
    }

    @Override
    public Book findById(int id) {
        for (Book book : temp) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        for (Book book : temp) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (Book book : temp) {
            if (book.getId() == id) {
                temp.remove(book);
                break;
            }
        }
    }

    @Override
    public void deleteByTitle(String title) {
        for (Book book : temp) {
            if (book.getTitle().equals(title)) {
                temp.remove(book);
            }
        }
    }

    @Override
    public List<Book> findByAll() {
        return temp;
    }

    @Override
    public List<Book> findByAuthorName(Author author) {
        List<Book> bookList = new ArrayList<>();
        for (Book book : temp) {
            if (book.getAuthor().getNickname().equals(author.getNickname())) {
                bookList.add(book);
                return bookList;
            }
        }
        return null;
    }
}
