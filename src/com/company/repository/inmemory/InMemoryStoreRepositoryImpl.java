package com.company.repository.inmemory;

import com.company.entity.Store;
import com.company.repository.StoreRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStoreRepositoryImpl implements StoreRepository {

    private static int incId;
    private static InMemoryStoreRepositoryImpl instance;
    private List<Store> storeList = new ArrayList<>();

    private InMemoryStoreRepositoryImpl() {
    }

    public static InMemoryStoreRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new InMemoryStoreRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void addStore(Store store) {
        store.setId(incId++);
        storeList.add(store);
    }

    @Override
    public void deleteStore(int id) {
        for (Store store : storeList) {
            if (store.getId() == id) {
                storeList.remove(store);
                break;
            }
        }
    }

    @Override
    public List<Store> findAll() {
        return storeList;
    }

    @Override
    public Store findById(int id) {
        for (Store store : storeList) {
            if (store.getId() == id) {
                return store;
            }
        }
        return null;
    }

    @Override
    public Store findByStoreName(String storeName) {
        for (Store store : storeList) {
            if (store.getStoreName().equals(storeName)) {
                return store;
            }
        }
        return null;
    }
}
