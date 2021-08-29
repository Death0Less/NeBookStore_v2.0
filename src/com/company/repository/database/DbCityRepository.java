package com.company.repository.database;

import com.company.entity.City;
import com.company.repository.CityRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbCityRepository extends DbAbstractRepository implements CityRepository {

    private static final String INSERT_CITY = "insert into cities values (default, ?)";

    private static final String DELETE_CITY = "delete from cities where id = ?";

    private static final String FIND_BY_ID = "select * from cities where id = ?";

    private static final String FIND_BY_NAME = "select * from cities where city = ?";

    private static final String FIND_ALL = "select * from";


    @Override
    public void addCity(City city) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CITY);
            preparedStatement.setString(1, city.getCity());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public void deleteCity(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CITY);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }

    }

    @Override
    public City findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idFromDb = resultSet.getInt(1);
            String cityNameFromDb = resultSet.getString(2);
            City cityFromDb = new City(idFromDb, cityNameFromDb);
            preparedStatement.close();
            return cityFromDb;
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    @Override
    public City findByName(String city) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int idFromDb = resultSet.getInt(1);
            String cityNameFromDb = resultSet.getString(2);
            City cityFromDb = new City(idFromDb, cityNameFromDb);
            preparedStatement.close();
            return cityFromDb;
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    @Override
    public List<City> findAll() {
        List<City> cityListFromDb = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idFromDb = resultSet.getInt(1);
                String cityNameFromDb = resultSet.getString(2);
                City cityFromDb = new City(idFromDb, cityNameFromDb);
                cityListFromDb.add(cityFromDb);
                preparedStatement.close();
                return cityListFromDb;
            }
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }

        return null;
    }
}
