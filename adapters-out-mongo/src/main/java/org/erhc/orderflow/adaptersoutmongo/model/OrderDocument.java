package org.erhc.orderflow.adaptersoutmongo.model;


import org.erhc.orderflow.core.domain.model.Order;
import org.erhc.orderflow.core.domain.model.OrderItem;
import org.erhc.orderflow.core.domain.model.OrderStatus;
import org.erhc.orderflow.core.domain.valueobject.OrderId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "orders")
public class OrderDocument {

    @Id
    private String id;
    private String customerId;
    private List<Item> items;


    public static OrderDocument fromDomain(Order order){
        OrderDocument doc = new OrderDocument();
        doc.id = order.id().value();
        doc.customerId = order.customerId();
        doc.items = order.items().stream()
                .map(Item::fromDomain)
                .toList();
        return doc;
    }

    public Order toDomain(){
        return new Order(
                new OrderId(this.id),
                customerId,
                items.stream()
                        .map(Item::toDomain)
                        .toList(),
                OrderStatus.CREATED
        );
    }

    public static class Item {
        public String productId;
        public int quantity;
        public BigDecimal price;

        static Item fromDomain(OrderItem item){
            Item i = new Item();
            i.productId = item.productId();
            i.quantity = item.quantity();
            i.price = item.price();
            return i;
        }

        OrderItem toDomain(){
            return new OrderItem(productId, quantity, price);
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
