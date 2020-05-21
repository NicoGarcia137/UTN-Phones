package edu.phones.controller;

import edu.phones.domain.City;
import edu.phones.exceptions.notExist.CityNotExistException;
import edu.phones.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CityController {

    CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /** CRUD **/
    public City createCity(City city){
        return cityService.createCity(city);
    }

    public void removeCity(City city) throws CityNotExistException {
        cityService.remove(city);
    }

    public void updateCity(City city) throws CityNotExistException {
        cityService.updateCity(city);
    }

    public City getCity(Integer cityId){
        return cityService.getCity(cityId);
    }

    public List<City> getAll(){
        return cityService.getAll();
    }
}
