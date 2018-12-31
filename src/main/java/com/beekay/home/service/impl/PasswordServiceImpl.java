package com.beekay.home.service.impl;

import com.beekay.home.api.v1.model.PasswordDTO;
import com.beekay.home.model.Password;
import com.beekay.home.service.PasswordService;

import java.util.Set;

public class PasswordServiceImpl implements PasswordService {



    @Override
    public Set<PasswordDTO> listPasswords() {
        return null;
    }

    @Override
    public PasswordDTO getPasswordById(Long id) {
        return null;
    }

    @Override
    public PasswordDTO getPasswordBySite(String site) {
        return null;
    }

    @Override
    public Password savePassword(Password password) {
        //TODO
        return null;
    }

    @Override
    public Password updatePasswordById(Long id) {
        //TODO
        return null;
    }

    @Override
    public void deletePasswordById(Long id) {
        //TODO
    }

    @Override
    public void deletePassword(Password password) {
        //TODO
    }
}
