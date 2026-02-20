package dev.sandeep.BookMyShowNov25.service;

import dev.sandeep.BookMyShowNov25.entity.City;
import dev.sandeep.BookMyShowNov25.repository.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepo cityRepo;

    @Override
    public City save(City city) {
        return cityRepo.save(city);
    }

    @Override
    public City getById(int cityId) {
        return cityRepo.findById(cityId).orElse(null);
    }

    @Override
    public void deleteById(int cityId) {
        cityRepo.deleteById(cityId);
    }

    @Override
    public List<City> getAll() {
        return cityRepo.findAll();
    }

    @Override
    public City update(int cityId, City newCity) {
        if (cityRepo.existsById(cityId)) {
            newCity.setId(cityId);
            return cityRepo.save(newCity);
        }
        return null;
    }
}
