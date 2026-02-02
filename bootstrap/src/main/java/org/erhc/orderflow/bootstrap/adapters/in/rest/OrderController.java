package org.erhc.orderflow.bootstrap.adapters.in.rest;

import org.erhc.orderflow.bootstrap.adapters.in.rest.dto.OrderResponse;
import org.erhc.orderflow.bootstrap.adapters.in.rest.mapper.OrderRestMapper;
import org.erhc.orderflow.core.application.request.CreateOrderRequest;
import org.erhc.orderflow.core.domain.model.Order;
import org.erhc.orderflow.core.domain.valueobject.OrderId;
import org.erhc.orderflow.core.ports.in.CreateOrderUseCase;
import org.erhc.orderflow.core.ports.in.ListOrdersUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final ListOrdersUseCase listOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, ListOrdersUseCase listOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.listOrderUseCase = listOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<String> create(
            @RequestBody CreateOrderRequest request
    ){
        OrderId id = createOrderUseCase.create(request);
        return ResponseEntity.ok(id.value());
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> list(){
        return ResponseEntity.ok(
                listOrderUseCase.list().stream()
                        .map(OrderRestMapper::toResponse)
                        .toList()
        );
    }

}
