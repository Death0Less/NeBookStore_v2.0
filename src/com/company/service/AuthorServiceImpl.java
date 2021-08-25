package com.company.service;

import com.company.entity.Author;
import com.company.repository.AuthorRepository;
import com.company.repository.database.DbAuthorRepository;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository = new DbAuthorRepository();

    @Override
    public void addAuthor(Author author) {
        authorRepository.addAuthor(author);
    }

    @Override
    public void deleteAuthor(int id) {
        authorRepository.deleteAuthor(id);
    }

    @Override
    public Author findByName(String author) {
        return authorRepository.findByName(author);
    }

    @Override
    public Author findById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
