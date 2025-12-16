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