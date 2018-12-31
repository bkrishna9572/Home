package com.beekay.home.repository;

import com.beekay.home.model.Password;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PasswordRepository extends CrudRepository<Password, Long> {
    Optional<Password> findBySiteName(String site);
}
