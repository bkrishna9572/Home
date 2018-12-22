package com.beekay.home.service.impl;

import com.beekay.home.model.Grocery;
import com.beekay.home.repository.GroceryRepository;
import com.beekay.home.service.GroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class GroceryServiceImpl implements GroceryService {

    private GroceryRepository repository;

    public GroceryServiceImpl(GroceryRepository repository) {
        log.info("GroceryServiceImpl Object Created");
        this.repository = repository;
    }

    @Override
    public Set<Grocery> listGroceries() {
        log.info("listGroceries called");
        Set<Grocery> groceries = new HashSet<>();
        repository.findAll().forEach(groceries::add);
        log.info("returning a set of size "+groceries.size());
        return groceries;
    }

    @Override
    public Grocery getGroceryById(Long id) {
        return null;
    }

    @Override
    public Grocery saveGrocery(Grocery grocery) {
        return null;
    }

    @Override
    public void deleteGrocery(Grocery grocery) {

    }

    @Override
    public void deleteGroceryById(Long id) {

    }
}
