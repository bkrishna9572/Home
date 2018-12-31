package com.beekay.home.service.impl;

import com.beekay.home.api.v1.mapper.GroceryMapper;
import com.beekay.home.api.v1.model.GroceryDTO;
import com.beekay.home.model.Grocery;
import com.beekay.home.repository.GroceryRepository;
import com.beekay.home.service.GroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class GroceryServiceImpl implements GroceryService {

    private final GroceryRepository repository;
    private final GroceryMapper groceryMapper;

    public GroceryServiceImpl(GroceryRepository repository, GroceryMapper groceryMapper) {
        this.groceryMapper = groceryMapper;
        this.repository = repository;
        log.info("GroceryServiceImpl Object Created");
    }

    @Override
    public Set<GroceryDTO> listGroceries() {
        log.info("listGroceries called");
        Set<GroceryDTO> groceries = StreamSupport.stream(repository.findAll().spliterator(),false)
                .map(groceryMapper::groceryToGroceryDTO)
                .collect(Collectors.toSet());
        log.info("returning a set of size "+groceries.size());
        return groceries;
    }

    @Override
    public GroceryDTO getGroceryById(Long id) {
        log.debug("Getting grocery by id "+id);
        Optional<Grocery> groceryOptional = repository.findById(id);
        if(groceryOptional.isPresent()){
            log.debug("Grocery found with id "+groceryOptional.get().getId());
            return groceryMapper.groceryToGroceryDTO(groceryOptional.get());
        }else{
            log.debug("Grocery not found with id "+id);
            return null;
        }
    }

    @Override
    public GroceryDTO getGroceryByName(String name) {
        log.debug("Getting grocery by name "+name);
        Optional<Grocery> groceryOptional = repository.findByName(name);
        if(groceryOptional.isPresent()){
            log.debug("Grocery found with id "+groceryOptional.get().getId());
            return groceryMapper.groceryToGroceryDTO(groceryOptional.get());
        }else{
            log.debug("Grocery not found with name "+name);
            //TODO Exception Handling
            return null;
        }
    }

    @Override
    public GroceryDTO saveGrocery(GroceryDTO groceryDTO) {
        Grocery grocery = groceryMapper.groceryDTOToGrocery(groceryDTO);

        Grocery savedGrocery = repository.save(grocery);

        return groceryMapper.groceryToGroceryDTO(savedGrocery);
    }

}
