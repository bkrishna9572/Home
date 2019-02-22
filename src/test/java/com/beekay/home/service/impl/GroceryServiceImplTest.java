package com.beekay.home.service.impl;

import com.beekay.home.api.v1.mapper.GroceryMapper;
import com.beekay.home.api.v1.model.GroceryDTO;
import com.beekay.home.exceptions.ResourceNotFoundException;
import com.beekay.home.exceptions.UniqueConstraintViolationException;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class GroceryServiceImplTest {

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

    @Test(expected = ResourceNotFoundException.class)
    public void getGroceryByIdNull() {
        when(groceryRepository.findById(anyLong())).thenReturn(Optional.empty());

        GroceryDTO groceryDto = service.getGroceryById(1L);

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

        GroceryDTO groceryDTO = null;
        try {
            groceryDTO = service.saveGrocery(new GroceryDTO());
        } catch (UniqueConstraintViolationException e) {
            e.printStackTrace();
        }
        assertEquals(grocery.getName(),groceryDTO.getName());
        verify(groceryRepository,times(1)).save(any());
    }

    @Test
    public void updateGrocery() {
        GroceryDTO dto = new GroceryDTO();
        dto.setName("Rice");
        Long id = 2L;

        Grocery returnGrocery = new Grocery();
        returnGrocery.setName(dto.getName());
        returnGrocery.setId(id);
        when(groceryRepository.save(any())).thenReturn(returnGrocery);

        GroceryDTO savedDTO = service.updateGrocery(id,dto);

        assertEquals(id, savedDTO.getId());
    }

    @Test
    public void deleteGrocery() {
        service.deleteGroceryById(anyLong());
        verify(groceryRepository,times(1)).deleteById(anyLong());
    }
}