# Mapeo de arquitectura a implementación

| Arquitectura | Implementación incluida |
|---|---|
| Aplicación Android Kotlin + Compose + MVVM | `app/src/main/java/com/upt/tareia/ui` + `ui/viewmodel` |
| Room | `data/local` |
| Repository | `data/DemoRepository.kt` (interfaz + demo) |
| Retrofit + OkHttp | `data/remote/ApiService.kt` |
| FastAPI | `backend/app/main.py` + routers |
| PostgreSQL / SQLAlchemy | `backend/app/db.py`, `models.py`, `docker-compose.yml` |
| Redis | contenedor preparado; punto de integración del worker |
| Object Storage | MinIO en `docker-compose.yml` |
| Motor IA / Adapter | `backend/app/services/ai.py` |
| Duplicados RN02/RN06 | `backend/app/services/rules.py` |
| Cursos con abreviatura única RN12 | `backend/app/services/rules.py` |
| Organización / Papelera / Anotaciones | `organization.py` + pantalla Organización |
| Horario / preferencias | endpoints configuración + pantallas dedicadas |

La UI utiliza datos demo por defecto para no bloquear la ejecución por servicios externos. El cliente Retrofit y el backend ya están separados para reemplazar gradualmente el repositorio demo por persistencia/sincronización real.
