package com.company.repository.file;

import com.company.entity.City;
import com.company.repository.CityRepository;

import java.util.List;

public class FileCityRepository implements CityRepository {

    private DataBase dataBase = new FileDataBaseImpl();
    private List<City> temp;

    {
        temp = dataBase.read(City.class);
    }

    @Override
    public void addCity(City city) {
        int lastCityId = dataBase.getId(City.class);
        city.setId(++lastCityId);
        dataBase.setId(City.class, lastCityId);
        temp.add(city);
        dataBase.write(temp, City.class);
    }

    @Override
    public void deleteCity(int id) {
        for (City city : temp) {
            if (city.getId() == id) {
                temp.remove(city);
                break;
            }
        }
    }

    @Override
    public City findById(int id) {
        for (City city : temp) {
            if (city.getId() == id) {
                return city;
            }
        }
        return null;
    }

    @Override
    public City findByName(String city) {
        for (City city1 : temp) {
            if (city1.getCity().equals(city)) {
                return city1;
            }
        }
        return null;
    }

    @Override
    public List<City> findAll() {
        return temp;
    }
}
