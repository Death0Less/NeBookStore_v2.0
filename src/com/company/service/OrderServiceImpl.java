package com.company.service;

import com.company.entity.Order;
import com.company.entity.Store;
import com.company.entity.User;
import com.company.repository.OrderRepository;
import com.company.repository.file.FileOrderRepository;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository = new FileOrderRepository();

    @Override
    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteOrder(id);
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByStore(Store store) {
        return orderRepository.findAllByStore(store);
    }

    @Override
    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }
}
