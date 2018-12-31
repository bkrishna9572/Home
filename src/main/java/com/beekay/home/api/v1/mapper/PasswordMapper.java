package com.beekay.home.api.v1.mapper;

import com.beekay.home.api.v1.model.PasswordDTO;
import com.beekay.home.model.Password;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PasswordMapper {
    PasswordMapper INSTANCE = Mappers.getMapper(PasswordMapper.class);

    PasswordDTO passwordToPasswordDTO(Password password);
}
