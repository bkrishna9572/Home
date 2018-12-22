package com.beekay.home.repository;

import com.beekay.home.model.Password;
import org.springframework.data.repository.CrudRepository;

public interface PasswordRepository extends CrudRepository<Password, Long> {
}
