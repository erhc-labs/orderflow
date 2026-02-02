package org.erhc.orderflow.bootstrap.adapters.in.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        String id,
        String customerId,
        String status,
        List<Item> items
) {

    public record Item(
            String productId,
            int quantity,
            BigDecimal price
    ){}

}
