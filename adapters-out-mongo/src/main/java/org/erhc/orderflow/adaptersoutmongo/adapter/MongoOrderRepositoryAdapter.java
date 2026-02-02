package org.erhc.orderflow.adaptersoutmongo.adapter;

import org.erhc.orderflow.adaptersoutmongo.model.OrderDocument;
import org.erhc.orderflow.adaptersoutmongo.repository.MongoOrderSpringRepository;
import org.erhc.orderflow.core.domain.model.Order;
import org.erhc.orderflow.core.ports.out.OrderRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MongoOrderRepositoryAdapter implements OrderRepositoryPort {


    private final MongoOrderSpringRepository mongoOrderSpringRepository;

    public MongoOrderRepositoryAdapter(MongoOrderSpringRepository mongoOrderSpringRepository) {
        this.mongoOrderSpringRepository = mongoOrderSpringRepository;
    }


    @Override
    public void save(Order order) {
        System.out.println("🔥 GUARDANDO EN MONGO: " + order.id());
        OrderDocument doc = new OrderDocument();
        doc.setId(order.id().value());
        doc.setCustomerId(order.customerId());

        doc.setItems(
                order.items().stream().map(item -> {
                    OrderDocument.Item i = new OrderDocument.Item();
                    i.productId = item.productId();
                    i.quantity = item.quantity();
                    i.price = item.price();
                    return i;
                }).collect(Collectors.toList())
        );
        mongoOrderSpringRepository.save(doc);
    }

     // ===== GET BY ID =====
    @Override
    public Optional<Order> findById(String id) {
        return mongoOrderSpringRepository.findById(id)
                .map(OrderDocument::toDomain);
    }

     // ===== List =====
    @Override
    public List<Order> findAll() {
        return mongoOrderSpringRepository.findAll()
                .stream()
                .map(OrderDocument::toDomain)
                .toList();
    }
}
