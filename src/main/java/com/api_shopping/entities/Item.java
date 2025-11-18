package com.api_shopping.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Item {
    private String productIdentifier;
    private Float price;
}