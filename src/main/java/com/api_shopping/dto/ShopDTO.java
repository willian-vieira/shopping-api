package com.api_shopping.dto;

import com.api_shopping.entities.Shop;
import java.time.LocalDateTime;
import java.util.List;

public class ShopDTO {
    private String userIdentifier;
    private float total;
    private LocalDateTime date;

    private List<ItemDTO> itemDTOS;

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<ItemDTO> getItemDTOS() {
        return itemDTOS;
    }

    public static ShopDTO convertToShopDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        return shopDTO;
    }
}