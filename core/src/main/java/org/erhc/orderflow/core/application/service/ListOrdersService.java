package org.erhc.orderflow.core.application.service;

import org.erhc.orderflow.core.domain.model.Order;
import org.erhc.orderflow.core.ports.in.ListOrdersUseCase;
import org.erhc.orderflow.core.ports.out.OrderRepositoryPort;

import java.util.List;

public class ListOrdersService implements ListOrdersUseCase {

    private final OrderRepositoryPort repository;

    public ListOrdersService(OrderRepositoryPort repository) {
        this.repository = repository;
    }


    @Override
    public List<Order> list() {
        return repository.findAll();
    }
}
