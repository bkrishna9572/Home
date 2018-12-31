package com.beekay.home.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {

    private Long id;
    private String siteName;
    private String userName;
    private String pass;
}
