# Verificación realizada

- Backend: sintaxis Python verificada con `compileall`.
- Backend demo: base SQLite inicializada con `seed.py`.
- Endpoint `/health`: OK.
- Endpoint `/cursos`: OK.
- Endpoint `/actividades`: OK.
- Endpoint `/ia/analizar`: OK usando el adaptador MockAIProvider.
- Proyecto Android: estructura Gradle, Manifest, navegación Compose, ViewModels, Repository, Room y Retrofit incluidos.
- No se incluyen secretos de Google ni claves de proveedor IA.

## Nota Android

El entorno donde se generó el ZIP no dispone del Android SDK/Gradle de Android Studio, por lo que el APK no se compiló aquí. El código fuente está preparado para importarse en Android Studio. Si el IDE solicita versión de Gradle al importar el proyecto, utiliza una versión compatible con AGP 8.9.1 (por ejemplo Gradle 8.11.1) o permite que Android Studio actualice el proyecto automáticamente.
