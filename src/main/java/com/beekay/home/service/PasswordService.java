package com.beekay.home.service;

import com.beekay.home.api.v1.model.PasswordDTO;
import com.beekay.home.model.Password;

import java.util.Set;

public interface PasswordService {

    Set<PasswordDTO> listPasswords();

    PasswordDTO getPasswordById(Long id);

    PasswordDTO getPasswordBySite(String site);

    Password savePassword(Password password);

    Password updatePasswordById(Long id);

    void deletePasswordById(Long id);

    void deletePassword(Password password);

}
