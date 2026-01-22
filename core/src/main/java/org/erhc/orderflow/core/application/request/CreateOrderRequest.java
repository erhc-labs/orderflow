package org.erhc.orderflow.core.application.request;



import java.math.BigDecimal;
import java.util.List;

public record CreateOrderRequest(
        String customerId,
        List<Item> items
) {
    public record Item(
            String productId,
            int quantity,
            BigDecimal price
    ) {}
}
