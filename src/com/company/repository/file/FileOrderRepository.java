package com.company.repository.file;

import com.company.entity.Order;
import com.company.entity.Store;
import com.company.entity.User;
import com.company.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class FileOrderRepository implements OrderRepository {

    private DataBase dataBase = new FileDataBaseImpl();
    private List<Order> temp;

    {
        temp = dataBase.read(Order.class);
    }


    @Override
    public void addOrder(Order order) {
        int lastOrderId = dataBase.getId(Order.class);
        order.setId(++lastOrderId);
        dataBase.setId(Order.class, lastOrderId);
        temp.add(order);
        dataBase.write(temp, Order.class);

    }

    @Override
    public void deleteOrder(int id) {
        for (Order order : temp) {
            if (order.getId() == id) {
                temp.remove(order);
                break;
            }
        }
    }

    @Override
    public Order findById(int id) {
        for (Order order : temp) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        return temp;
    }

    @Override
    public List<Order> findAllByStore(Store store) {
        List<Order> orderList = new ArrayList<>();
        for (Order order : temp) {
            if (order.getStore().getStoreName().equals(store.getStoreName())) {
                orderList.add(order);
                return orderList;
            }
        }
        return null;
    }

    @Override
    public List<Order> findAllByUser(User user) {
        List<Order> orderList = new ArrayList<>();
        for (Order order : temp) {
            if (order.getUser().getLastName().equals(user.getLastName())) {
                orderList.add(order);
                return orderList;
            }
        }
        return null;
    }
}
