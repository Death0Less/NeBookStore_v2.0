package com.company.service;

import com.company.entity.Store;

import java.util.List;

public interface StoreService {
    void addStore(Store store);

    void deleteStore(int id);

    List<Store> findAll();

    Store findById(int id);

    Store findByStoreName(String storeName);
}
