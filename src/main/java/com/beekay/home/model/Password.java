package com.beekay.home.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Password extends BaseEntity {

    private String siteName;

    private String userName;

    private String pass;

}
