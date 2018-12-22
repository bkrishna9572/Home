package com.beekay.home.service;

import com.beekay.home.model.Grocery;

import java.util.Set;

public interface GroceryService {

    Set<Grocery> listGroceries();

    Grocery getGroceryById(Long id);

    Grocery saveGrocery(Grocery grocery);

    void deleteGrocery(Grocery grocery);

    void deleteGroceryById(Long id);

}
