package org.erhc.orderflow.bootstrap.adapters.out.repository;

import org.erhc.orderflow.core.domain.model.Order;
import org.erhc.orderflow.core.ports.out.OrderRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryOrderRepository implements OrderRepositoryPort {

    private final List<Order> store = new ArrayList<>();

    @Override
    public void save(Order order) {
        store.add(order);
        System.out.println("Order saved: " + order.id().value());
    }
}
