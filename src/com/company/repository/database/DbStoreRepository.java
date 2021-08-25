package com.company.repository.database;

import com.company.entity.Address;
import com.company.entity.City;
import com.company.entity.Store;
import com.company.repository.StoreRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbStoreRepository extends DbAbstractRepository implements StoreRepository {

    private static final String INSERT_STORE = "insert into stores values (default, ?, ?, ?)";

    private static final String DELETE_STORE_BY_ID = "delete from stores where id = ?";

    private static final String FIND_ALL = "select * from stores";

    private static final String FIND_STORE_BY_ID = "select * from stores where id = ?";

    private static final String FIND_STORE_BY_STORE_NAME = "select * from stores where storeName = ?";


    @Override
    public void addStore(Store store) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STORE);
            preparedStatement.setString(1, store.getStoreName());
            preparedStatement.setObject(2, store.getAddress().getId());
            preparedStatement.setObject(3, store.getCity().getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public void deleteStore(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STORE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }

    @Override
    public List<Store> findAll() {
        List<Store> storeListFromDb = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idFromDb = resultSet.getInt(1);
                String storeNameFromDb = resultSet.getString(2);
                Address addressFromDb = (Address) resultSet.getObject(3);
                City cityFromDb = (City) resultSet.getObject(4);
                Store storeFromDb = new Store(idFromDb, storeNameFromDb, addressFromDb, cityFromDb);
                storeListFromDb.add(storeFromDb);
            }
            preparedStatement.close();
            return storeListFromDb;
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    @Override
    public Store findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_STORE_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }

    @Override
    public Store findByStoreName(String storeName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_STORE_BY_STORE_NAME);
            preparedStatement.setString(1, storeName);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
        return null;
    }
}
