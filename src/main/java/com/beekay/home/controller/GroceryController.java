package com.beekay.home.controller;

import com.beekay.home.api.v1.model.GroceryDTO;
import com.beekay.home.api.v1.model.GroceryListDTO;
import com.beekay.home.service.GroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/groceries")
@Slf4j
public class GroceryController {

    private final GroceryService groceryService;

    public GroceryController(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    @GetMapping
    public ResponseEntity<GroceryListDTO> getAllGroceries(){
        return new ResponseEntity<>(
                new GroceryListDTO(groceryService.listGroceries()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GroceryDTO> getGroceryById(@PathVariable Long id){
        return new ResponseEntity<>(
                groceryService.getGroceryById(id), HttpStatus.OK
        );
    }

    @GetMapping("/name/{groceryName}")
    public ResponseEntity<GroceryDTO> getGroceryById(@PathVariable String groceryName){
        return new ResponseEntity<>(
                groceryService.getGroceryByName(groceryName), HttpStatus.OK
        );
    }

}
