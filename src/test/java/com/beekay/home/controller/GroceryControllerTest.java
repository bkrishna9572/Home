package com.beekay.home.controller;

import com.beekay.home.api.v1.model.GroceryDTO;
import com.beekay.home.model.Quantity;
import com.beekay.home.service.GroceryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class GroceryControllerTest {

    @Mock
    GroceryService groceryService;

    @InjectMocks
    GroceryController groceryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(groceryController).build();
    }

    @Test
    public void getAllGroceries() throws Exception {
        Set<GroceryDTO> groceries = new HashSet<>();
        GroceryDTO dto1 = new GroceryDTO();
        dto1.setId(1L);
        groceries.add(dto1);

        when(groceryService.listGroceries()).thenReturn(groceries);

        mockMvc.perform(get("/api/v1/groceries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groceries", hasSize(1)));
    }

    @Test
    public void getGroceryById() throws Exception {
        GroceryDTO dto = new GroceryDTO();
        dto.setId(1L);

        when(groceryService.getGroceryById(anyLong())).thenReturn(dto);

        mockMvc.perform(get("/api/v1/groceries/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)));
    }

    @Test
    public void getGroceryByName() throws Exception {
        GroceryDTO dto = new GroceryDTO();
        dto.setId(1L);
        dto.setName("Rice");

        when(groceryService.getGroceryByName(anyString())).thenReturn(dto);

        mockMvc.perform(get("/api/v1/groceries/name/Rice")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Rice")));
    }

    @Test
    public void saveGrocery() throws Exception {
        GroceryDTO dto = new GroceryDTO();
        dto.setId(1L);
        dto.setName("Rice");
        dto.setQuantity(Quantity.LITRE);

        when(groceryService.saveGrocery(any())).thenReturn(dto);

        mockMvc.perform(post("/api/v1/groceries")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",equalTo("Rice")));
    }

    @Test
    public void updateGrocery() throws Exception {
        GroceryDTO dto = new GroceryDTO();
        dto.setName("Rice");

        GroceryDTO returnDTO = new GroceryDTO();
        returnDTO.setName(dto.getName());

        when(groceryService.updateGrocery(anyLong(),any())).thenReturn(returnDTO);

        mockMvc.perform(put("/api/v1/groceries/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(returnDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo("Rice")));
    }

    @Test
    public void deleteGroceryById() throws Exception {
        mockMvc.perform(delete("/api/v1/groceries/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        verify(groceryService,times(1)).deleteGroceryById(anyLong());;
    }
}