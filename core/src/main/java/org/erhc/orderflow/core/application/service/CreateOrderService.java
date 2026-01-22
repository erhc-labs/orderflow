package org.erhc.orderflow.core.application.service;

import org.erhc.orderflow.core.application.request.CreateOrderRequest;
import org.erhc.orderflow.core.domain.model.Order;
import org.erhc.orderflow.core.domain.model.OrderItem;
import org.erhc.orderflow.core.domain.valueobject.OrderId;
import org.erhc.orderflow.core.ports.in.CreateOrderUseCase;
import org.erhc.orderflow.core.ports.out.OrderRepositoryPort;

import java.util.List;


public class CreateOrderService implements CreateOrderUseCase {

    private final OrderRepositoryPort repository;
    private final AuditService auditService;

    public CreateOrderService(
            OrderRepositoryPort repository,
            AuditService auditService)
    {
        this.repository = repository;
        this.auditService = auditService;
    }


    @Override
    public OrderId create(CreateOrderRequest request) {

        List<OrderItem> items = request.items().stream()
                .map(i -> new OrderItem(
                        i.productId(),
                        i.quantity(),
                        i.price()
                )).toList();

        Order order = new Order(request.customerId(), items);
        repository.save(order);

        auditService.auditCreate(
                "Order",
                order.id().value(),
                "system"
        );

        return order.id();
    }
}
