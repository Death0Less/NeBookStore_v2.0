package com.company.repository.inmemory;

import com.company.entity.Order;
import com.company.entity.Store;
import com.company.entity.User;
import com.company.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderRepositoryImpl implements OrderRepository {

    private static int incId;
    private static InMemoryOrderRepositoryImpl instance;
    private List<Order> orderList = new ArrayList<>();

    private InMemoryOrderRepositoryImpl() {
    }

    public static InMemoryOrderRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new InMemoryOrderRepositoryImpl();
        }
        return instance;
    }


    @Override
    public void addOrder(Order order) {
        order.setId(incId++);
        orderList.add(order);
    }

    @Override
    public void deleteOrder(int id) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                orderList.remove(order);
                break;
            }
        }
    }

    @Override
    public Order findById(int id) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        return orderList;
    }

    @Override
    public List<Order> findAllByStore(Store store) {
        List<Order> orderList1 = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getStore().getStoreName().equals(store.getStoreName())) {
                orderList1.add(order);
            }
        }
        return orderList1;
    }

    @Override
    public List<Order> findAllByUser(User user) {
        List<Order> orderList2 = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getUser().getLastName().equals(user.getLastName())) {
                orderList2.add(order);

            }
        }
        return orderList2;
    }
}
