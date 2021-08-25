package com.company.repository;

import com.company.entity.Address;

import java.util.List;

public interface AddressRepository {
    void addAddress(Address address);

    void deleteAddress(int id);

    Address findByName(String address);

    Address findById(int id);

    List<Address> findAll();


}
