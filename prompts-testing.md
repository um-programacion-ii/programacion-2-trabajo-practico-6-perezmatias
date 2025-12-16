# Prompts de Testing

## Prompt 1: Tests Unitarios con Mockito

### Prompt Utilizado:
Genera un test unitario para la clase `ProductoBusinessService` usando JUnit 5 y Mockito.
Necesito probar dos escenarios:
1. `obtenerTodosLosProductos`: debe llamar al `DataServiceClient` y retornar la lista simulada.
2. `crearProducto`: al recibir un request con precio negativo (-10), debe lanzar `IllegalArgumentException` y verificar que NO se llame al `DataServiceClient` (usando `verify(mock, never())`).

### Respuesta Recibida:
[Código de la clase `ProductoBusinessServiceTest` con las anotaciones `@ExtendWith(MockitoExtension.class)`, `@Mock` e `@InjectMocks`]

### Modificaciones Realizadas:
- Ninguna significativa, el código generado cumplía con las expectativas de aislamiento de la prueba.

### Explicación del Prompt:
Se buscaba validar la lógica de negocio pura (reglas if/else) aislándola de dependencias externas como la red o la base de datos, lo cual es la definición de un test unitario rápido y fiable.

### Aprendizajes Obtenidos:
- Cómo inyectar dependencias simuladas con `@InjectMocks` automáticamente.
- La importancia de verificar no solo lo que retorna un método, sino también los "efectos secundarios" (o la ausencia de ellos) usando `verify`.

---

## Prompt 2: Tests de Integración con TestRestTemplate

### Prompt Utilizado:
Genera un test de integración para `DataController` usando `@SpringBootTest` con `webEnvironment = RANDOM_PORT`.
Usa `TestRestTemplate` para hacer una petición POST real al endpoint `/data/productos`.
Verifica que la respuesta sea 201 Created y que el cuerpo de la respuesta contenga el ID generado y el nombre correcto.
Asegúrate de usar el perfil "dev" (`@ActiveProfiles("dev")`) para usar la base de datos H2.

### Respuesta Recibida:
[Código de la clase `DataControllerIntegrationTest`]

### Modificaciones Realizadas:
- Se ajustó la ruta del endpoint a `/data/productos` para coincidir con el RequestMapping del controlador.

### Explicación del Prompt:
Necesitaba verificar que todo el flujo del microservicio de datos (Controller -> Service -> Repository -> H2 DB) funcionara en conjunto correctamente en un entorno controlado.

### Aprendizajes Obtenidos:
- Diferencia entre test unitario (aislado) y de integración (contexto completo de Spring).
- Uso de `webEnvironment = RANDOM_PORT` para evitar conflictos de puertos durante los tests.