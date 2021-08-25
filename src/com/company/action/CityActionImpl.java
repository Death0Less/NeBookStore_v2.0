package com.company.action;

import com.company.entity.City;
import com.company.service.CityService;
import com.company.service.CityServiceImpl;
import com.company.util.Reader;
import com.company.util.ReaderImpl;
import com.company.util.Writer;
import com.company.util.WriterImpl;
import com.company.valid.CityValidator;

import java.util.List;

public class CityActionImpl implements CityAction {

    private Writer writer = new WriterImpl();
    private Reader reader = new ReaderImpl();
    private CityValidator cityValidator = new CityValidator();
    private CityService cityService = new CityServiceImpl();

    public CityActionImpl(Writer writer, Reader reader, CityValidator cityValidator, CityService cityService) {
        this.writer = writer;
        this.reader = reader;
        this.cityValidator = cityValidator;
        this.cityService = cityService;
    }

    public CityActionImpl() {

    }

    @Override
    public void addCity() {
        writer.writerStr("Введите город:");
        String city = reader.readStr();
        if (!checkCity(city)) {
            writer.writerStr("Вы ввели не те данные.");
            return;
        }
        if (cityValidator.checkCity(city)) {
            City city1 = new City(city);
            cityService.addCity(city1);
        } else {
            writer.writerStr("Вы ввели не те данные.");
        }
    }

    @Override
    public void deleteCity() {
        writer.writerStr("Введите id, чтобы удалить city:");
        int id = reader.readInt();
        cityService.deleteCity(id);
    }

    @Override
    public void findById() {
        writer.writerStr("Введите id, чтобы найти city");
        int id = reader.readInt();
        City byId = cityService.findById(id);
        writer.writerStr(byId.getId() + " " + byId.getCity());
    }

    @Override
    public void findByName() {
        writer.writerStr("Введите город, чтобы найти city");
        String city = reader.readStr();
        City byName = cityService.findByName(city);
        writer.writerStr(byName.getId() + " " + byName.getCity());
    }

    @Override
    public void findAll() {
        List<City> cityList = cityService.findAll();
        writer.writerStr("Города:");
        for (City city : cityList) {
            writer.writerStr(city.getId() + city.getCity());
        }
    }

    boolean checkCity(String city) {
        return cityService.findByName(city) == null;
    }
}
