package com.company.service;

import com.company.entity.Address;
import com.company.repository.AddressRepository;
import com.company.repository.database.DbAddressRepository;

import java.util.List;

public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository = new DbAddressRepository();

    @Override
    public void addAddress(Address address) {
        addressRepository.addAddress(address);
    }

    @Override
    public void deleteAddress(int id) {
        addressRepository.deleteAddress(id);
    }

    @Override
    public Address findByName(String address) {
        return addressRepository.findByName(address);
    }

    @Override
    public Address findById(int id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
