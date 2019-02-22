package com.beekay.home.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groceries")
public class Grocery extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "quantity")
    @Enumerated( value = EnumType.STRING)
    private Quantity quantity;

    @Column(name = "amount")
    private BigDecimal amount;

    @Builder
    public Grocery(Long id, String name, Quantity quantity, BigDecimal amount) {
        super(id);
        this.name = name;
        this.quantity = quantity;
        this.amount = amount;
    }
}
