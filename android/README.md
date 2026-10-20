# TARE·IA — Solución móvil Android

Proyecto académico basado en la arquitectura C4 entregada para **TARE·IA**. Incluye una aplicación Android en **Kotlin + Jetpack Compose + MVVM**, caché local preparada con **Room**, cliente REST con **Retrofit/OkHttp**, y un backend en **FastAPI** con persistencia SQLAlchemy, PostgreSQL, Redis y un adaptador de IA sustituible.

## Pantallas incluidas

1. Splash / identidad TARE·IA
2. Inicio de sesión
3. Registro
4. Inicio / dashboard
5. Captura o importación de documento para IA
6. Resultado y validación de tareas detectadas
7. Calendario
8. Lista de tareas
9. Detalle de tarea
10. Crear / editar tarea
11. Cursos
12. Progreso académico
13. Notificaciones
14. Perfil / ajustes

También se añadieron pantallas auxiliares de **Organización**, **Horario** y **Preferencias de estudio**, porque aparecen expresamente en la arquitectura del sistema.

## Cómo abrir la app Android

1. Abre **Android Studio**.
2. `File > Open` y selecciona la carpeta `TAREIA`.
3. Espera a que Gradle sincronice.
4. Utiliza las versiones incluidas del proyecto: Gradle 8.11.1, AGP 8.9.1 y Kotlin 2.1.20. Para Windows, consulta `ABRIR-EN-WINDOWS.md`.
5. Selecciona un emulador Android 9+ (API 28+) y ejecuta `app`.

> La app inicia en **modo demo** (`USE_DEMO_DATA=true`), así que todas las pantallas funcionan con datos de ejemplo aunque el backend no esté levantado. Esto es útil para exposición y revisión visual.

## Backend rápido sin Docker (SQLite demo)

```bash
cd backend
python -m venv .venv
# Windows: .venv\\Scripts\\activate
# macOS/Linux: source .venv/bin/activate
pip install -r requirements.txt
python seed.py
uvicorn app.main:app --reload
```

API: `http://localhost:8000`  |  Swagger: `http://localhost:8000/docs`

## Backend con PostgreSQL + Redis + MinIO

```bash
docker compose up -d
cd backend
cp .env.example .env
# En Windows puedes copiar manualmente .env.example a .env
pip install -r requirements.txt
python seed.py
uvicorn app.main:app --reload
```

En el emulador Android, `10.0.2.2` apunta al `localhost` de tu PC. Por eso `API_BASE_URL` ya está preparado como `http://10.0.2.2:8000/`.

## IA y Google Calendar

Por seguridad y para que el proyecto pueda ejecutarse sin credenciales reales, el ZIP **no incluye claves privadas**. El backend tiene `AIProvider` + `MockAIProvider`, siguiendo Strategy/Adapter. Sustituir el proveedor no requiere cambiar la UI ni el orquestador conceptual.

Google Calendar y OAuth están preparados en la arquitectura, pero la conexión real necesita un proyecto de Google Cloud y sus credenciales. Debes introducirlas en `.env`, nunca dentro del repositorio.

## Estructura

- `app/` — Android Compose
- `backend/app/routers/` — controladores FastAPI
- `backend/app/services/` — reglas y adaptadores
- `backend/app/models.py` — entidades persistentes
- `docker-compose.yml` — PostgreSQL, Redis y MinIO
- `docs/` — arquitectura original y referencia visual

## Alcance de esta entrega

Esta versión prioriza que la interfaz aprobada sea navegable y demostrable y que el código ya nazca con la separación MVVM/Repository/API de la arquitectura. Integraciones que requieren cuentas externas (OAuth real, Calendar real y proveedor IA de pago) quedan desacopladas y listas para configurar, pero no contienen secretos ni credenciales.
