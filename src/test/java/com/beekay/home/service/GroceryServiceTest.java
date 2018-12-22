package com.beekay.home.service;

import com.beekay.home.model.Grocery;
import com.beekay.home.repository.GroceryRepository;
import com.beekay.home.service.impl.GroceryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class GroceryServiceTest {

    private static final String RICE = "Rice";
    private static final Long ID = 1L;
    private static final BigDecimal AMOUNT = new BigDecimal(2);

    @Mock
    GroceryRepository groceryRepository;

    @InjectMocks
    GroceryServiceImpl service;

    Grocery grocery;

    @Before
    public void setUp() throws Exception {

        grocery = Grocery.builder().id(ID).name(RICE).amount(AMOUNT).build();
    }

    @Test
    public void listGroceries() {

        Set<Grocery> groceries = new HashSet<>();
        groceries.add(grocery);

        when(groceryRepository.findAll()).thenReturn(groceries);

        Set<Grocery> returnedGroceries = service.listGroceries();

        assertEquals(1, returnedGroceries.size());

        verify(groceryRepository,times(1)).findAll();

    }
}