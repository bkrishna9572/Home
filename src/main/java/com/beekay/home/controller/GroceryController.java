package com.beekay.home.controller;

import com.beekay.home.api.v1.model.GroceryDTO;
import com.beekay.home.api.v1.model.GroceryListDTO;
import com.beekay.home.service.GroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<GroceryDTO> saveGrocery(@RequestBody GroceryDTO groceryDTO){
        return new ResponseEntity<>(
                groceryService.saveGrocery(groceryDTO),
                HttpStatus.CREATED
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<GroceryDTO> updateGrocery(@PathVariable Long id, @RequestBody GroceryDTO groceryDTO){
        return new ResponseEntity<>(
                groceryService.updateGrocery(id, groceryDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGrocery(@PathVariable Long id){
        groceryService.deleteGroceryById(id);
        return new ResponseEntity<Void>(
                HttpStatus.OK
        );
    }

}
