
# 🏋️‍♂️ Club Deportivo Backend

Bienvenido al backend del sistema de gestión **Club Deportivo**, desarrollado con **Java + Spring Boot**, siguiendo buenas prácticas REST, seguridad con JWT, y almacenamiento en base de datos **MySQL**. Este proyecto permite gestionar miembros, disciplinas, entrenadores y asignaciones dentro de un club deportivo.

## 📌 Características principales

- API REST con operaciones CRUD completas.
- Seguridad mediante JWT (Bearer Authentication).
- Inheritance en JPA (`JOINED`) para modelar `Member`, `AdultMember` y `ChildMember`.
- Relaciones entre entidades (miembro-disciplina, entrenador-disciplina).
- Testing completo con JUnit 5, Mockito y MockMvc.
- Documentado y organizado con enfoque profesional.

## 🧠 Diagrama de Clases UML

A continuación se muestra el diseño del modelo de dominio:

![UML del proyecto](./Captura%20de%20pantalla%202025-05-06%20153645.png)


## 🚀 Endpoints principales

| Método | Endpoint                  | Descripción                         |
|--------|---------------------------|-------------------------------------|
| POST   | `/auth/login`             | Inicia sesión y retorna el JWT      |
| POST   | `/api/members`            | Crea un nuevo miembro               |
| GET    | `/api/members`            | Lista todos los miembros            |
| PUT    | `/api/members/{id}`       | Actualiza un miembro                |
| DELETE | `/api/members/{id}`       | Elimina un miembro                  |
| POST   | `/api/disciplines`        | Crea una nueva disciplina           |
| DELETE | `/api/disciplines/{id}`   | Elimina una disciplina              |

## 🔐 Seguridad

La autenticación está basada en JWT, y las rutas están protegidas mediante filtros en Spring Security. Solo `/auth/**` está libre de autenticación.

## 🧪 Testing

Se incluyen pruebas:
- Unitarias de servicios y controladores.
- Pruebas con `MockMvc` sin tocar la base de datos real.
- Validación de errores y autenticación.

## 🔧 Tecnologías utilizadas

Este proyecto ha sido desarrollado usando las siguientes tecnologías y dependencias:

- **Java 21**
- **Spring Boot 3.4.5**
  - spring-boot-starter-web
  - spring-boot-starter-data-jpa
  - spring-boot-starter-security
  - spring-boot-starter-validation
  - spring-boot-devtools
  - spring-boot-starter-test
- **Spring Security**
  - spring-security-test
- **Base de datos MySQL**
  - mysql-connector-j 8.0.32
- **JWT (JSON Web Tokens)**
  - jjwt-api 0.11.5
  - jjwt-impl 0.11.5
  - jjwt-jackson 0.11.5
- **Lombok** (para reducir boilerplate)
- **JUnit 5** para testing (`junit-jupiter`)
- **WebFlux y Reactor** (dependencias incluidas para pruebas)
- **Maven** como sistema de construcción


## 📂 Base de datos

La base de datos utilizada es MySQL. La configuración se encuentra en `application.properties`. El proyecto usa `ddl-auto=update` para facilitar el desarrollo.

## 📽️ Presentación del Proyecto

👉 Puedes ver la presentación completa del proyecto aquí:  
[🔗 Presentación Canva](https://www.canva.com/design/DAGnUIDaGv0/fo_mFe3JRsw4RMsfrwh7dw/edit?utm_content=DAGnUIDaGv0&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

## 📌 Gestión del Proyecto

🗂️ Puedes consultar la planificación y seguimiento de tareas en el tablero de Trello:  
[🔗 Trello - Proyecto 1](https://trello.com/b/pdc09ooc/proyecto-1)


## 👨‍💻 Autor
**Club Deportivo - API REST**  
Desarrollado por **Rafael Gamero Arrabal**  
¡Gracias por tu atención! 🙌
[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?logo=linkedin)](https://www.linkedin.com/in/rafael-gamero-arrabal-619200186/)

---

Este proyecto ha sido desarrollado siguiendo prácticas profesionales de desarrollo backend. ¡Gracias por visitar este repositorio!


