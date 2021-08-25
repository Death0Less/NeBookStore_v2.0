package com.company.repository.file;

import com.company.entity.Address;
import com.company.repository.AddressRepository;

import java.util.List;

public class FileAddressRepository implements AddressRepository {

    private DataBase dataBase = new FileDataBaseImpl();
    private List<Address> temp;

    {
        temp = dataBase.read(Address.class);
    }

    @Override
    public void addAddress(Address address) {
        int lastAddressId = dataBase.getId(Address.class); // Получаем id из базы данных
        address.setId(++lastAddressId); // Увеличиваем id на 1
        dataBase.setId(Address.class, lastAddressId); // Сетим id Address'у
        temp.add(address);
        dataBase.write(temp, Address.class); // Записываем в базу данных обновлённый лист
    }

    @Override
    public void deleteAddress(int id) {
        for (Address address : temp) {
            if (address.getId() == id) {
                temp.remove(address);
                break;
            }
        }
        dataBase.write(temp, Address.class);
    }

    @Override
    public Address findByName(String address) {
        for (Address address1 : temp) {
            if (address1.getAddress().equals(address)) {
                return address1;
            }
        }
        return null;
    }

    @Override
    public Address findById(int id) {
        for (Address address : temp) {
            if (address.getId() == id) {
                return address;
            }
        }
        return null;
    }

    @Override
    public List<Address> findAll() {
        return temp;
    }
}
