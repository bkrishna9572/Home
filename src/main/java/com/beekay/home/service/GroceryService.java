package com.beekay.home.service;

import com.beekay.home.api.v1.model.GroceryDTO;

import java.util.Set;

public interface GroceryService {

    Set<GroceryDTO> listGroceries();

    GroceryDTO getGroceryById(Long id);

    GroceryDTO getGroceryByName(String name);

    GroceryDTO saveGrocery(GroceryDTO grocery);

    GroceryDTO updateGrocery(Long id, GroceryDTO groceryDTO);
//
//    void deleteGrocery(Grocery grocery);
//
//    void deleteGroceryById(Long id);

}
