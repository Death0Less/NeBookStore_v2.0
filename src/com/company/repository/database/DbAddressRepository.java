package com.company.repository.database;

import com.company.entity.Address;
import com.company.repository.AddressRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbAddressRepository extends DbAbstractRepository implements AddressRepository {

    private static final String INSERT_ADDRESS = "insert into addresses values (default, ?)";

    private static final String DELETE_ADDRESS = "delete from addresses where id = ?";

    private static final String FIND_BY_NAME = "select * from addresses where address = ?";

    private static final String FIND_BY_ID = "select * from addresses where id = ?";

    private static final String FIND_ALL = "select * from";

    @Override
    public void addAddress(Address address) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADDRESS);
            preparedStatement.setString(1, address.getAddress());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public void deleteAddress(int id) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADDRESS);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public Address findByName(String address) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            preparedStatement.setString(1, address);

            ResultSet resultSet = preparedStatement.executeQuery(); // Выборка строки(id, address)
            resultSet.next(); // Так сказала Аня : "Типо так надо всегда, я хз"

            int idFromDb = resultSet.getInt(1);
            String addressNameFromDb = resultSet.getString(2);
            Address addressFromDb = new Address(idFromDb, addressNameFromDb);

            preparedStatement.close();

            return addressFromDb;

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    //АртёмОЧКА
    //кам цу мир

    @Override
    public Address findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int idFromDb = resultSet.getInt(1);
            String addressNameFromDb = resultSet.getString(2);
            Address addressFromDb = new Address(idFromDb, addressNameFromDb);

            preparedStatement.close();

            return addressFromDb;

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    @Override
    public List<Address> findAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            List<Address> addressListFromDb = new ArrayList<>();

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idFromDb = resultSet.getInt(1);
                String addressNameFromDb = resultSet.getString(2);
                Address addressFromDb = new Address(idFromDb, addressNameFromDb);
                addressListFromDb.add(addressFromDb);
            }
            preparedStatement.close();

            return addressListFromDb;

        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }
}
