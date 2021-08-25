package com.company.action;

import com.company.entity.Address;
import com.company.entity.Order;
import com.company.entity.Store;
import com.company.service.OrderService;
import com.company.service.OrderServiceImpl;
import com.company.service.StoreService;
import com.company.service.StoreServiceImpl;
import com.company.util.Reader;
import com.company.util.ReaderImpl;
import com.company.util.Writer;
import com.company.util.WriterImpl;

import java.util.Arrays;
import java.util.List;

public class OrderActionImpl implements OrderAction {

    private OrderService orderService = new OrderServiceImpl();
    private Writer writer = new WriterImpl();
    private Reader reader = new ReaderImpl();
    private StoreService storeService = new StoreServiceImpl();

    public OrderActionImpl(OrderService orderService, Writer writer, Reader reader, StoreService storeService) {
        this.orderService = orderService;
        this.writer = writer;
        this.reader = reader;
        this.storeService = storeService;
    }

    public OrderActionImpl() {
    }


    @Override
    public void addDelivery() {
        writer.writerStr("Дай адрес:");
        String address = reader.readStr();
        Address address1 = new Address(address);
        orderService.addOrder(new Order(MainActionImpl.activeSession.getBasket().getBooks(), MainActionImpl.activeSession.getUser(), address1, true));
    }

    @Override
    public void addPickUp() {
        writer.writerStr("Введите пункты самовывоза:");
        Store store = getStoreList();
        Order order = new Order(MainActionImpl.activeSession.getBasket().getBooks(), MainActionImpl.activeSession.getUser(), false, store);
        orderService.addOrder(order);
    }

    @Override
    public void deleteOrder() {
        writer.writerStr("Введите id, чтобы удалить заказ:");
        int id = reader.readInt();
        orderService.deleteOrder(id);
    }

    @Override
    public void findAll() {
        List<Order> orderList = orderService.findAll();
        for (Order order : orderList) {
            writer.writerStr(order.getId() + order.getUser().getFirstName() + order.getUser().getLastName() + Arrays.toString(order.getBooks()));
        }
    }

    @Override
    public void findByStore() {
        writer.writerStr("Выберите магазин:");
        Store store = getStoreList();
        List<Order> orderList = orderService.findAllByStore(store);
        for (Order order : orderList) {
            writer.writerStr(order.getId() + " " + order.getStore());
        }
    }

    @Override
    public void orderHistory() {
        List<Order> orders = orderService.findAllByUser(MainActionImpl.activeSession.getUser());
        for (Order order : orders) {
            if (order.getStore() != null) {
                writer.writerStr(order.getId() + " " + order.getStore());
            }
            if (order.getAddress() != null) {
                writer.writerStr(order.getId() + " " + order.getAddress());
            }
        }
    }

    ////////////////////КРАСАВА, ТЁМА
    ////////////////////ЛУЧШИЙ, ПРОСТО ЛУЧШИЙ!!!!!!!!!!!!!!!!!!

    private Store getStoreList() {
        List<Store> storeList = storeService.findAll();
        for (Store store : storeList) {
            writer.writerStr(store.getId() + store.getStoreName());
        }
        int i = reader.readInt();
        return storeList.get(i);
    }
}
