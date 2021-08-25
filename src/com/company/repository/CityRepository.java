package com.company.repository;

import com.company.entity.City;

import java.util.List;

public interface CityRepository {
    void addCity(City city);

    void deleteCity(int id);

    City findById(int id);

    City findByName(String city);

    List<City> findAll();

}
