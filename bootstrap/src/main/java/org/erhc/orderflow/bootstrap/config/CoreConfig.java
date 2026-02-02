package org.erhc.orderflow.bootstrap.config;

import org.erhc.orderflow.core.application.service.*;
import org.erhc.orderflow.core.ports.out.AuditPort;
import org.erhc.orderflow.core.ports.out.OrderRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    @Bean
    public AuditDiffCalculator auditDiffCalculator(){
        return new AuditDiffCalculator();
    }

    @Bean
    public AuditService auditService(
           AuditPort auditPort,
           AuditDiffCalculator diffCalculator
    ){
        return new AuditService(auditPort,diffCalculator);
    }

    @Bean
    public CreateOrderService createOrderService(
            OrderRepositoryPort repository,
            AuditService auditService
    ) {
        return new CreateOrderService(repository, auditService);
    }

    @Bean
    public GetOrderByIdService getOrderByIdService(OrderRepositoryPort repository){
        return new GetOrderByIdService(repository);
    }

    @Bean
    public ListOrdersService listOrdersService(OrderRepositoryPort repository){
        return new ListOrdersService(repository);
    }

}
