package com.beekay.home.model;

import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Grocery extends BaseEntity {

    private String name;

    private Quantity quantity;

    private BigDecimal amount;

    @Builder
    public Grocery(Long id, String name, Quantity quantity, BigDecimal amount) {
        super(id);
        this.name = name;
        this.quantity = quantity;
        this.amount = amount;
    }
}
