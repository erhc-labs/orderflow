package org.erhc.orderflow.core.ports.out;

import org.erhc.orderflow.core.domain.model.Order;

public interface OrderRepositoryPort {

    void save(Order order);

}
