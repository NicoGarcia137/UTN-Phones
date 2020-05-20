package edu.phones.controller;

import edu.phones.domain.City;
import edu.phones.domain.Province;
import edu.phones.exceptions.CityNotExistException;
import edu.phones.exceptions.ProvinceNotExistException;
import edu.phones.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProvinceController {

    ProvinceService provinceService;

    @Autowired
    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    /** CRUD **/
    public Province createProvince(Province province){
        return provinceService.createProvince(province);
    }

    public void removeProvince(Province province) throws ProvinceNotExistException {
        provinceService.remove(province);
    }

    public void updateProvince(Province province) throws ProvinceNotExistException {
        provinceService.updateProvince(province);
    }

    public Province getProvince(Integer provinceId){
        return provinceService.getProvince(provinceId);
    }

    public List<Province> getAll(){
        return provinceService.getAll();
    }
}