# 🎓 Sistema Educativo - Microservicios

## 📌 Descripción General
Este proyecto es parte del **Parcial 2 - Microservicios** del curso de Ingeniería de Sistemas. Se implementa un **sistema educativo distribuido** utilizando **Spring Boot** y **Spring Cloud**, donde se gestiona la información de usuarios (estudiantes y docentes), asignaturas y matrículas, aplicando conceptos modernos de arquitectura basada en microservicios.

## 🔍 Enfoque Distribuido
El sistema está compuesto por múltiples microservicios independientes, cada uno con su propia base de datos, configuraciones, y responsabilidades bien definidas. La arquitectura incluye:
- **Descubrimiento de servicios** con **Eureka**
- **Configuración centralizada** con **Spring Cloud Config**
- **Seguridad** mediante JWT
- **Comunicación interna** con Feign Client
- **Monitorización** con Spring Boot Admin y Actuator
- **Contenerización y orquestación** con Docker y Docker Compose

---

## 🏗️ Estructura del Proyecto

```plaintext
sistema-educativo-microservicios-RichardZambrano/
│
├── usuarios-servicio/        # Gestión de estudiantes y docentes
├── asignaturas-servicio/     # CRUD de materias
├── matriculas-servicio/      # Registro de estudiantes en materias
│
├── config-server/            # Configuración centralizada (Spring Cloud Config)
├── eureka-server/            # Descubrimiento de servicios (Eureka)
├── monitor-admin/            # Consola de monitoreo (Spring Boot Admin)
│
├── docker-compose.yml        # Orquestación completa con Docker
└── README.md                 # Documentación del proyecto
```

## ⚙️ Microservicios Implementados
📘 ``usuarios-servicio``
- Gestión de usuarios (estudiantes y docentes)

- Autenticación con Spring Security y JWT

- Endpoints protegidos y roles definidos

📕 ``asignaturas-servicio``
- CRUD completo de materias

- Persistencia en PostgreSQL

📗 ``matriculas-servicio``
- Registro de estudiantes en asignaturas

- Comunicación vía Feign con los otros dos microservicios

## 🔄 Comunicación entre Microservicios
- Se utiliza Feign Client para consumir datos entre microservicios

- ``matriculas-servicio`` consume endpoints de:

- ``usuarios-servicio para`` obtener estudiantes/docentes

- ``asignaturas-servicio`` para obtener materias

- Eureka permite que cada servicio se registre y se descubran dinámicamente

## 🛡️ Seguridad con JWT
- ``usuarios-servicio`` provee endpoints para autenticación ``(/auth/login)``

- Se generan y validan tokens JWT

-Endpoints protegidos según roles (``ADMIN``, ``DOCENTE``, ``ESTUDIANTE``, etc.)

- Seguridad manejada con filtros y configuraciones de Spring Security

## 📊 Monitoreo y Salud del Sistema
- Se usa Spring Boot Actuator para exponer endpoints como ``/actuator/health``

- Consola web de monitoreo con Spring Boot Admin ``(monitor-admin)``

- Se monitorean servicios registrados automáticamente vía Eureka

## 🧪 Pruebas
- Se implementan pruebas unitarias y de integración con Spring Boot Test

- Cobertura básica garantizada por microservicio

- Tests adicionales pueden realizarse desde Postman (colección opcional)

## 🐳 Despliegue con Docker
- Cada microservicio contiene su propio Dockerfile, y el entorno completo se despliega con docker-compose.yml.

### 🔧 Requisitos
- Docker

- Docker Compose

- Git

## ▶️ Pasos para ejecutar:
```bash

git clone https://github.com/RichardZam/sistema-educativo-microservicios-RichardZambrano.git
cd sistema-educativo-microservicios-RichardZambrano
docker-compose up --build
```
### 🌐 Accesos:
- Eureka: http://localhost:8761

- Config Server: http://localhost:8888

- Monitor Admin: http://localhost:8090

- Servicios (puertos expuestos):

- Usuarios: 8081

- Asignaturas: 8082

- Matrículas: 8083

## 🧾 Datos Personales
- Nombre: Richard Zambrano Diaz Y Stiven David Zapatas Crastro
- Carrera: Ingeniería de Sistemas
- Parcial: 2 – Microservicios
- Repositorio: GitHub - sistema-educativo-microservicios-RichardZambrano