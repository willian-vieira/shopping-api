package com.api_shopping.dto;

import com.api_shopping.entities.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private String productIdentifier;
    private Float price;

    public static ItemDTO convertToItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }
}