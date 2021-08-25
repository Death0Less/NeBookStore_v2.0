package com.company.service;

import com.company.entity.Store;
import com.company.repository.StoreRepository;
import com.company.repository.database.DbStoreRepository;

import java.util.List;

public class StoreServiceImpl implements StoreService {

    private StoreRepository storeRepository = new DbStoreRepository();

    @Override
    public void addStore(Store store) {
        storeRepository.addStore(store);
    }

    @Override
    public void deleteStore(int id) {
        storeRepository.deleteStore(id);
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store findById(int id) {
        return storeRepository.findById(id);
    }

    @Override
    public Store findByStoreName(String storeName) {
        return storeRepository.findByStoreName(storeName);
    }
}
