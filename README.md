# Taller Evaluativo 2025-2

📌 **Proyecto Maven con Spring Boot**  
Este repositorio implementa la solución al Taller Evaluativo siguiendo principios **SOLID**, pruebas unitarias con **JUnit 5**, cobertura con **JaCoCo (≥ 80%)**, e integración con **Sonar**.

#### Integrantes

- Tomas Felipe Ramirez Alvarez

-------------------------------------------------------------------------------------
## 📖 Descripción del taller

### 1. Crear un repositorio en GitHub con un proyecto **Maven + Spring Boot** y su respectivo README.

- ![img_1.png](img_1.png)
- ![img.png](img.png)
- Se puede evidenciar que el taller corre perfectamente con los parametros pedidos, se toma captura de los momentos
mas relevantes del test corriendo de igual mandera al final del readme se deja los comandos para correrlo

-----
### 2. Resolver los requerimientos del cliente siguiendo **principios SOLID**.
##### S (Single Responsibility Principle)Cada clase tiene una responsabilidad clara:
   - Producto → solo modela un producto.
   - ServicioInventario → administra el stock y notifica a los agentes.
   - Agentes → contiene las implementaciones de notificación.
   - Excepciones → agrupa los errores del dominio.

##### O (Open/Closed Principle) El sistema está abierto a extensión pero cerrado a modificación:

- Puedes crear nuevos agentes (AgenteEmail,AgenteWebhook, etc.) sin cambiar ServicioInventario.
Basta con implementar la interfazAgente y registrarlo.

##### L (Liskov Substitution Principle)
- Todos los agentes implementan la misma interfaz (Agente) y se pueden usar indistintamente en ServicioInventario.

##### I (Interface Segregation Principle)
- La interfaz Agente es pequeña y específica (notificar(Producto producto)), no obliga a implementar métodos innecesarios.

##### D (Dependency Inversion Principle)
ServicioInventario no depende de implementaciones concretas (AgenteLog,AgenteAdvertencia), sino de la abstracciónAgente.

-----
### 3. Implementar al menos un **patrón de diseño** y explicar su selección.
#### Se implementó el Patrón Observer (u Observador).

-Es un modelo de diseño que facilita que un objeto (sujeto) informe automáticamente a otros objetos (seguidores) cuando 
su estado se modifica, sin que el sujeto necesite conocer información específica sobre los seguidores.

- Sujeto (Subject): ServicioInventario

- Observadores (Observers): AgenteLog y AgenteAdvertencia

- Relación: cada vez que cambia el stock,ServicioInventario notifica automáticamente a todos los agentes registrados.

¿Por qué Observer?
- Porque existía una situación (cambio de inventario) que necesitaba ser comunicada a varios interesados (agentes).
  Facilita la inclusión de más agentes más adelante sin modificar la lógica principal.
  Se adapta idealmente a la necesidad de “informar a los interesados”.

-----

4. Incluir diagramas de **Contexto**, **Casos de uso** y **Clases**.
- 


5. Identificar criterios de aceptación y organizar el plan de trabajo con **Épicas**, **Features** y **Historias de Usuario (HU)**.
6. Usar **inyección de dependencias** para instanciar objetos.
7. Implementar pruebas unitarias con **JUnit 5**.
8. Integrar **JaCoCo** y **Sonar**, asegurando una cobertura mínima del **80%**.
9. Documentar evidencias del funcionamiento y explicar comandos Maven para correr el software.

---

## ⚙️ Requisitos del entorno

- **Java JDK 17**
- **Apache Maven 3.9.x**
- **Spring Boot 3.3.3**
- **JUnit 5** 
- **JaCoCo** (plugin Maven)
- **SonarQube / SonarCloud**

---

## 🚀 Comandos básicos 

se agrega una selecion por estetica y funcionalidad

```bash
# Verificar instalación
mvn -v
java -version

# Validar POM
mvn validate

# Compilar
mvn clean compile

# Ejecutar pruebas y generar reporte JaCoCo
mvn clean test

# Arrancar aplicación
mvn spring-boot:run

