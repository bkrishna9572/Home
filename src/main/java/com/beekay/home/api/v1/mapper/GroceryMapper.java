package com.beekay.home.api.v1.mapper;

import com.beekay.home.api.v1.model.GroceryDTO;
import com.beekay.home.model.Grocery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroceryMapper {
    GroceryMapper INSTANCE = Mappers.getMapper(GroceryMapper.class);

    @Mapping(source = "id", target = "id")
    GroceryDTO groceryToGroceryDTO(Grocery grocery);

    Grocery groceryDTOToGrocery(GroceryDTO groceryDTO);
}
