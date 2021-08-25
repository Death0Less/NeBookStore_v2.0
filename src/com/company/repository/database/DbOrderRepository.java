package com.company.repository.database;

import com.company.entity.Book;
import com.company.entity.Order;
import com.company.entity.Store;
import com.company.entity.User;
import com.company.repository.OrderRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DbOrderRepository extends DbAbstractRepository implements OrderRepository {

    private static final String INSERT_INTO_ORDERS = "insert into orders values (default, ?, ?, ?, ?)";

    public static final String INSERT_INTO_ORDER_BOOK = "insert into order_book values (?, ?)";


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
}
