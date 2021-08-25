package com.company.service;

import com.company.entity.City;

import java.util.List;

public interface CityService {
    void addCity(City city);

    void deleteCity(int id);

    City findById(int id);

    City findByName(String city);

    List<City> findAll();
}
