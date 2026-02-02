package org.erhc.orderflow.core.ports.out;

import org.erhc.orderflow.core.domain.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {

    void save(Order order);

    Optional<Order> findById(String id);

    List<Order> findAll();

}
