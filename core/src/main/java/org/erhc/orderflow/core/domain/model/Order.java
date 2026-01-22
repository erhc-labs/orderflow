package org.erhc.orderflow.core.domain.model;

import org.erhc.orderflow.core.domain.valueobject.OrderId;

import java.util.List;

public class Order {

    private final OrderId id;
    private final String customerId;
    private final List<OrderItem> items;
    private OrderStatus status;

    public Order(String customerId, List<OrderItem> items){
        if(customerId == null || customerId.isBlank()){
            throw new IllegalArgumentException("customerId is required");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }

        this.id = OrderId.newId();
        this.customerId = customerId;
        this.items = List.copyOf(items);
        this.status = OrderStatus.CREATED;
    }

    public OrderId id(){
        return id;
    }

    public String customerId(){
        return customerId;
    }

    public List<OrderItem> items(){
        return items;
    }

    public OrderStatus status(){
        return status;
    }

    public void markAsPaid(){
        if(status != OrderStatus.CREATED){
            throw new IllegalStateException("Only CREATED orders can be paid");
        }
        this.status = OrderStatus.PAID;
    }


}
