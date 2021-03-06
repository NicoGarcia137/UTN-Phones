package edu.phones.controller;

import edu.phones.domain.Province;
import edu.phones.exceptions.alreadyExist.ProvinceAlreadyExistsException;
import edu.phones.exceptions.notExist.ProvinceNotExistException;
import edu.phones.service.ProvinceService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProvinceControllerTest {

    ProvinceController provinceController;
    @Mock
    ProvinceService provinceService;

    @Before
    public void setUp(){
        initMocks(this);
        provinceController = new ProvinceController(provinceService);
    }

    @Test
    public void testCreateProvinceOk() throws ProvinceAlreadyExistsException {
        Province toAdd = new Province("name");
        Province added = new Province(1, "name");
        when(provinceService.createProvince(toAdd)).thenReturn(added);

        Province province = provinceController.createProvince(toAdd);

        assertEquals(added.getProvinceId(), province.getProvinceId());
        assertEquals(added.getName(), province.getName());
        verify(provinceService, times(1)).createProvince(toAdd);
    }

    @Test
    public void testRemoveProvinceOk() throws ProvinceNotExistException {
        Province toRemove = new Province(1, "name");
        doNothing().when(provinceService).remove(toRemove);

        provinceController.removeProvince(toRemove);

        verify(provinceService, times(1)).remove(toRemove);
    }

    @Test
    public void testUpdateProvinceOk() throws ProvinceNotExistException {
        Province toUpdate = new Province(1, "name");
        Province updated = new Province(1, "name2");
        when(provinceService.updateProvince(toUpdate)).thenReturn(updated);

        Province province = provinceController.updateProvince(toUpdate);

        assertEquals(updated.getProvinceId(), province.getProvinceId());
        verify(provinceService, times(1)).updateProvince(toUpdate);
    }

    @Test
    public void testGetProvinceOk(){
        Province prov = new Province(1, "name");
        Integer id = 1;
        when(provinceService.getProvince(id)).thenReturn(prov);

        Province getted = provinceController.getProvince(id);

        assertEquals(prov.getProvinceId(), getted.getProvinceId());
        verify(provinceService, times(1)).getProvince(id);
    }

    @Test
    public void testGetAllOk(){
        List<Province> provList = new ArrayList<>();
        provList.add(new Province(1, "name"));
        when(provinceService.getAll()).thenReturn(provList);

        List<Province> provinces = provinceController.getAll();

        assertEquals(provList.size(), provinces.size());
        verify(provinceService, times(1)).getAll();
    }
}
