package org.erhc.orderflow.core.ports.in;

import org.erhc.orderflow.core.domain.model.Order;

public interface GetOrderByIdUseCase {

    Order getById(String orderId);
}
