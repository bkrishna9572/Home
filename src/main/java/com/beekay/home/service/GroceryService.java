package com.beekay.home.service;

import com.beekay.home.api.v1.model.GroceryDTO;
import com.beekay.home.exceptions.UniqueConstraintViolationException;

import java.util.Set;

public interface GroceryService {

    Set<GroceryDTO> listGroceries();

    GroceryDTO getGroceryById(Long id);

    GroceryDTO getGroceryByName(String name);

    GroceryDTO saveGrocery(GroceryDTO grocery) throws UniqueConstraintViolationException;

    GroceryDTO updateGrocery(Long id, GroceryDTO groceryDTO);

    void deleteGroceryById(Long id);

}
