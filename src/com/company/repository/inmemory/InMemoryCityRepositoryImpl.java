package com.company.repository.inmemory;

import com.company.entity.City;
import com.company.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCityRepositoryImpl implements CityRepository {

    private static int incId;
    private static InMemoryCityRepositoryImpl instance;
    private List<City> cityList = new ArrayList<>();

    private InMemoryCityRepositoryImpl() {
    }

    public static InMemoryCityRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new InMemoryCityRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void addCity(City city) {
        city.setId(incId++);
        cityList.add(city);
    }

    @Override
    public void deleteCity(int id) {
        for (City city : cityList) {
            if (city.getId() == id) {
                cityList.remove(city);
                break;
            }
        }
    }

    @Override
    public City findById(int id) {
        for (City city : cityList) {
            if (city.getId() == id) {
                return city;
            }
        }
        return null;
    }

    @Override
    public City findByName(String city) {
        for (City city1 : cityList) {
            if (city1.getCity().equals(city1)) {
                return city1;
            }
        }
        return null;
    }

    @Override
    public List<City> findAll() {
        return cityList;
    }
}
