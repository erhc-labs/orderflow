package org.erhc.orderflow.core.application.service;

import org.erhc.orderflow.core.application.request.CreateOrderRequest;
import org.erhc.orderflow.core.domain.model.Order;
import org.erhc.orderflow.core.ports.out.AuditPort;
import org.erhc.orderflow.core.ports.out.OrderRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateOrderServiceTest {

    private OrderRepositoryPort repository;
    private CreateOrderService service;
    private AuditPort auditPort;
    private AuditService auditService;
    private AuditDiffCalculator diffCalculator;

    @BeforeEach
    void setUp(){
        repository = mock(OrderRepositoryPort.class);
        auditPort = mock(AuditPort.class);
        diffCalculator = new AuditDiffCalculator();
        auditService = new AuditService(auditPort,diffCalculator);
        service = new CreateOrderService(repository,auditService);
    }

    @Test
    void shouldCreateOrderSuccessfully(){

         // ===== Arrange =====
        CreateOrderRequest request = new CreateOrderRequest(
                "customer-1",
                List.of(
                        new CreateOrderRequest.Item(
                                "product-1",
                                2,
                                BigDecimal.valueOf(10)
                        )
                )
        );

        ArgumentCaptor<Order> orderCaptor =
            ArgumentCaptor.forClass(Order.class);

         // ===== Act =====
        var orderId = service.create(request);

         // ===== Assert =====
        assertNotNull(orderId);

        verify(repository).save(orderCaptor.capture());

        Order savedOrder = orderCaptor.getValue();
        assertEquals("customer-1", savedOrder.customerId());
        assertEquals(1, savedOrder.items().size());
        assertEquals("product-1", savedOrder.items().get(0).productId());

    }

    @Test
    void shouldFailWhenOrderHasNoItems(){
        CreateOrderRequest request = new CreateOrderRequest(
                "customer-1",
                List.of()
        );
        assertThrows(IllegalArgumentException.class, () -> service.create(request));

        verify(repository, never()).save(any());
    }

    @Test
    void shouldFailWhenCustomerIdIsBlank(){

        CreateOrderRequest request = new CreateOrderRequest(
                "",
                List.of(
                        new CreateOrderRequest.Item(
                                "product-1",
                                2,
                                BigDecimal.valueOf(10)
                        )
                )
        );

        assertThrows(IllegalArgumentException.class,
                () -> service.create(request));

        verify(repository, never()).save(any());
    }

    @Test
    void shouldFailWhenQuantityIsZero(){

        CreateOrderRequest request = new CreateOrderRequest(
                "customer-1",
                List.of(
                        new CreateOrderRequest.Item(
                                "product-1",
                                0,
                                BigDecimal.TEN
                        )
                )
        );

        assertThrows(IllegalArgumentException.class, () -> service.create(request));

    }

    @Test
    void shouldFailWhenPriceIsNegative(){
        CreateOrderRequest request = new CreateOrderRequest(
                "customer-1",
                List.of(
                        new CreateOrderRequest.Item(
                                "product-1",
                                2,
                                BigDecimal.valueOf(-2)
                                )
                )
        );

     assertThrows(IllegalArgumentException.class, () -> service.create(request));

    }

    @Test
    void shouldFailWhenItemsIsNull(){

        CreateOrderRequest request = new CreateOrderRequest(
                "customer-1",
                null
        );
        assertThrows(NullPointerException.class, () -> service.create(request));

    }

    @Test
    void shouldSaveOrderOnlyOnce(){
        CreateOrderRequest request = new CreateOrderRequest(
                "customer-1",
                List.of(
                        new CreateOrderRequest.Item(
                                "product-1",
                                1,
                                BigDecimal.TEN
                        )
                )
        );

        service.create(request);

        verify(repository, times(1)).save(any());

    }

    @Test
    void shouldRegisterAuditWhenOrderIsCreated(){
        CreateOrderRequest request = new CreateOrderRequest(
                "customer-1",
                List.of(
                        new CreateOrderRequest.Item(
                                "product-1",
                                1,
                                BigDecimal.TEN
                        )
                )
        );
        service.create(request);
        verify(auditPort, times(1)).register(any());
    }

}
