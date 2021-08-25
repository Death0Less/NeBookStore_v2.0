package com.company.repository.inmemory;

import com.company.entity.Address;
import com.company.repository.AddressRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAddressRepositoryImpl implements AddressRepository {
    private static int incID;
    private static InMemoryAddressRepositoryImpl instance;
    private List<Address> addressList = new ArrayList<>();

    private InMemoryAddressRepositoryImpl() {
    }

    public static InMemoryAddressRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new InMemoryAddressRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void addAddress(Address address) {
        address.setId(incID++);
        addressList.add(address);
    }

    @Override
    public void deleteAddress(int id) {
        for (Address address : addressList) {
            if (address.getId() == id) {
                addressList.remove(address);
            }
            break;
        }
    }

    @Override
    public Address findByName(String address) {
        for (Address address1 : addressList) {
            if (address1.getAddress().equals(address)) {
                return address1;
            }
        }
        return null;
    }

    @Override
    public Address findById(int id) {
        for (Address address : addressList) {
            if (address.getId() == id) {
                return address;
            }
        }
        return null;
    }

    @Override
    public List<Address> findAll() {
        return addressList;
    }
}
