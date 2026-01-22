package org.erhc.orderflow.core.domain.model;

import java.math.BigDecimal;

public record OrderItem(
        String productId,
        int quantity,
        BigDecimal price
) {

    public OrderItem {

        if(productId  == null || productId.isBlank()){
            throw new IllegalArgumentException("productId is required");
        }
        if(quantity <= 0){
            throw new IllegalArgumentException("quantity must be greater than zero");
        }
        if(price == null ||  price.signum() < 0){
            throw new IllegalArgumentException("price must be >= 0");
        }
    }
}
