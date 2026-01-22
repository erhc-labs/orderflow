package org.erhc.orderflow.core.ports.in;

import org.erhc.orderflow.core.application.request.CreateOrderRequest;
import org.erhc.orderflow.core.domain.valueobject.OrderId;

public interface CreateOrderUseCase {

    OrderId create(CreateOrderRequest request);
}
