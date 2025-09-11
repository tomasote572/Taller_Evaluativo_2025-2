# Taller Evaluativo 2025-2

📌 **Proyecto Maven con Spring Boot**  
Este repositorio implementa la solución al Taller Evaluativo siguiendo principios **SOLID**, pruebas unitarias con **JUnit 5**, cobertura con **JaCoCo (≥ 80%)**, e integración con **Sonar**.

#### Integrantes

-Tomas Felipe Ramirez Alvarez

## 📖 Descripción del taller

1. Crear un repositorio en GitHub con un proyecto **Maven + Spring Boot** y su respectivo README.

-



2. Resolver los requerimientos del cliente siguiendo **principios SOLID**.
3. Implementar al menos un **patrón de diseño** y explicar su selección.
4. Incluir diagramas de **Contexto**, **Casos de uso** y **Clases**.
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
- **JUnit 5** (incluido en `spring-boot-starter-test`)
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

