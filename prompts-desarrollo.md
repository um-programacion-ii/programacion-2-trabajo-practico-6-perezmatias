# Prompts de Desarrollo

## Prompt 1: Configuración Inicial Data Service

### Prompt Utilizado:
Necesito configurar el archivo pom.xml para un microservicio llamado "data-service" utilizando Spring Boot 3.5.0 y Java 21. Debe incluir dependencias para Spring Data JPA, Spring Web, Validation, Lombok, y los drivers para H2, MySQL y PostgreSQL. También necesito la clase principal de la aplicación y un application.yml configurado para un perfil "dev" con base de datos H2 en memoria en el puerto 8081.

### Respuesta Recibida:
[Se generó el código XML para pom.xml con las dependencias solicitadas y el archivo YAML con configuración de perfiles]

### Modificaciones Realizadas:
- Se verificó manualmente que la versión de Spring Cloud sea la 2025.0.0.
- Se ajustó el ddl-auto a 'update' para facilitar el desarrollo inicial.

### Explicación del Prompt:
Se utilizó este prompt para establecer la base del proyecto siguiendo los requisitos estrictos de versiones del README y preparar el entorno para manejar múltiples bases de datos en el futuro.

### Aprendizajes Obtenidos:
- Cómo gestionar dependencias de múltiples bases de datos en un mismo proyecto Spring Boot.
- La utilidad de separar configuraciones por perfiles (dev, mysql, postgres) en un solo archivo YAML usando '---'.

---

## Prompt 2: Creación de Entidades JPA

### Prompt Utilizado:
Necesito implementar las entidades JPA para el microservicio de datos siguiendo el modelo relacional.
1. Entidad "Categoria": id, nombre (único), descripcion, y relación OneToMany con productos.
2. Entidad "Producto": id, nombre, descripcion, precio (BigDecimal), relación ManyToOne con Categoria y OneToOne con Inventario.
3. Entidad "Inventario": id, cantidad, stockMinimo, fechaActualizacion, y relación OneToOne con Producto.
   Usa anotaciones de Lombok (@Data, @NoArgsConstructor, @AllArgsConstructor) y Jakarta Persistence.

### Respuesta Recibida:
[Se generó el código Java para las tres clases: Categoria, Producto e Inventario con las anotaciones JPA correctas]

### Modificaciones Realizadas:
- Se añadió un método @PrePersist en la entidad Inventario para asegurar que la fecha de actualización se establezca automáticamente.
- Se verificó que la precisión del precio en Producto fuera correcta (precision=10, scale=2).

### Explicación del Prompt:
El prompt se diseñó para trasladar los requisitos de base de datos del README a clases Java funcionales, asegurando que las relaciones entre tablas (Foreign Keys) se mapeen correctamente con JPA.

### Aprendizajes Obtenidos:
- Diferencia entre @OneToMany y @ManyToOne y cómo el atributo 'mappedBy' define quién es el dueño de la relación.
- Uso de CascadeType.ALL para propagar operaciones (si borro un producto, se borra su inventario).

---

## Prompt 3: Implementación de Repositorios y Servicios

### Prompt Utilizado:
Implementa la capa de acceso a datos y lógica de negocio para el microservicio de datos.
1. Crea interfaces JpaRepository para Categoria, Producto e Inventario.
2. Agrega métodos personalizados: buscar producto por nombre de categoría, buscar inventario por ID de producto, y buscar inventario con stock bajo (cantidad <= stockMinimo).
3. Implementa clases @Service transaccionales que utilicen estos repositorios para realizar operaciones CRUD básicas.
4. Asegúrate de manejar la relación bidireccional entre Producto e Inventario al guardar.

### Respuesta Recibida:
[Código Java generado para los 3 repositorios y los 3 servicios con la lógica solicitada]

### Modificaciones Realizadas:
- Se añadió lógica manual en `ProductoService.actualizar` para manejar correctamente la actualización de la entidad anidada Inventario sin romper la relación JPA.
- Se utilizó `@Query` personalizado en InventarioRepository para la lógica de stock bajo.

### Explicación del Prompt:
Se solicitó la creación de la capa intermedia que conecta los controladores (que haremos después) con la base de datos, encapsulando las consultas específicas requeridas en los casos de uso del TP (como el reporte de stock bajo).

### Aprendizajes Obtenidos:
- Importancia de `@Transactional` para asegurar la integridad de datos.
- Cómo usar "Derived Query Methods" de Spring Data (ej: `findByCategoriaNombre`) para evitar escribir SQL manualmente.

---

## Prompt 4: Implementación de Controlador REST

### Prompt Utilizado:
Crea una clase `DataController` anotada con `@RestController` y `@RequestMapping("/data")` para exponer los servicios de datos.
Necesito los siguientes endpoints para cumplir con los diagramas de secuencia:
1. CRUD completo de Productos (GET, POST, PUT, DELETE).
2. GET para obtener productos por nombre de categoría.
3. GET para listar todas las categorías y POST para crear nuevas.
4. GET para obtener inventario con stock bajo y por ID de producto.
   Usa `ResponseEntity` para manejar los códigos de estado HTTP correctamente (200 OK, 201 Created, 204 No Content).

### Respuesta Recibida:
[Código Java del DataController con todos los endpoints mapeados a los servicios correspondientes]

### Modificaciones Realizadas:
- Se agregaron anotaciones `@Valid` en los métodos de creación y actualización para asegurar que las validaciones de las entidades funcionen.
- Se unificó todo en un solo controlador `/data` para simplificar la configuración del Feign Client más adelante, tal como se muestra en el ejemplo del README.

### Explicación del Prompt:
Se solicitó la capa de interfaz HTTP que permite que otros microservicios (como business-service) interactúen con la base de datos sin conocer los detalles de JPA, cumpliendo con la arquitectura de microservicios.

### Aprendizajes Obtenidos:
- Uso de `ResponseEntity` para tener control total sobre la respuesta HTTP.
- Mapeo de verbos HTTP (GET, POST, PUT, DELETE) a operaciones CRUD.
- Importancia de definir bien las rutas (paths) para que sean intuitivas y RESTful.