package com.company.repository.database;

import com.company.entity.Role;
import com.company.entity.User;
import com.company.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUserRepository extends DbAbstractRepository implements UserRepository {

    private static final String INSERT_USER = "insert into users values (default, ?, ?, ?, ?, ?)";

    private static final String SELECT_ROLE = "select * from roles where role = ?";

    private static final String DELETE_USER = "delete from users where id = ?";

    private static final String DELETE_USER_BY_EMAIL = "delete from users where email = ?";

    private static final String FIND_ALL = "select * from users";

    private static final String UPDATE_EMAIL = "update users set email = ? where id = ?";

    private static final String UPDATE_PASSWORD = "update users set password = ? where id = ?";

    private static final String UPDATE_LASTNAME = "update users set lastName = ? where id = ?";

    private static final String UPDATE_FIRSTNAME = "update users set firstName = ? where id = ?";

    private static final String FIND_USER_BY_ID = "select * from users where id = ?";

    private static final String FIND_USER_BY_EMAIL = "select * from users where id = ?";


    @Override
    public void addUser(User user) {
        try {

            String roleFromUser = user.getRole().name();
            PreparedStatement preparedStatementForRole = connection.prepareStatement(SELECT_ROLE);
            preparedStatementForRole.setString(1, roleFromUser);
            ResultSet resultSet = preparedStatementForRole.executeQuery();
            resultSet.next();
            int idOfRole = resultSet.getInt(1);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, idOfRole);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public void delById(int id) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }

    }

    @Override
    public void delByEmail(String email) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userListFromList = new ArrayList<>();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idFromDb = resultSet.getInt(1);
                String lastNameFromDb = resultSet.getString(2);
                String firstNameFromDb = resultSet.getString(3);
                String emailFromDb = resultSet.getString(4);
                String passwordFromDb = resultSet.getString(5);
                String roleFromDb = resultSet.getString(6);

                User userFromDb = new User(idFromDb, lastNameFromDb, firstNameFromDb, emailFromDb,
                        passwordFromDb, Role.valueOf(roleFromDb));
                userListFromList.add(userFromDb);
            }
            preparedStatement.close();
            return userListFromList;

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    @Override
    public void updateEmail(int id, String email) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMAIL);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public void updatePassword(int id, String password) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public void updateFirstName(int id, String firstName) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FIRSTNAME);
            preparedStatement.setString(1, firstName);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public void updateLastName(int id, String lastName) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LASTNAME);
            preparedStatement.setString(1, lastName);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public User findById(int id) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int idFromDb = resultSet.getInt(1);
            String lastNameFromDb = resultSet.getString(2);
            String firstNameFromDb = resultSet.getString(3);
            String emailFromDb = resultSet.getString(4);
            String passwordFromDb = resultSet.getString(5);
            String roleFromDb = resultSet.getString(6);

            User userFromDb = new User(idFromDb, lastNameFromDb, firstNameFromDb, emailFromDb,
                    passwordFromDb, Role.valueOf(roleFromDb));

            preparedStatement.close();
            return userFromDb;

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int idFromDb = resultSet.getInt(1);
            String lastNameFromDb = resultSet.getString(2);
            String firstNameFromDb = resultSet.getString(3);
            String emailFromDb = resultSet.getString(4);
            String passwordFromDb = resultSet.getString(5);
            String roleFromDb = resultSet.getString(6);

            User userFromDb = new User(idFromDb, lastNameFromDb, firstNameFromDb, emailFromDb,
                    passwordFromDb, Role.valueOf(roleFromDb));

            preparedStatement.close();
            return userFromDb;


        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }
}
