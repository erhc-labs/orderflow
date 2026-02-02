package org.erhc.orderflow.adaptersoutmongo;


import org.erhc.orderflow.adaptersoutmongo.adapter.MongoOrderRepositoryAdapter;
import org.erhc.orderflow.core.domain.model.Order;
import org.erhc.orderflow.core.domain.model.OrderItem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;



import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(
        properties = {
                "spring.data.mongodb.uri=mongodb://localhost:27017/orderflow_test"
        }
)
public class MongoOrderRepositoryAdapterIT {

    @Autowired
    private MongoOrderRepositoryAdapter repository;

    @Test
    void shouldSaveAndFindOrderById(){

         // ===== Arrange =====
        Order order = new Order(
                "customer-test",
                List.of(
                        new OrderItem(
                                "product-1",
                                2,
                                BigDecimal.valueOf(10)
                        )
                )
        );

        String orderId = order.id().value();

         // ===== ACT =====
        repository.save(order);
        var result = repository.findById(orderId);

         // ===== Assert =====
        assertThat(result).isPresent();
        assertThat(result.get().customerId()).isEqualTo("customer-test");
        assertThat(result.get().items()).hasSize(1);
        assertThat(result.get().items().get(0).productId()).isEqualTo("product-1");


    }

}
