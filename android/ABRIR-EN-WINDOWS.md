# Abrir TAREIA en Windows

1. Extraer el ZIP en una carpeta propia, por ejemplo `Documentos/TAREIA`.
2. En Android Studio, elegir **File > Open** y seleccionar la carpeta que contiene `settings.gradle.kts`. No seleccionar solamente `app`.
3. En **Settings > Build, Execution, Deployment > Build Tools > Gradle**, utilizar el JDK incluido con Android Studio (Java 17 o 21).
4. Esperar la sincronización con Gradle 8.11.1.
5. Tener instalados Android SDK Platform 35 y Android SDK Build-Tools 36.0.0 desde SDK Manager.
6. Seleccionar un emulador o teléfono con Android 9 o posterior y ejecutar `app`.

Android Studio debe generar `local.properties` con la ubicación del SDK de esta computadora. El archivo del SDK de la Mac no se distribuye.

Para compilar desde la terminal del proyecto en Windows:

```powershell
.\gradlew.bat assembleDebug
```

El APK se genera en `app/build/outputs/apk/debug/app-debug.apk`.

Esta copia mantiene el modo de demostración original. La interfaz y la navegación funcionan con datos de ejemplo; las integraciones externas requieren su configuración correspondiente.
