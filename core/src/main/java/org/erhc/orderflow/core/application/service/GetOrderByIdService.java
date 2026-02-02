package org.erhc.orderflow.core.application.service;

import org.erhc.orderflow.core.domain.model.Order;
import org.erhc.orderflow.core.ports.in.GetOrderByIdUseCase;
import org.erhc.orderflow.core.ports.out.OrderRepositoryPort;

public class GetOrderByIdService implements GetOrderByIdUseCase {

    private final OrderRepositoryPort repository;

    public GetOrderByIdService(OrderRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Order getById(String orderId) {
        return repository.findById(orderId)
                .orElseThrow(()-> new IllegalArgumentException("Order not found: " + orderId));
    }

}
