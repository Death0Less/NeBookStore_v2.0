package com.company.action;


import com.company.entity.Author;
import com.company.service.AuthorService;
import com.company.service.AuthorServiceImpl;
import com.company.util.Reader;
import com.company.util.ReaderImpl;
import com.company.util.Writer;
import com.company.util.WriterImpl;
import com.company.valid.AuthorValidator;

import java.util.List;

public class AuthorActionImpl implements AuthorAction {

    private Reader reader = new ReaderImpl();
    private Writer writer = new WriterImpl();
    private AuthorService authorService = new AuthorServiceImpl();
    private AuthorValidator authorValidator = new AuthorValidator();

    public AuthorActionImpl(Reader reader, Writer writer, AuthorService authorService, AuthorValidator authorValidator) {
        this.reader = reader;
        this.writer = writer;
        this.authorService = authorService;
        this.authorValidator = authorValidator;
    }

    public AuthorActionImpl() {
    }

    @Override
    public void addAuthor() {
        writer.writerStr("Введите автора:");
        String nickName = reader.readStr();
        if (!checkNickName(nickName)) {
            writer.writerStr("Вы ввели не те данные.");
            return;
        }
        if (authorValidator.checkAuthor(nickName)) {
            authorService.addAuthor(new Author(nickName));
        } else {
            writer.writerStr("Вы ввел не те данные.");
        }

    }

    @Override
    public void deleteAuthor() {
        writer.writerStr("Введите id, чтобы удалить автора:");
        int id = reader.readInt();
        authorService.deleteAuthor(id);
    }

    @Override
    public void findByName() {
        writer.writerStr("Введите автора, чтобы найти его:");
        String nickName = reader.readStr();
        Author author = authorService.findByName(nickName);
        writer.writerStr(author.getId() + " - " + " " + author.getNickname());
    }

    @Override
    public void findById() {
        writer.writerStr("Введите id, чтобы найти автра:");
        int id = reader.readInt();
        Author author = authorService.findById(id);
        writer.writerStr(author.getId() + " - " + " " + author.getNickname());
    }

    @Override
    public void findAll() {
        List<Author> authorsList = authorService.findAll();
        writer.writerStr("Список авторов:");
        for (Author author : authorsList) {
            writer.writerStr(author.getId() + " - " + " " + author.getNickname());
        }

    }

    private boolean checkNickName(String nickName) {
        return authorService.findByName(nickName) == null;
    }
}
