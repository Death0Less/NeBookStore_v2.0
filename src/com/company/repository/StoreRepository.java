package com.company.repository;

import com.company.entity.Store;

import java.util.List;

public interface StoreRepository {
    void addStore(Store store);

    void deleteStore(int id);

    List<Store> findAll();

    Store findById(int id);

    Store findByStoreName(String storeName);
}
