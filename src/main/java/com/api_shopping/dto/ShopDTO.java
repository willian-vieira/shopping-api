package com.api_shopping.dto;

import com.api_shopping.entities.Shop;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ShopDTO {
    private String userIdentifier;
    private float total;
    private LocalDateTime date;

    private List<ItemDTO> itemDTOS;

    public static ShopDTO convertToShopDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        return shopDTO;
    }
}