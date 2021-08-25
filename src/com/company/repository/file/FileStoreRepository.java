package com.company.repository.file;

import com.company.entity.Order;
import com.company.entity.Store;
import com.company.repository.StoreRepository;

import java.util.List;

public class FileStoreRepository implements StoreRepository {

    private DataBase dataBase = new FileDataBaseImpl();
    private List<Store> temp;

    @Override
    public void addStore(Store store) {
        int lastOrderId = dataBase.getId(Order.class);
        store.setId(++lastOrderId);
        dataBase.setId(Store.class, lastOrderId);
        temp.add(store);
        dataBase.write(temp, Store.class);
    }

    @Override
    public void deleteStore(int id) {
        for (Store store : temp) {
            if (store.getId() == id) {
                temp.remove(store);
                break;
            }
        }
    }

    @Override
    public List<Store> findAll() {
        return temp;
    }

    @Override
    public Store findById(int id) {
        for (Store store : temp) {
            if (store.getId() == id) {
                return store;
            }
        }
        return null;
    }

    @Override
    public Store findByStoreName(String storeName) {
        for (Store store : temp) {
            if (store.getStoreName().equals(storeName)) {
                return store;
            }
        }
        return null;
    }
}
