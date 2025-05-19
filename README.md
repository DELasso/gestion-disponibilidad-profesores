# 📘 Sistema de Gestión de Disponibilidad de Profesores - UdeM

Este proyecto es una plataforma para la Universidad de Medellín que permite a profesores y estudiantes gestionar horarios, asesorías y disponibilidad.  
Está dividido en dos grandes módulos:

- `frontend/` – Aplicación web desarrollada en **React 18**.
- `backend/` – API REST desarrollada en **Spring Boot 3**, con base de datos en memoria **H2** y logging conectado a **ELK**.

---


## 📦 Tecnologías utilizadas

| Parte       | Tecnología                     | Versión     |
|-------------|--------------------------------|-------------|
| Frontend    | React + React Router DOM       | ^18.x       |
| Backend     | Spring Boot                    | ^3.4.4      |
| BD (dev)    | H2 (en memoria)                | -           |
| Logging     | Logstash + Elasticsearch + Kibana | 8.6.2    |
| Contenedores| Docker & Docker Compose        | latest      |
| Logs        | Logback + Logstash TCP Appender| -           |

---

## 🧩 Estructura del Repositorio

```bash
.
├── backend
│   ├── Dockerfile
│   ├── logback-spring.xml
│   ├── src/main/java/com/sistema_profesores/
│   ├── src/main/resources/application.properties
├── frontend
│   ├── Dockerfile
│   ├── src/pages, components, ...
├── logstash/
│   └── logstash.conf
├── docker-compose.yml
├── README.md
```
## ⚙️ Cómo ejecutar el proyecto con Docker Compose
git clone https://github.com/DELasso/gestion-disponibilidad-profesores.git
cd gestion-disponibilidad-profesores
docker-compose up --build

Luego abre en tu navegador:

    Frontend: http://localhost:3000

    Backend: http://localhost:8080

    Kibana (logs): http://localhost:5601

    Elasticsearch: http://localhost:9200
