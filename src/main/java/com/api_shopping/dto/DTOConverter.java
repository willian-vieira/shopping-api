package com.api_shopping.dto;

import com.api_shopping.entities.Item;
import com.api_shopping.entities.Shop;

public class DTOConverter {
    public static ItemDTO convert(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItemDTOS(
            shop.getItems()
                .stream()
                .map(DTOConverter::convert).toList()
        );
        return shopDTO;
    }
}