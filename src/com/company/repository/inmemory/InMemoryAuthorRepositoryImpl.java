package com.company.repository.inmemory;

import com.company.entity.Author;
import com.company.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAuthorRepositoryImpl implements AuthorRepository {
    private static int incId;
    private final List<Author> authorList = new ArrayList<>();
    private static InMemoryAuthorRepositoryImpl instance;

    private InMemoryAuthorRepositoryImpl() {
    }

    public static InMemoryAuthorRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new InMemoryAuthorRepositoryImpl();
        }
        return instance;
    }


    @Override
    public void addAuthor(Author author) {
        author.setId(incId++);
        authorList.add(author);
    }

    @Override
    public void deleteAuthor(int id) {
        for (Author author : authorList) {
            if (author.getId() == id) {
                authorList.remove(author);
            }
            break;
        }
    }

    @Override
    public Author findByName(String author) {
        for (Author author1 : authorList) {
            if (author1.getNickname().equals(author)) {
                return author1;
            }
        }
        return null;
    }

    @Override
    public Author findById(int id) {
        for (Author author : authorList) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }

    @Override
    public List<Author> findAll() {
        return authorList;
    }
}
