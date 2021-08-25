package com.company.service;

import com.company.entity.Address;

import java.util.List;

public interface AddressService {
    void addAddress(Address address);

    void deleteAddress(int id);

    Address findByName(String address);

    Address findById(int id);

    List<Address> findAll();
}
