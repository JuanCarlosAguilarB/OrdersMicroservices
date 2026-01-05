
---

# Arquitectura Event-Driven con Spring Boot WebFlux - Order microservices

## Descripción general

Este proyecto implementa una solución backend basada en **dos microservicios independientes**, desarrollados con **Java 17 y Spring Boot WebFlux**, que se comunican de forma **asíncrona mediante RabbitMQ**.

La solución sigue principios de **DDD (Domain-Driven Design)**, **arquitectura por capas** y **event-driven architecture**, priorizando claridad, robustez y facilidad de mantenimiento.

---

## Arquitectura general

```
┌──────────────────┐        RabbitMQ        ┌───────────────────┐
│  orders-service  │ ─── OrderCreated ───▶ │  audit-service    │
│  MySQL (R2DBC)   │                        │ MongoDB (Reactive)│
└──────────────────┘                        └───────────────────┘
```

### Componentes

* **orders-service**

  * Gestión de pedidos
  * Persistencia en MySQL (reactivo)
  * Publicación de eventos de dominio
* **audit-service**

  * Consumo de eventos
  * Auditoría en MongoDB
  * Tolerancia a reprocesos y duplicados
* **RabbitMQ**

  * Comunicación asíncrona
* **Docker Compose**

  * Ejecución local completa

---

## Microservicio: Orders Service

### Responsabilidad

* Crear y consultar pedidos
* Publicar el evento `OrderCreated` al crear un pedido exitosamente

### Tecnologías

* Spring Boot WebFlux
* R2DBC MySQL
* RabbitMQ

### API REST

#### Crear pedido

```
POST /api/v1/orders
```

#### Obtener pedido por id

```
GET /api/v1/orders/{id}
```

#### Listar pedidos (paginado)

```
GET /api/v1/orders?page=0&size=10
```

---

### Evento publicado: `OrderCreated`

El evento representa un **hecho del dominio**, no una intención, y sigue el contrato definido en la prueba técnica.

```json
{
  "eventId": "uuid",
  "eventType": "OrderCreated",
  "occurredAt": "2026-01-03T12:34:56Z",
  "order": {
    "id": 123,
    "customerEmail": "cliente@correo.com",
    "totalAmount": 150000.00,
    "currency": "COP",
    "status": "CREATED"
  }
}
```

📌 El evento es:

* Inmutable
* Autocontenido
* Independiente del modelo interno
* Versionable y auditable

---

## Microservicio: Audit Service

### Responsabilidad

* Consumir eventos `OrderCreated`
* Registrar auditoría en MongoDB
* Exponer API de consulta

### Tecnologías

* Spring Boot WebFlux
* MongoDB Reactive
* RabbitMQ

---

### Persistencia

Colección MongoDB: `order_audits`

Campos principales:

* `eventId`
* `eventType`
* `orderId`
* `occurredAt`
* `payloadHash` (para idempotencia)

**Idempotencia**

* Se calcula un `payloadHash` del mensaje
* Si el evento ya fue procesado, se ignora
* Permite reprocesos seguros

---

### API REST

#### Consultar auditorías por pedido

```
GET /api/v1/audits?orderId=123
```

#### Consultar auditoría por evento

```
GET /api/v1/audits/{eventId}
```

---

## 🐰 RabbitMQ

### Configuración

* Exchange: `orders.exchange`
* Routing key: `orders.created`
* Queue audit: `orders.created.audit.queue`
* Dead Letter Queue: `orders.created.audit.dlq`

### Estrategia de errores

* El consumidor no detiene el servicio ante fallos
* RabbitMQ gestiona reintentos
* Mensajes fallidos terminan en DLQ

---

## Ejecución local con Docker Compose

### Servicios incluidos

* MySQL
* MongoDB
* RabbitMQ (con consola de administración)
* orders-service
* audit-service

### Pasos para ejecutar

```bash
docker-compose up --build
```

### Puertos

* Orders API: `http://localhost:8080`
* Audit API: `http://localhost:8081`
* RabbitMQ Management: `http://localhost:15672`

---

##  Decisiones de diseño

* **WebFlux end-to-end** (no código bloqueante)
* **DDD sin sobreingeniería**
* **Eventos de dominio en pasado**
* **Separación estricta de capas**
* **Application Service sin semántica HTTP**
* **Contratos de eventos independientes**
* **Idempotencia y tolerancia a reprocesos**

---

##  Pruebas

El diseño permite:

* Tests unitarios de dominio
* Tests del Application Service sin infraestructura
* Tests de consumidor Rabbit con eventos simulados

---
