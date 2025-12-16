# Prompts de Docker

## Prompt 1: Configuración de Docker Compose para Bases de Datos

### Prompt Utilizado:
Genera un archivo `docker-compose.yml` para levantar dos bases de datos:
1. MySQL 8.4: base de datos `microservices_db`, usuario `microservices_user`, contraseña `microservices_pass`, puerto 3306.
2. PostgreSQL 16: misma base, usuario y contraseña, puerto 5432.
   Incluye healthchecks para ambos servicios, volúmenes persistentes para no perder datos al reiniciar, y una red compartida llamada `microservices_network`.

### Respuesta Recibida:
[Código YAML del docker-compose completo con los servicios mysql y postgres configurados según los requisitos]

### Modificaciones Realizadas:
- Se añadió explícitamente `MYSQL_ROOT_PASSWORD` que es obligatorio para la imagen oficial de MySQL.
- Se configuraron los timeouts de los healthchecks a 20s para dar tiempo suficiente al arranque en máquinas con menos recursos.

### Explicación del Prompt:
Necesitaba un entorno de bases de datos reproducible y aislado que no requiriera instalaciones locales complejas, facilitando que cualquier desarrollador (o el profesor) pueda ejecutar el proyecto con un simple `docker compose up`.

### Aprendizajes Obtenidos:
- La utilidad de los `healthchecks` para saber cuándo la base de datos está realmente lista para recibir conexiones.
- Cómo usar volúmenes nombrados (`mysql_data`) para persistencia de datos entre reinicios del contenedor.