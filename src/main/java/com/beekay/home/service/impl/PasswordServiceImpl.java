package com.beekay.home.service.impl;

import com.beekay.home.api.v1.mapper.PasswordMapper;
import com.beekay.home.api.v1.model.PasswordDTO;
import com.beekay.home.model.Password;
import com.beekay.home.repository.PasswordRepository;
import com.beekay.home.service.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class PasswordServiceImpl implements PasswordService {

    private final PasswordRepository passwordRepository;
    private PasswordMapper mapper = PasswordMapper.INSTANCE;

    public PasswordServiceImpl(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    @Override
    public Set<PasswordDTO> listPasswords() {
        log.debug("Retrieving list of passwords");
        return StreamSupport.stream(passwordRepository.findAll().spliterator(),false)
                .map(mapper::passwordToPasswordDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public PasswordDTO getPasswordById(Long id) {
        Optional<Password> passwordOptional = passwordRepository.findById(id);
        if(passwordOptional.isPresent()){
            return mapper.passwordToPasswordDTO(passwordOptional.get());
        }
        return null;
    }

    @Override
    public PasswordDTO getPasswordBySite(String site) {
        Optional<Password> passwordOptional = passwordRepository.findBySiteName(site);
        if(passwordOptional.isPresent()){
            return mapper.passwordToPasswordDTO(passwordOptional.get());
        }
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
