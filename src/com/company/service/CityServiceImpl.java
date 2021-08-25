package com.company.service;

import com.company.entity.City;
import com.company.repository.CityRepository;
import com.company.repository.database.DbCityRepository;

import java.util.List;

public class CityServiceImpl implements CityService {

    private CityRepository cityRepository = new DbCityRepository();

    @Override
    public void addCity(City city) {
        cityRepository.addCity(city);
    }

    @Override
    public void deleteCity(int id) {
        cityRepository.deleteCity(id);

    }

    @Override
    public City findById(int id) {
        return cityRepository.findById(id);
    }

    @Override
    public City findByName(String city) {
        return cityRepository.findByName(city);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
