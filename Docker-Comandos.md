# Docker - Guía Rápida de Comandos (OrderFlow)

Este documento resume los comandos Docker más usados, qué hacen y cuándo usarlos al trabajar con el proyecto OrderFlow.

---

## Recordatorio de Arquitectura
````
core                → No se ejecuta
adapters            → No se ejecuta
bootstrap           → Único ejecutable  (Spring boot)
docker-compose.yml  → Intraestructura (Mongo -App)
````
Solo docker levanta la aplicación.

---
### Paso previo obligatorio
Antes de usar Docker, generar el JAR ejecutable
````
mvn clean package -DskipTests 
````
Genera
``
bootstrap/target/bootstrap-*.jar``
---

### Docker Compose 

- Levantar todo (Mongo + App)
````
docker compose up -d
````
Inicia todos los servicios definidos en ``docker-compose.yml``

---

- Levantar solo MongoDB
````
docker compose up -d mongo
````
Útil cuando solo necesitas la base de datos

---

- Construir la imagen de la aplicación
````
docker compose build orderflow-app
````
Crea la imagen Docker usando el ``Dockerfile`` del módulo ``bootstrap``

---
- Construir SIN cache (recomendado tras cambio de código)
````
docker compose build --no-cache orderflow-app
````
Fuerza a Docker a reconstruir todo desde cero.

---
- Levantar solo la aplicación
````
docker compose up -d orderflow-app
````
Inicia únicamente el servicio de la app (Mongo debe estar activo).

---

## Verificacion y monitoreo
- Ver contenedores activos
````
docker ps
````
Muestra qué contenedores están corriendo.

---
- Ver logs de la aplicación
````
docker logs orderflow-app
````
Útil para verificar que Spring Boot arrancó correctamente.

---
- Ver logs de MongoDB
````
docker logs orderflow-mongo
````
---
- Se logs en tiempo real
````
docker logs -f orderflow-app
````
Ideal para debugging en caliente

---
##  Probar la aplicación
Probar desde terminal
````
curl http://localhost:8080/orders
````
---
Probar desde navegador o Postman
````
http://localhost:8080/orders
````
---
## 🛑 Detener servicios
- Detener todos los contenedores
````
docker compose down
````
Apaga los servicios, mantiene los datos.

---

- Detener y borrar volúmenes (borra datos)
````
docker compose down -v
````
Elimina también MongoDB y sus datos.

---

## 🧹 Limpieza (cuando Docker se ensucia)
- Eliminar contenedores detenidos
````
docker container prune
````
---

- Eliminar imágenes no usadas
````
docker image prune
````

---
-  Limpieza total (Cuidado)
````
docker system prune -a
````