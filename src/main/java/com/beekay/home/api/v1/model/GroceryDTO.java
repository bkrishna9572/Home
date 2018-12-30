package com.beekay.home.api.v1.model;

import com.beekay.home.model.Quantity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GroceryDTO {
    private Long id;
    private String name;
    private Quantity quantity;
    private BigDecimal amount;
}
