package com.company.repository.file;

import com.company.entity.Author;
import com.company.repository.AuthorRepository;

import java.util.List;

public class FileAuthorRepository implements AuthorRepository {

    private DataBase dataBase = new FileDataBaseImpl();
    private List<Author> temp;

    {
        temp = dataBase.read(Author.class);
    }


    @Override
    public void addAuthor(Author author) {
        int lastAuthorId = dataBase.getId(Author.class);
        author.setId(++lastAuthorId);
        dataBase.setId(Author.class, lastAuthorId);
        temp.add(author);
        dataBase.write(temp, Author.class);
    }

    @Override
    public void deleteAuthor(int id) {
        for (Author author : temp) {
            if (author.getId() == id) {
                temp.remove(author);
                break;
            }
        }
    }

    @Override
    public Author findByName(String author) {
        for (Author author1 : temp) {
            if (author1.getNickname().equals(author)) {
                return author1;
            }
        }
        return null;
    }

    @Override
    public Author findById(int id) {
        for (Author author : temp) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }

    @Override
    public List<Author> findAll() {
        return temp;
    }
}
