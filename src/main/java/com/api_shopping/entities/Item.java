package com.api_shopping.entities;

import com.api_shopping.dto.ItemDTO;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Item {
    private String productIdentifier;
    private Float price;

    public static Item convertToItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setProductIdentifier(itemDTO.getProductIdentifier());
        item.setPrice(itemDTO.getPrice());
        return item;
    }
}