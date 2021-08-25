package com.company.repository.database;

import com.company.entity.Author;
import com.company.repository.AuthorRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbAuthorRepository extends DbAbstractRepository implements AuthorRepository {

    private static final String INSERT_AUTHOR = "insert into authors values (default, ?)";

    private static final String DELETE_AUTHOR = "delete from authors where id = ?";

    private static final String FIND_BY_NAME = "select * from authors where nickname = ?";

    private static final String FIND_BY_ID = "select * from authors where id = ?";

    private static final String FIND_ALL = "select * from authors";

    @Override
    public void addAuthor(Author author) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AUTHOR);
            preparedStatement.setString(1, author.getNickname());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public void deleteAuthor(int id) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_AUTHOR);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }

    }

    @Override
    public Author findByName(String author) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, author);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int idFromDb = resultSet.getInt(1);
            String nickNameFromDb = resultSet.getString(2);
            Author authorFromDb = new Author(idFromDb, nickNameFromDb);

            preparedStatement.close();
            return authorFromDb;

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    @Override
    public Author findById(int id) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int idFromDb = resultSet.getInt(1);
            String nickNameFromDB = resultSet.getString(2);
            Author authorFromDb = new Author(idFromDb, nickNameFromDB);

            preparedStatement.close();

            return authorFromDb;

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    @Override
    public List<Author> findAll() {
        List<Author> authorListFromDb = new ArrayList<>();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idFromDb = resultSet.getInt(1);
                String nickNameFromDb = resultSet.getString(2);
                Author authorFromDb = new Author(idFromDb, nickNameFromDb);
                authorListFromDb.add(authorFromDb);
            }
            preparedStatement.close();

            return authorListFromDb;

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }
}
