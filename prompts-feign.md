# Prompts de Feign Client

## Prompt 1: Implementación de Cliente Feign y DTOs

### Prompt Utilizado:
Estoy desarrollando el "business-service" que debe comunicarse con el "data-service".
1. Crea los DTOs necesarios (ProductoDTO, CategoriaDTO, InventarioDTO) para mapear las respuestas JSON.
2. Crea una interfaz `DataServiceClient` usando `@FeignClient`.
3. Mapea los métodos de la interfaz a los endpoints del `DataController` que creamos antes (/data/productos, etc.).
4. Asegúrate de que la URL del cliente se tome de la propiedad `${data.service.url}`.

### Respuesta Recibida:
[Se generaron las clases DTO con Lombok y la interfaz DataServiceClient con las anotaciones de mapeo HTTP correctas (@GetMapping, @PostMapping)]

### Modificaciones Realizadas:
- Se añadió la clase `ProductoRequest` para separar la estructura de lectura de la de escritura, aunque inicialmente comparten campos similares.
- Se verificó que los `@PathVariable` tengan el nombre del parámetro explícito (ej: `@PathVariable("id")`) para evitar problemas de compilación.

### Explicación del Prompt:
Se necesitaba una forma declarativa de consumir la API REST del microservicio de datos sin escribir código repetitivo con `RestTemplate` o `WebClient`. Feign automatiza esto basándose en interfaces.

### Aprendizajes Obtenidos:
- Cómo Feign actúa como un proxy dinámico implementando la interfaz en tiempo de ejecución.
- La importancia de mantener la consistencia entre los DTOs del cliente y las Entidades/DTOs del servidor.