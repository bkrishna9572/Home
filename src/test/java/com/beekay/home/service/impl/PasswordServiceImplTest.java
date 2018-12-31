package com.beekay.home.service.impl;

import com.beekay.home.api.v1.model.PasswordDTO;
import com.beekay.home.model.Password;
import com.beekay.home.repository.PasswordRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PasswordServiceImplTest {

    @Mock
    PasswordRepository repository;

    @InjectMocks
    PasswordServiceImpl passwordServiceImpl;

    Password password;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        password = new Password();
        password.setId(1L);
        password.setSiteName("google.com");
    }

    @Test
    public void listPasswords() {
        Set<Password> passwords = new HashSet<>();
        passwords.add(password);

        when(repository.findAll()).thenReturn(passwords);

        Set<PasswordDTO> returnedPasswords = passwordServiceImpl.listPasswords();

        assertEquals(1,returnedPasswords.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getPasswordById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(password));

        PasswordDTO dto = passwordServiceImpl.getPasswordById(1L);
        assertNotNull(dto);
        assertEquals(Long.valueOf(1L),dto.getId());
        verify(repository,times(1)).findById(anyLong());
    }

    @Test
    public void getPasswordByIdNull() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        PasswordDTO dto = passwordServiceImpl.getPasswordById(1L);
        assertNull(dto);
        verify(repository,times(1)).findById(anyLong());
    }

    @Test
    public void getPasswordBySite() {
        when(repository.findBySiteName(anyString())).thenReturn(Optional.of(password));

        PasswordDTO dto = passwordServiceImpl.getPasswordBySite("google.com");
        assertNotNull(dto);
        assertEquals(password.getSiteName(),dto.getSiteName());
        verify(repository,times(1)).findBySiteName(anyString());
    }
}