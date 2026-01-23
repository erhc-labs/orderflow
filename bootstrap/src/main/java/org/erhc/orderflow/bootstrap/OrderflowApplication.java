package org.erhc.orderflow.bootstrap;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ... existing code ...
@SpringBootApplication(
        scanBasePackages = "org.erhc.orderflow"
)
public class OrderflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderflowApplication.class, args);
    }
}
// ... existing code ...