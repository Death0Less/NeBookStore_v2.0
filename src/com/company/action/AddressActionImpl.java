package com.company.action;

import com.company.entity.Address;
import com.company.service.AddressService;
import com.company.service.AddressServiceImpl;
import com.company.util.Reader;
import com.company.util.ReaderImpl;
import com.company.util.Writer;
import com.company.util.WriterImpl;
import com.company.valid.AddressValidator;

import java.util.List;

public class AddressActionImpl implements AddressAction {

    private Reader reader = new ReaderImpl();
    private Writer writer = new WriterImpl();
    private AddressService addressService = new AddressServiceImpl();
    private AddressValidator addressValidator = new AddressValidator();

    public AddressActionImpl(Reader reader, Writer writer, AddressService addressService, AddressValidator addressValidator) {
        this.reader = reader;
        this.writer = writer;
        this.addressService = addressService;
        this.addressValidator = addressValidator;
    }

    public AddressActionImpl() {
    }

    @Override
    public void addAddress() {
        writer.writerStr("Введите адрес:");
        String address = reader.readStr();
        if (!checkAddress(address)) {
            writer.writerStr("Такой адрес уже существует.");
            return;
        }
        if (addressValidator.checkAddress(address)) {
            addressService.addAddress(new Address(address));
        } else {
            writer.writerStr("Вы ввели не те данные.");
        }
    }

    @Override
    public void deleteAddress() {
        writer.writerStr("Введите адрес, чтобы удалить адрес:");
        int id = reader.readInt();
        addressService.deleteAddress(id);

    }

    @Override
    public void findByName() {
        writer.writerStr("Введите адрес:");
        String address1 = reader.readStr();
        Address address = addressService.findByName(address1);
        writer.writerStr(address.getId() + " - " + " " + address.getAddress());
    }

    @Override
    public void findById() {
        writer.writerStr("Введите id");
        int id1 = reader.readInt();
        Address address = addressService.findById(id1);
        writer.writerStr(address.getId() + " - " + " " + address.getAddress());
    }

    @Override
    public void findAll() {
        List<Address> addresses = addressService.findAll();
        for (Address address : addresses) {
            writer.writerStr(address.getId() + " - " + " " + address.getAddress());
        }

    }

    private boolean checkAddress(String address) {
        return addressService.findByName(address) == null;
    }
}
