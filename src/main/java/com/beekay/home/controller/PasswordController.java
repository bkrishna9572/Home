package com.beekay.home.controller;

import com.beekay.home.api.v1.model.PasswordDTO;
import com.beekay.home.api.v1.model.PasswordListDTO;
import com.beekay.home.service.PasswordService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/passwords")
public class PasswordController {

    private final PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @GetMapping
    public ResponseEntity<PasswordListDTO> listPassowrds(){
        return new ResponseEntity<>(
                new PasswordListDTO(passwordService.listPasswords()),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PasswordDTO> getPasswordById(@PathVariable Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(
                passwordService.getPasswordById(id),
                headers,
                HttpStatus.OK
        );
    }

    @GetMapping(value = "site/{siteName}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PasswordDTO> getPasswordById(@PathVariable String siteName){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        ResponseEntity<PasswordDTO> response = new ResponseEntity<>(
                passwordService.getPasswordBySite(siteName),
                headers,
                HttpStatus.OK
        );
        return response;
    }
}
