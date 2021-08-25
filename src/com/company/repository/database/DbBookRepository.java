package com.company.repository.database;

import com.company.entity.Author;
import com.company.entity.Book;
import com.company.repository.BookRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbBookRepository extends DbAbstractRepository implements BookRepository {

    private static final String INSERT_BOOK = "insert into books values (default, ?, ?, ?, ?)";

    private static final String FIND_BOOK_BY_ID = "select * from books where id = ?";

    private static final String FIND_BOOK_BY_TITLE = "select * from books where title = ?";

    private static final String DELETE_BOOK_BY_ID = "delete from books where id = ?";

    private static final String DELETE_BOOK_BY_TITLE = "delete from books where title = ?";

    private static final String FIND_ALL = "select * from books";

    private static final String FIND_BY_AUTHOR_NAME = "select * from where authorNickName = ?";

    @Override
    public void addBook(Book book) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setObject(3, book.getAuthor().getId());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public Book findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BOOK_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idFromDb = resultSet.getInt(1);
            String titleFromDb = resultSet.getString(2);
            String descriptionFromDb = resultSet.getString(3);
            Author authorFromDb = (Author) resultSet.getObject(4);
            double priceFromDb = resultSet.getDouble(5);
            Book bookFromDb = new Book(idFromDb, titleFromDb, descriptionFromDb, authorFromDb, priceFromDb);
            preparedStatement.close();
            return bookFromDb;
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    @Override
    public Book findByTitle(String title) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BOOK_BY_TITLE);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idFromDb = resultSet.getInt(1);
            String titleFromDb = resultSet.getString(2);
            String descriptionFromDb = resultSet.getString(3);
            Author authorFromDb = (Author) resultSet.getObject(4);
            double priceFromDb = resultSet.getDouble(5);
            Book bookFromDb = new Book(idFromDb, titleFromDb, descriptionFromDb, authorFromDb, priceFromDb);
            preparedStatement.close();
            return bookFromDb;
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }

    }

    @Override
    public void deleteByTitle(String title) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK_BY_TITLE);
            preparedStatement.setString(1, title);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public List<Book> findByAll() {
        List<Book> bookListFromDb = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idFromDb = resultSet.getInt(1);
                String titleFromDb = resultSet.getString(2);
                String descriptionFromDb = resultSet.getString(3);
                Author authorFromDb = (Author) resultSet.getObject(4);
                double priceFromDb = resultSet.getDouble(5);
                Book bookFromDb = new Book(idFromDb, titleFromDb, descriptionFromDb, authorFromDb, priceFromDb);
                bookListFromDb.add(bookFromDb);
            }
            preparedStatement.close();
            return bookListFromDb;
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    @Override
    public List<Book> findByAuthorName(Author author) {
        List<Book> bookListFromDb = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_AUTHOR_NAME);
            preparedStatement.setString(1, author.getNickname());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idFromDb = resultSet.getInt(1);
                String titleFromDb = resultSet.getString(2);
                String descriptionFromDb = resultSet.getString(3);
                Author authorFromDb = (Author) resultSet.getObject(4);
                double priceFromDb = resultSet.getDouble(5);
                Book bookFromDb = new Book(idFromDb, titleFromDb, descriptionFromDb, authorFromDb, priceFromDb);
                bookListFromDb.add(bookFromDb);
            }
            preparedStatement.close();
            return bookListFromDb;
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }
}
