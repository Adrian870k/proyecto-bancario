# Proyecto Bancario

Este es un proyecto de demostración de una aplicación bancaria construida con una arquitectura de microservicios. La solución utiliza Spring Boot para los servicios, MySQL para la persistencia de datos y RabbitMQ para la comunicación asíncrona entre servicios.

Todo el entorno está contenedorizado con Docker y orquestado con Docker Compose.

---

## 🚀 Arquitectura de la Solución

La solución se compone de 4 contenedores principales:

* **`servicio-clientes` (Puerto: 8081):** Microservicio Spring Boot encargado de gestionar el CRUD (Crear, Leer, Actualizar, Eliminar) de Clientes y Personas.
* **`servicio-cuentas` (Puerto: 8082):** Microservicio Spring Boot que maneja la lógica de Cuentas, Movimientos y la generación de Reportes.
* **`mysql-db` (Puerto: 3307):** Contenedor de base de datos MySQL 8.0. Persiste la información de ambos servicios en bases de datos separadas (`db_clientes` y `db_cuentas`).
* **`rabbitmq` (Puertos: 5672, 15672):** Broker de mensajería que maneja la comunicación de eventos (ej. cuando un cliente es creado) entre los servicios.

---

## 🛠️ Tecnologías Utilizadas

* Java 17
* Spring Boot 3
* Maven
* Spring Data JPA
* MySQL 8.0
* RabbitMQ
* Docker y Docker Compose

---

## 📋 Requisitos Previos

* [Docker](https://www.docker.com/products/docker-desktop/)
* [Docker Compose](https://docs.docker.com/compose/install/)
* Un cliente API como [Postman](https://www.postman.com/)
* Un cliente de Base de Datos como [DBeaver](https://dbeaver.io/) o MySQL Workbench

---

## ▶️ Cómo Desplegar y Ejecutar

El proyecto está diseñado para levantarse con un solo comando.

1.  Abre una terminal en la raíz del proyecto (donde se encuentra el archivo `docker-compose.yml`).
2.  **Construye las imágenes** de los microservicios:
    ```bash
    docker-compose build
    ```
3.  **Levanta todo el entorno** (en modo detached):
    ```bash
    docker-compose up -d
    ```

El sistema completo (4 contenedores) estará corriendo y listo para recibir peticiones.

---

## 🧪 Cómo Probar la Solución

Puedes interactuar con todos los componentes del sistema a través de los siguientes puntos de acceso:

### Servicios API (Postman)

* **Servicio Clientes:** `http://localhost:8081`
* **Servicio Cuentas:** `http://localhost:8082`

#### Flujo de Pruebas Recomendado:

1.  **(POST)** `http://localhost:8081/clientes` - Crea un nuevo cliente.
2.  **(POST)** `http://localhost:8082/cuentas` - Crea una nueva cuenta usando el `clienteId` del paso 1.
3.  **(POST)** `http://localhost:8082/movimientos` - Realiza un depósito (valor positivo) en la cuenta.
4.  **(POST)** `http://localhost:8082/movimientos` - Intenta un retiro (valor negativo) que supere el saldo para probar la excepción "Saldo no disponible".
5.  **(GET)** `http://localhost:8082/reportes?fechaInicio=...&fechaFin=...&cliente=1` - Genera un reporte para ver el estado de cuenta.

### Herramientas de Soporte (Navegador)

* **Admin de RabbitMQ:**
    * **URL:** `http://localhost:15672`
    * **Usuario:** `guest`
    * **Contraseña:** `guest`
    * *(Aquí puedes ver el `banco_eventos_exchange` y la `cuenta_cliente_eventos_queue`)*

* **Base de Datos MySQL:**
    * **Host:** `localhost`
    * **Puerto:** `3307`
    * **Usuario:** `root`
    * **Contraseña:** `rootpassword123`
    * *(Conéctate con tu cliente de DB para ver las tablas en `db_clientes` y `db_cuentas`)*

### Cómo Detener la Aplicación

Para detener y eliminar todos los contenedores y redes:

```bash
docker-compose down
```