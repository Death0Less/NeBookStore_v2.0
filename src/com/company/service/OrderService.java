package com.company.service;

import com.company.entity.Order;
import com.company.entity.Store;
import com.company.entity.User;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);

    void deleteOrder(int id);

    Order findById(int id);

    List<Order> findAll();

    List<Order> findAllByStore(Store store);

    List<Order> findAllByUser(User user);
}
