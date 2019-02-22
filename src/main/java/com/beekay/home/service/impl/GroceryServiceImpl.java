package com.beekay.home.service.impl;

import com.beekay.home.api.v1.mapper.GroceryMapper;
import com.beekay.home.api.v1.model.GroceryDTO;
import com.beekay.home.exceptions.ResourceNotFoundException;
import com.beekay.home.exceptions.UniqueConstraintViolationException;
import com.beekay.home.model.Grocery;
import com.beekay.home.repository.GroceryRepository;
import com.beekay.home.service.GroceryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@Profile({"default","dev"})
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
            throw new ResourceNotFoundException("Could not find any grocery with id "+id);
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
            throw new ResourceNotFoundException("Could not find any grocery with name "+name);
        }
    }

    @Override
    public GroceryDTO saveGrocery(GroceryDTO groceryDTO) throws UniqueConstraintViolationException {
        log.debug("Saving a grocery with name "+groceryDTO.getName());
        try {
            GroceryDTO g = getGroceryByName(groceryDTO.getName());
            throw new UniqueConstraintViolationException("Entry for " + groceryDTO.getName() +
                    " already found with id " + g.getId());
        } catch (ResourceNotFoundException ex) {
            Grocery grocery = groceryMapper.groceryDTOToGrocery(groceryDTO);

            Grocery savedGrocery = repository.save(grocery);
            log.debug("Grocery saved with id "+savedGrocery.getId());
            return groceryMapper.groceryToGroceryDTO(savedGrocery);
        }

    }

    @Override
    public GroceryDTO updateGrocery(Long id, GroceryDTO groceryDTO) {
        Grocery grocery = groceryMapper.groceryDTOToGrocery(groceryDTO);
        grocery.setId(id);
        Grocery savedGrocery = repository.save(grocery);
        log.debug("Updated Grocery with id "+id);
        return groceryMapper.groceryToGroceryDTO(savedGrocery);
    }

    @Override
    public void deleteGroceryById(Long id) {
        log.debug("Deleting grocery with id "+id);
        repository.deleteById(id);
    }

}
