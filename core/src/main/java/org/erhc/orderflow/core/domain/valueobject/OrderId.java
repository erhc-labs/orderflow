package org.erhc.orderflow.core.domain.valueobject;

import java.util.UUID;

public record OrderId(String value) {

    public OrderId {
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException("Order Id cannot be null or empty");
        }
    }

    public static OrderId newId(){
        return new OrderId(UUID.randomUUID().toString());
    }
}
