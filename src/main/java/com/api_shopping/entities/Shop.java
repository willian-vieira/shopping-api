package com.api_shopping.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "shop")
public class Shop {
    private Long id;
    private String userIdentifier;
    private float total;
    private LocalDateTime date;
}