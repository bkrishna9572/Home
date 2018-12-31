package com.beekay.home.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passwords")
public class Password extends BaseEntity {

    private String siteName;

    private String userName;

    private String pass;

    @Builder
    public Password(Long id, String siteName, String userName, String pass) {
        super(id);
        this.siteName = siteName;
        this.userName = userName;
        this.pass = pass;
    }
}
