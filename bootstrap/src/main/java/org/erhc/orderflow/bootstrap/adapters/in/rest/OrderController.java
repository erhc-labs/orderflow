package org.erhc.orderflow.bootstrap.adapters.in.rest;

import org.erhc.orderflow.core.application.request.CreateOrderRequest;
import org.erhc.orderflow.core.domain.valueobject.OrderId;
import org.erhc.orderflow.core.ports.in.CreateOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<String> create(
            @RequestBody CreateOrderRequest request
    ){
        OrderId id = createOrderUseCase.create(request);
        return ResponseEntity.ok(id.value());
    }

}
