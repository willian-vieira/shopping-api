package com.api_shopping.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ShopDTO {
    private String userIdentifier;
    private float total;
    private LocalDateTime date;
}