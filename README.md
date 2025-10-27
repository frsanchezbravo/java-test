# Test Java - Price Service

Microservicio en Spring Boot que proporciona un endpoint REST para consultar los precios aplicables a un producto de una cadena según la fecha de aplicación.

## Descripción

El servicio consulta la tabla `PRICES` de una base de datos H2 en memoria, que contiene los precios finales (`PRICE`) de productos para distintas tarifas (`PRICE_LIST`) y fechas (`START_DATE` / `END_DATE`).  
Si varias tarifas coinciden en un rango de fechas, se aplica la de **mayor prioridad** (`PRIORITY`).

### Tabla PRICES (ejemplo)

| BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE  | CURR |
|----------|-------------------|-------------------|-----------|-----------|---------|-------|------|
| 1        | 2020-06-14 00:00   | 2020-12-31 23:59   | 1         | 35455     | 0       | 35.50 | EUR  |
| 1        | 2020-06-14 15:00   | 2020-06-14 18:30   | 2         | 35455     | 1       | 25.45 | EUR  |
| 1        | 2020-06-15 00:00   | 2020-06-15 11:00   | 3         | 35455     | 1       | 30.50 | EUR  |
| 1        | 2020-06-15 16:00   | 2020-12-31 23:59   | 4         | 35455     | 1       | 38.95 | EUR  |

## Tecnologías

- Java 17
- Spring Boot
- Spring Web, Spring Data JPA, H2 Database
- JUnit 5, Mockito para tests
- Lombok 
- Maven

## Instalación y Ejecución

1. Clonar el repositorio:
   ```bash
   git clone <repo-url>
   cd test-java
