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