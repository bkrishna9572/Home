package com.beekay.home.service.impl;

import com.beekay.home.api.v1.mapper.GroceryMapper;
import com.beekay.home.api.v1.model.GroceryDTO;
import com.beekay.home.model.Grocery;
import com.beekay.home.repository.GroceryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class GroceryServiceTest {

    private static final String RICE = "Rice";
    private static final Long ID = 1L;
    private static final BigDecimal AMOUNT = new BigDecimal(2);

    @Mock
    GroceryRepository groceryRepository;

    GroceryServiceImpl service;

    Grocery grocery;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new GroceryServiceImpl(groceryRepository, GroceryMapper.INSTANCE);
        grocery = Grocery.builder().id(ID).name(RICE).amount(AMOUNT).build();

    }

    @Test
    public void listGroceries() {

        Set<Grocery> groceries = new HashSet<>();
        groceries.add(grocery);

        when(groceryRepository.findAll()).thenReturn(groceries);

        Set<GroceryDTO> returnedGroceries = service.listGroceries();

        assertEquals(1, returnedGroceries.size());

        verify(groceryRepository,times(1)).findAll();

    }

    @Test
    public void getGroceryById() {
        when(groceryRepository.findById(anyLong())).thenReturn(Optional.of(grocery));

        GroceryDTO groceryDto = service.getGroceryById(1L);

        assertNotNull(groceryDto);
        verify(groceryRepository, times(1)).findById(anyLong());
    }

    @Test
    public void getGroceryByIdNull() {
        when(groceryRepository.findById(anyLong())).thenReturn(Optional.empty());

        GroceryDTO groceryDto = service.getGroceryById(1L);

        assertNull(groceryDto);
        verify(groceryRepository, times(1)).findById(anyLong());
    }

    @Test
    public void getGroceryByName() {
        when(groceryRepository.findByName(anyString())).thenReturn(Optional.of(grocery));

        GroceryDTO groceryDto = service.getGroceryByName("dummy");

        assertNotNull(groceryDto);
        verify(groceryRepository, times(1)).findByName(anyString());
    }

    @Test
    public void saveGrocery() {
        when(groceryRepository.save(any())).thenReturn(grocery);

        GroceryDTO groceryDTO = service.saveGrocery(new GroceryDTO());
        assertEquals(grocery.getName(),groceryDTO.getName());
        verify(groceryRepository,times(1)).save(any());
    }
}