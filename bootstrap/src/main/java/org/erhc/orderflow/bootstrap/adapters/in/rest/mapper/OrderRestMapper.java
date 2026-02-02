package org.erhc.orderflow.bootstrap.adapters.in.rest.mapper;

import org.erhc.orderflow.bootstrap.adapters.in.rest.dto.OrderResponse;
import org.erhc.orderflow.core.domain.model.Order;

public class OrderRestMapper {

    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.id().value(),
                order.customerId(),
                order.status().name(),
                order.items().stream()
                        .map(i -> new OrderResponse.Item(
                                i.productId(),
                                i.quantity(),
                                i.price()
                        )).toList()
        );
    }
}
