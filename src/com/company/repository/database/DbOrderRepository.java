package com.company.repository.database;

import com.company.entity.*;
import com.company.repository.OrderRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbOrderRepository extends DbAbstractRepository implements OrderRepository {

    private static final String INSERT_INTO_ORDERS = "insert into orders values (default, ?, ?, ?, ?)";

    public static final String INSERT_INTO_ORDER_BOOK = "insert into order_book values (?, ?)";

    public static final String DELETE_FROM_ORDERS = "delete from orders where id = ?";

    public static final String DELETE_FROM_ORDER_BOOK = "delete from order_book where order_id = ?";

    public static final String FIND_ORDERS_BY_ID = "select * from orders o join stores s on o.store_id = s.id " +
            "join users u on o.user_id = u.id join roles r on u.role_id = r.id join addresses a on a.id = s.address_id" +
            "join cities c on c.id = s.city_id where o.id = ?";

    public static final String SELECT_BOOKS_BY_ORDER_ID = "select * from books b join order_book o_k on b.id = book_id " +
            "join authors a on b.author_id = a.id where o_k.order_id = ?";

    public static final String SELECT_STORE = "select * from stores s join orders o on s.id = o.store_id join cities c on s.city_id = c.id " +
            "where s.id = ?";

    public static final String SELECT_ALL_FROM_ORDERS = "select * from orders o join stores s on o.store_id = s.id " +
            "join users u on o.user_id = u.id join roles r on u.role_id = r.id join addresses a on a.id = s.address_id" +
            "join cities c on c.id = s.city_id";

    public static final String SELECT_ALL_BY_STORE = "select * from orders o join stores s on o.store_id = s.id" +
            "join users u on o.user_id = u.id join roles r on u.role_id = r.id join addresses a on a.id = s.address_id" +
            "join cities c on c.id = s.city_id where s.id = ?";

    public static final String SELECT_ALL_BY_USER = "select * from orders o join stores s on o.store_id = s.id" +
            "join users u on o.user_id = u.id join roles r on u.role_id = r.id join addresses a on a.id = s.address_id" +
            "join cities c on c.id = s.city_id where u.id = ?";


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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDERS_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int orderId = resultSet.getInt(1);

            boolean isDeliveryFromDb = resultSet.getBoolean(4);

            User userFromDb = getResultUser(resultSet);

            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_BOOKS_BY_ORDER_ID);
            preparedStatement1.setInt(1, id);

            ResultSet resultSet1 = preparedStatement1.executeQuery();
            List<Book> bookList = getResultBook(resultSet1);
            Book[] books = bookList.toArray(new Book[0]);

            if (isDeliveryFromDb) {
                return getOrderWithDelivery(resultSet, orderId, isDeliveryFromDb, userFromDb, books);
            } else {
                return getOrderWithOutDelivery(resultSet, orderId, userFromDb, books, isDeliveryFromDb);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orderList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_ORDERS);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int orderId = resultSet.getInt(1);

            boolean isDeliveryFromDb = resultSet.getBoolean(4);

            User userFromDb = getResultUser(resultSet);

            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_BOOKS_BY_ORDER_ID);
            preparedStatement1.setInt(1, orderId);

            ResultSet resultSet1 = preparedStatement1.executeQuery();
            List<Book> bookList = getResultBook(resultSet1);
            Book[] books = bookList.toArray(new Book[0]);

            if (isDeliveryFromDb) {
                orderList.add(getOrderWithDelivery(resultSet, orderId, isDeliveryFromDb, userFromDb, books));
            } else {
                orderList.add(getOrderWithOutDelivery(resultSet, orderId, userFromDb, books, isDeliveryFromDb));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<Order> findAllByStore(Store store) {
        List<Order> orderList = new ArrayList<>();
        int storeId = store.getId();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_STORE);
            preparedStatement.setInt(1, storeId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int orderId = resultSet.getInt(1);

            boolean isDeliveryFromDb = resultSet.getBoolean(4);

            User userFromDb = getResultUser(resultSet);

            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_BOOKS_BY_ORDER_ID);
            preparedStatement1.setInt(1, orderId);

            ResultSet resultSet1 = preparedStatement1.executeQuery();
            List<Book> bookList = getResultBook(resultSet1);
            Book[] books = bookList.toArray(new Book[0]);

            orderList.add(getOrderWithOutDelivery(resultSet, orderId, userFromDb, books, isDeliveryFromDb));

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<Order> findAllByUser(User user) {
        List<Order> orderList = new ArrayList<>();
        int userId = user.getId();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BY_USER);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int orderId = resultSet.getInt(1);

            boolean isDeliveryFromDb = resultSet.getBoolean(4);

            User userFromDb = getResultUser(resultSet);

            PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_BOOKS_BY_ORDER_ID);
            preparedStatement1.setInt(1, orderId);

            ResultSet resultSet1 = preparedStatement1.executeQuery();
            List<Book> bookList = getResultBook(resultSet1);
            Book[] books = bookList.toArray(new Book[0]);

            if (isDeliveryFromDb) {
                orderList.add(getOrderWithDelivery(resultSet, orderId, isDeliveryFromDb, userFromDb, books));
            } else {
                orderList.add(getOrderWithOutDelivery(resultSet, orderId, userFromDb, books, isDeliveryFromDb));
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return orderList;
    }

    private User getResultUser(ResultSet resultSet) {
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

    private List<Book> getResultBook(ResultSet resultSet) {
        List<Book> bookList = new ArrayList<>();

        try {

            while (resultSet.next()) {
                int idFromBD = resultSet.getInt(1);
                String titleFromBd = resultSet.getString(2);
                String descriptionFromDb = resultSet.getString(3);
                double priceFromDb = resultSet.getDouble(5);
                int authorIdFromDb = resultSet.getInt(4);
                String nickNameFromDb = resultSet.getString(9);

                Author authorFromDb = new Author(authorIdFromDb, nickNameFromDb);
                Book bookFromDb = new Book(idFromBD, titleFromBd, descriptionFromDb, authorFromDb, priceFromDb);
                bookList.add(bookFromDb);
            }
            return bookList;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    private Order getOrderWithDelivery(ResultSet resultSet, int orderId, boolean isDelivery, User user, Book[] bookArray) {
        try {

            String addressFromDb = resultSet.getString(19);
            Address address = new Address(addressFromDb);

            Order order = new Order(bookArray, user, address, isDelivery);

            return order;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    private Order getOrderWithOutDelivery(ResultSet resultSet, int orderId, User user, Book[] bookArray, boolean isDelivery) {
        try {

            int storeIdFromDb = resultSet.getInt(5);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STORE);
            preparedStatement.setInt(1, storeIdFromDb);

            ResultSet resultSet1 = preparedStatement.executeQuery();
            resultSet1.next();

            String storeNameFromDb = resultSet1.getString(2);

            int addressIdFromDb = resultSet1.getInt(3);
            String addressNameFromDb = resultSet1.getString(7);

            int cityIdFromDb = resultSet1.getInt(10);
            String cityNameFromDb = resultSet1.getString(11);

            Address address = new Address(addressIdFromDb, addressNameFromDb);
            City city = new City(cityIdFromDb, cityNameFromDb);

            Store store = new Store(storeIdFromDb, storeNameFromDb, address, city);

            Order order = new Order(bookArray, user, isDelivery, store);

            preparedStatement.close();

            return order;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
