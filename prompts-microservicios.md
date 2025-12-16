# Prompts de Microservicios

## Prompt 1: Configuración de Business Service con Feign

### Prompt Utilizado:
Necesito configurar un nuevo microservicio llamado "business-service".
1. Genera un pom.xml con Spring Boot 3.5.0, Java 21 y la dependencia `spring-cloud-starter-openfeign`.
2. Crea la clase principal anotada con `@EnableFeignClients`.
3. Configura el application.yml para correr en el puerto 8080 y define una propiedad personalizada `data.service.url` apuntando a `http://localhost:8081` (donde corre el data-service).

### Respuesta Recibida:
[Se generó el pom.xml con las dependencias de Cloud OpenFeign y la configuración básica del servicio]

### Modificaciones Realizadas:
- Se agregaron timeouts de conexión en la configuración de Feign para evitar bloqueos indefinidos si el data-service no responde.

### Explicación del Prompt:
Este prompt establece la base del segundo microservicio, asegurando que tenga las bibliotecas necesarias para actuar como cliente HTTP (consumidor) del primer servicio.

### Aprendizajes Obtenidos:
- La importancia de la anotación `@EnableFeignClients` en la clase principal.
- Cómo externalizar URLs en el `application.yml` para no tener direcciones IP o puertos "duros" en el código Java.