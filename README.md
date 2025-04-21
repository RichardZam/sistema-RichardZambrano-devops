# 🎓 Sistema Educativo - Microservicios DEVOPS

## 📌 Descripción General
Este proyecto forma parte del **Parcial 3A - Microservicios-DEVOPS** del curso de Ingeniería de Sistemas. Consiste en un **sistema educativo distribuido** desarrollado con **Spring Boot** y **Spring Cloud**, enfocado en la gestión de usuarios (estudiantes y docentes), asignaturas y matrículas, siguiendo principios modernos de arquitectura basada en microservicios.

---

## 🔍 Enfoque Distribuido y Tecnologías
La arquitectura del sistema se construye sobre los siguientes pilares:

- ✅ **Eureka** para descubrimiento de servicios
- ⚙️ **Spring Cloud Config** para configuración centralizada
- 🔐 **JWT y Spring Security** para autenticación y autorización
- 📡 **Feign Client** para comunicación entre microservicios
- 📈 **Spring Boot Admin + Actuator** para monitoreo
- 🐳 **Docker** y **Docker Compose** para contenerización
- 🔭 **Grafana + Prometheus** para métricas avanzadas
- 🔁 **CI/CD automatizado** con GitHub Actions

---


## 🏗️ Estructura del Proyecto

```plaintext
sistema-educativo-microservicios-RichardZambrano/
│
├── usuarios-servicio/        # Gestión de estudiantes y docentes
├── asignaturas-servicio/     # CRUD de materias
├── matriculas-servicio/      # Registro de estudiantes en materias
│
├── config-server/            # Configuración centralizada
├── eureka-server/            # Descubrimiento de servicios
├── monitor-admin/            # Monitorización con Spring Boot Admin
│
├── prometheus/               # Configuración de Prometheus
├── grafana/                  # Configuración de Grafana
│
├── .github/workflows/        # Pipeline CI/CD con GitHub Actions
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
### Spring Boot Admin
- Se usa Spring Boot Actuator para exponer endpoints como ``/actuator/health``

- Consola web de monitoreo con Spring Boot Admin ``(monitor-admin)``

- Se monitorean servicios registrados automáticamente vía Eureka
### Prometheus + Grafana
 - Recolección de métricas con ``Prometheus``

- Dashboards personalizados en ``Grafana`` para métricas del sistema
## 🧪 Pruebas    
### 🔬 Pruebas Unitarias:

- Cada microservicio cuenta con al menos 2 pruebas unitarias utilizando Mockito para simular dependencias y validar el comportamiento de los componentes de forma aislada.

### 🔗 Pruebas de Integración:

- Se desarrolló al menos 1 prueba de integración por microservicio utilizando WebTestClient, para verificar el funcionamiento completo de los endpoints y su integración con la lógica interna.

## 🐳 Despliegue con Docker
- Cada microservicio contiene su propio Dockerfile, y el entorno completo se despliega con docker-compose.yml.

### 🔧 Requisitos
- Docker

- Docker Compose

- Git

## ▶️ Pasos para ejecutar:
```bash

git clone https://github.com/RichardZam/sistema-RichardZambrano-devops.git
cd sistema-RichardZambrano-devops
docker-compose up --build
```
### 🌐 Accesos:
- Componente | URL
- Eureka | http://localhost:8761
- Config Server | http://localhost:8888
- Monitor Admin | http://localhost:8088
- Grafana | http://localhost:3000
- Prometheus | http://localhost:9090
- Usuarios API | http://localhost:8081
- Asignaturas API | http://localhost:8082
- Matrículas API | http://localhost:8083

## 🧾 Datos Personales
- Nombre: Richard Zambrano Diaz Y Stiven David Zapatas Castro
- Carrera: Ingeniería de Sistemas
- Parcial: 2 – Microservicios
- Repositorio: GitHub - https://github.com/RichardZam/sistema-RichardZambrano-devops