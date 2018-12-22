package com.beekay.home.service;

import com.beekay.home.model.Password;

import java.util.Set;

public interface PasswordService {

    Set<Password> listPasswords();

    Password getPasswordById(Long id);

    Password getPasswordBySite(String site);

    Password savePassword(Password password);

    Password updatePasswordById(Long id);

    void deletePasswordById(Long id);

    void deletePassword(Password password);

}
