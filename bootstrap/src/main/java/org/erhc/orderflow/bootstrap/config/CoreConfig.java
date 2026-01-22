package org.erhc.orderflow.bootstrap.config;

import org.erhc.orderflow.core.application.service.AuditDiffCalculator;
import org.erhc.orderflow.core.application.service.AuditService;
import org.erhc.orderflow.core.application.service.CreateOrderService;
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

}
