package com.api_shopping.entities;

import com.api_shopping.dto.ShopDTO;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity(name = "shop")
public class Shop {
    private Long id;
    private String userIdentifier;
    private float total;
    private LocalDateTime date;

    private List<Item> items;

    public static Shop convertToShop(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setUserIdentifier(shopDTO.getUserIdentifier());
        shop.setTotal(shopDTO.getTotal());
        shop.setDate(shopDTO.getDate());
        shop.setItems(shopDTO.getItemDTOS().stream().map(Item::convertToItem).collect(Collectors.toList()));
        return shop;
    }
}