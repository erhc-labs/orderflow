package org.erhc.orderflow.core.ports.in;

import org.erhc.orderflow.core.domain.model.Order;

import java.util.List;

public interface ListOrdersUseCase {
    List<Order> list();
}
