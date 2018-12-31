package com.beekay.home.controller;

import com.beekay.home.api.v1.model.PasswordDTO;
import com.beekay.home.service.PasswordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class PasswordControllerTest {

    @Mock
    PasswordService passwordService;

    @InjectMocks
    PasswordController controller;

    MockMvc mockMvc;

    PasswordDTO password;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        password = new PasswordDTO();
        password.setId(1L);
        password.setSiteName("google.com");
    }

    @Test
    public void listPasswords() throws Exception {
        Set<PasswordDTO> passwords = new HashSet<>();
        passwords.add(password);
        when(passwordService.listPasswords()).thenReturn(passwords);

        mockMvc.perform(get("/api/v1/passwords"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.passwords",hasSize(1)));
    }

    @Test
    public void getPasswordById() throws Exception {
        when(passwordService.getPasswordById(anyLong())).thenReturn(password);

        mockMvc.perform(get("/api/v1/passwords/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",equalTo(1)));
    }

    @Test
    public void getPasswordBySite() throws Exception {
        when(passwordService.getPasswordBySite(anyString())).thenReturn(password);

        mockMvc.perform(get("/api/v1/passwords/site/google.com")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.siteName",equalTo("google.com")));
    }
}