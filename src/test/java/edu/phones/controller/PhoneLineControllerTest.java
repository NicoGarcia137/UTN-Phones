package edu.phones.controller;

import edu.phones.domain.PhoneLine;
import edu.phones.domain.Province;
import edu.phones.exceptions.notExist.PhoneLineNotExistException;
import edu.phones.service.PhoneLineService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PhoneLineControllerTest {

    PhoneLineController lineController;
    @Mock
    PhoneLineService lineService;

    @Before
    public void serUp(){
        initMocks(this);
        lineController = new PhoneLineController(lineService);
    }

    @Test
    public void testCreateLineOk(){
        PhoneLine toAdd = new PhoneLine("2235997823", false, null , null);
        PhoneLine added = new PhoneLine(1, "2235997823", true, null, null);
        when(lineService.createLine(toAdd)).thenReturn(added);
        lineController.createLine(toAdd);

        assertEquals(toAdd.getNumber(), added.getNumber());
        assertEquals(true, added.getState());
        verify(lineService, times(1)).createLine(toAdd);
    }

    @Test
    public void testRemoveLineOk() throws PhoneLineNotExistException {
        PhoneLine toRemove = new PhoneLine(1, "2235997823", true, null, null);
        doNothing().when(lineService).removeLine(toRemove);
        lineController.removeLine(toRemove);

        verify(lineService, times(1)).removeLine(toRemove);
    }

    @Test
    public void testUpdateLineOk() throws PhoneLineNotExistException {
        PhoneLine toUpdate = new PhoneLine(1,"2235997823", true, null , null);
        PhoneLine updated = new PhoneLine(1, "2235997822", true, null, null);
        when(lineService.updateLine(toUpdate)).thenReturn(updated);
        lineController.updateLine(toUpdate);

        assertEquals(toUpdate.getpLineId(), updated.getpLineId());
        verify(lineService, times(1)).updateLine(toUpdate);
    }

    @Test
    public void testGetLineOk(){
        PhoneLine line = new PhoneLine(1, "2235997823", true, null, null);
        Integer id = 1;
        when(lineService.getLine(id)).thenReturn(line);
        lineController.getLine(id);

        assertEquals(id, line.getpLineId());
        verify(lineService, times(1)).getLine(id);
    }

    @Test
    public void testGetAllOk(){
        List<PhoneLine> lines = new ArrayList<>();
        lines.add(new PhoneLine(1, "2235997823", true, null, null));
        when(lineService.getAll()).thenReturn(lines);
        lineController.getAll();

        verify(lineService, times(1)).getAll();
    }
}
