package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.City;
import java.util.List;

public interface CityService {
    City save(City city);
    City getById(int cityId);
    void deleteById(int cityId);
    List<City> getAll();
    City update(int cityId, City newCity);
}
