package com.beekay.home.repository;

import com.beekay.home.model.Grocery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroceryRepository extends CrudRepository<Grocery, Long> {
    Optional<Grocery> findByName(String name);
}
