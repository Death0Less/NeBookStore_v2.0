package com.company.repository.database;

import com.company.entity.*;
import com.company.repository.OrderRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DbOrderRepository extends DbAbstractRepository implements OrderRepository {

    private static final String INSERT_INTO_ORDERS = "insert into orders values (default, ?, ?, ?, ?)";

    public static final String INSERT_INTO_ORDER_BOOK = "insert into order_book values (?, ?)";

    public static final String DELETE_FROM_ORDERS = "delete from orders where id = ?";

    public static final String DELETE_FROM_ORDER_BOOK = "delete from order_book where order_id = ?";

    public static final String FIND_ORDERS_BY_ID = "select * from orders o join stores s on o.store_id = s.id " +
            "join users u on o.user_id = u.id join roles r on u.role_id = r.id join addresses a on a.id = s.address_id" +
            "join cities c on c.id = s.city_id";

    public static final String FIND_ORDER_BOOK_BY_ID = "";


    @Override
    public void addOrder(Order order) {
        try {
            connection.setAutoCommit(false); // группировка транзакций в одну общую

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDERS);
            if (order.isDelivery()) {
                preparedStatement.setString(2, order.getAddress().getAddress());
                preparedStatement.setInt(4, 0);
            } else {
                preparedStatement.setString(2, "");
                preparedStatement.setInt(4, order.getStore().getId());
            }
            preparedStatement.setInt(1, order.getUser().getId());
            preparedStatement.setBoolean(3, order.isDelivery());
            preparedStatement.execute();
            preparedStatement.close();

            for (Book book : order.getBooks()) {
                PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_INTO_ORDER_BOOK);
                preparedStatement1.setInt(1, order.getId());
                preparedStatement1.setInt(2, book.getId());
                preparedStatement1.execute();
                preparedStatement1.close();
            }

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException sqlException) {
            try {
                connection.rollback();
            } catch (SQLException sqlException1) {
                sqlException1.printStackTrace();
            }
            sqlException.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_ORDERS);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();

            PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_FROM_ORDER_BOOK);
            preparedStatement1.setInt(1, id);
            preparedStatement1.execute();
            preparedStatement1.close();

            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException sqlException1) {
                sqlException1.printStackTrace();
            }
        }

    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findAllByStore(Store store) {
        return null;
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return null;
    }

    private User resultUser(ResultSet resultSet) {
        try {

            int userId = resultSet.getInt(10);
            String lastName = resultSet.getString(11);
            String firstName = resultSet.getString(12);
            String email = resultSet.getString(13);
            String password = resultSet.getString(14);
            String role = resultSet.getString(17);

            return new User(userId, lastName, firstName, email, password, Role.valueOf(role));

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
