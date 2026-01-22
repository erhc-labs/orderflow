package org.erhc.orderflow.core.application.service;

import org.erhc.orderflow.core.domain.model.AuditChange;
import org.erhc.orderflow.core.domain.model.Order;
import org.erhc.orderflow.core.domain.model.OrderItem;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class AuditDiffCalculatorTest {

    @Test
    void shouldDetectStatusChange(){

        Order before = new Order(
                "customer-1",
                List.of(new OrderItem("p1",1, BigDecimal.TEN))
        );

        Order after = new Order(
                "customer-1",
                List.of(new OrderItem("p1",21, BigDecimal.TEN))
        );

        after.markAsPaid();

        AuditDiffCalculator calculator = new AuditDiffCalculator();
        List<AuditChange> changes =
                calculator.calculate(before, after);

        assertTrue(
                changes.stream().anyMatch(c ->
                        c.field().equals("status") &&
                                c.oldValue().equals("CREATED") &&
                                c.newValue().equals("PAID")
                )
        );
    }

}
