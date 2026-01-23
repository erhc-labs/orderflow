# OrderFlow - Sistema de Gestión de Órdenes usando arquitectura Hexagonal

### Descripción
OrderFlow es un sistema de gestión de órdenes diseñado con la arquitectura Hexagonal, también conocida como Clean Architecture. Esta arquitectura promueve la separación clara entre las capas del sistema, garantizando una alta cohesión y bajo acoplamiento entre componentes. OrderFlow está diseñado para ser escalable, flexible y fácil de mantener, lo que lo convierte en una excelente opción para empresas que necesitan un sistema robusto y adaptable para administrar sus órdenes de manera eficiente.

---
### Objetivo
El objetivo del proyecto es demostrar buenas **prácticas de diseño**, separación estricta de responsabilidades y una base sólida para evolucionar hacia persistencia real, eventos y seguridad.

---
## Objetivos del Proyecto
- Aplicar **Arquitectura Hexagonal**
- Mantener el **core totalmente desacoplado de framework**
- Facilitar pruebas unitarias puras en el dominio
- Permitir cambiar infraestructura (DB, mensajería) sin tocar el negocio
- Servir como **proyecto demostrativo**
---
## Arquitectura General
El sistema está dividido en **módulos Maven**, cada uno con una responsabilidad clara:

````
orderflow/
├── core/                   # Dominio y casos de uso (framework-agnostic)
├── adapters-in-rest/       # Entradas HTTP (REST)
├── adapters-out-mongo/     # Persistencia MongoDB
├── adapters-out-kafka/     # Publicación de eventos (auditoría)
├── bootstrap/              # Spring Boot (arranque y configuración)
└── pom.xml                 # Parent Maven

````
---
## Principios clave
- El core no depende de Spring
- Los puertos (interfaces) viven en el core
- Los adapters implementan exclusivamente los puertos
- ``bootstrap`` se encarga exclusivamente de:
  - Iniciar Spring Boot
  - Configurar beans
  - Ensamblar dependencias
---

## Módulos en detalle
``core``

Contiene:
  - Entidades de dominio
  - Value Objects
  - Casos de uso (Application Services)
  - Puerto de entrada y salida
  - Lógica de negocio pura
  - Pruebas unitarias con JUnit 5 y Mockito

No contiene:
- Anotaciones Spring
- Acceso a base de datos
- Infraestructura
---

``bootstrap``

- Único módulo ejecutable
- Contiene el ``main()``
- Configuración de beans (``@Configuration``)
- Ensambla core + adapters
- Expone el sistema al exterior

---

``adapters-in-rest``
- Controlador REST
- Traducción HTTP -> casos de uso
- No contiene lógica de negocio

---
``adapters-out-mongo``
- Implementación del puerto de persistencia
- Uso de Spring Data MongoDB
- Traducción dominio -> documento Mongo
---

``adapters-out-kafka``
- Implementación del puerto de auditoría
- Publicación de eventos
- Preparado para arquitectura orientas a eventos
---

## Flujo de una operación
Ejemplo: crear una orden
````
HTTP Request
   ↓
REST Controller (adapter-in)
   ↓
CreateOrderService (core)
   ↓
OrderRepositoryPort (core)
   ↓
MongoOrderRepository (adapter-out)
   ↓
MongoDB

````
La auditoría sigue el mismo patrón a través de su propio puerto.

---

## Ejecución del Proyecto
Requisitos:
- Java 21
- Maven 3.9
- Docker (opcional, para MongoDB)

Ejecutar la aplicación
Desde Intellij o línea de comandos:
````
mvn spring-boot:run -pl bootstrap
````
La aplicación se inicia en:
``http://localhost:8080``

---
## Pruebas
Las pruebas unitarias se ejecutan desde el módulo ``core``
``mvn test``
- No requieren Spring
- No requieren base de datos
- Validan reglas de negocio y casos de borde
---

## Tecnologías utilizadas
- Java 21
- Maven (multi-módulo)
- Spring Boot 3.x (solo en bootstrap)
- JUnit 5
- Mockito
- MongoDB
- Kafka
---
## Autor
Edwin HC
Backend Developer - Java

Este proyecto forma parte de mi portafolio técnico y está orientado a demostrar **criterios de diseño, buenas prácticas y código mantenible**.