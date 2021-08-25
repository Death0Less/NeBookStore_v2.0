package com.company.action;

import com.company.entity.Address;
import com.company.entity.City;
import com.company.entity.Store;
import com.company.service.*;
import com.company.util.Reader;
import com.company.util.ReaderImpl;
import com.company.util.Writer;
import com.company.util.WriterImpl;
import com.company.valid.StoreValidator;

import java.util.List;

public class StoreActionImpl implements StoreAction {

    private StoreService storeService = new StoreServiceImpl();
    private StoreValidator storeValidator = new StoreValidator();
    private Writer writer = new WriterImpl();
    private Reader reader = new ReaderImpl();
    private CityService cityService = new CityServiceImpl();
    private AddressService addressService = new AddressServiceImpl();

    public StoreActionImpl(StoreService storeService, StoreValidator storeValidator, Writer writer, Reader reader, CityService cityService, AddressService addressService) {
        this.storeService = storeService;
        this.storeValidator = storeValidator;
        this.writer = writer;
        this.reader = reader;
        this.cityService = cityService;
        this.addressService = addressService;
    }

    public StoreActionImpl() {
    }

    @Override
    public void addStore() {
        writer.writerStr("Введите город:");
        City city = getCityList();
        writer.writerStr("Введите адрес:");
        Address address = getAddressList();
        writer.writerStr("Введите нзвание магазина:");
        String storeName = reader.readStr();
        if (!storeValidator.validStoreName(storeName)) {
            writer.writerStr("Вы ввели не те данные!");
        }

        Store store = new Store(storeName, address, city);
        storeService.addStore(store);
    }

    @Override
    public void deleteStore() {
        writer.writerStr("Введите id, чтобы удалить магазин:");
        int id = reader.readInt();
        storeService.deleteStore(id);
    }

    @Override
    public void findAll() {
        List<Store> storeList = storeService.findAll();
        for (Store store : storeList) {
            writer.writerStr(store.getId() + store.getStoreName());
        }
    }

    @Override
    public void findById() {
        writer.writerStr("Введите id, чтобы найти магазин:");
        int id = reader.readInt();
        Store store = storeService.findById(id);
        writer.writerStr(store.getId() + " " + store.getStoreName());
    }

    @Override
    public void findByStoreName() {
        writer.writerStr("Введите название магазина, чтобы найти магазин:");
        String storeName = reader.readStr();
        Store store = storeService.findByStoreName(storeName);
        writer.writerStr(store.getId() + " " + store.getStoreName());
    }

    private City getCityList() {
        List<City> cityList = cityService.findAll();
        for (City city : cityList) {
            writer.writerStr(city.getId() + city.getCity());
        }
        int i = reader.readInt();
        return cityList.get(i);
    }

    private Address getAddressList() {
        List<Address> addressList = addressService.findAll();
        for (Address address : addressList) {
            writer.writerStr(address.getId() + " " + address.getAddress());
        }
        int i = reader.readInt();
        return addressList.get(i);
    }
}
