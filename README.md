# TARE-IA

Aplicaci�n Android para organizar actividades acad�micas. Proyecto del equipo CURWEN.

## Descargar e instalar

[Descargar la versi�n de demostraci�n](https://github.com/UPT-FAING-EPIS/proyecto-si784-2026-ii-curwen/releases)

1. Abrir la publicaci�n m�s reciente y descargar `TARE-IA.apk`.
2. Abrir el archivo en un tel�fono con Android 9 o posterior.
3. Si Android lo solicita, permitir la instalaci�n desde la aplicaci�n utilizada para descargarlo.

La versi�n de demostraci�n incluye Inicio, Apuntes, Tareas, Calendario y Perfil. Las tareas se mantienen en memoria y se reinician al cerrar el proceso de la aplicaci�n. La extracci�n mediante IA y la sincronizaci�n con Google Calendar est�n pendientes.

## Documentaci�n

- [FD01 � Factibilidad](docs/unidad-i/FD01-Informe-de-Factibilidad.pdf)
- [FD02 � Visi�n](docs/unidad-i/FD02-Informe-de-Vision.pdf)
- [FD03 � SRS](docs/unidad-i/FD03-Informe-SRS.pdf)
- [C�digo Android](android/)

Los archivos Word y Markdown FD01�FD06 de la ra�z son los formatos del docente. No se ha incorporado un informe FD04 del equipo.

## Desarrollo y publicaci�n

La rama principal es `main`. El c�digo se encuentra en `android/`. Para compilar: `gradle -p android assembleDebug` (Gradle 8.9). Se requiere Java 17 y Android SDK 35.

Los cambios en `android/` enviados a `main` ejecutan la compilaci�n, el an�lisis de Android y la publicaci�n de un APK de demostraci�n en GitHub Releases. Tambi�n se puede ejecutar **Publicar Android** desde la pesta�a Actions.

## Planteamiento del proyecto

1. Título
TARE-IA: Tareas académicas con reconocimiento y ejecución mediante IA.

2. Problema detallado con fuentes
   
En el entorno educativo universitario, los estudiantes reciben información académica fragmentada a través de múltiples canales simultáneos, tales como plataformas LMS (Moodle), correos institucionales, grupos de WhatsApp y documentos sueltos. Esta dispersión genera una sobrecarga que dificulta la consolidación de un cronograma único y eficiente.

Evidencia del problema: Sobrecarga cognitiva y el mito del "multitasking"

Existe la presunción errónea de que las actuales generaciones de estudiantes poseen una capacidad innata para procesar múltiples fuentes de información de manera simultánea. Sin embargo, la evidencia científica demuestra que el cerebro humano no es capaz de realizar multitasking real al procesar información, sino que efectúa un rápido "cambio de tareas" (task-switching). Alternar constantemente entre diversos canales digitales (LMS, redes sociales, correo) genera una interferencia cognitiva que limita los recursos atencionales del estudiante, lo que se traduce en una mayor ineficiencia, una mayor tasa de errores y un aprendizaje deficiente (Kirschner & De Bruyckere, 2017). El registro manual de compromisos académicos bajo estas condiciones de fragmentación consume un tiempo valioso y exacerba la pérdida de información.

Evidencia del problema: Procrastinación académica y entregas tardías

La desorganización derivada de la gestión manual de tareas es un factor directamente vinculado a la procrastinación académica. Investigaciones indican que la incapacidad para visualizar claramente los plazos a corto y mediano plazo contribuye a que más del 50% de los estudiantes universitarios experimenten problemas crónicos de postergación y entregas tardías (Steel, 2007).

3. Objetivos de investigación y solución medibles
Objetivo general

Evaluar el impacto de la implementación de TARE-IA en la reducción del tiempo dedicado a la organización manual de actividades y en la disminución de la tasa de entregas tardías u olvidadas en estudiantes universitarios.

Objetivos de investigación

Línea base de organización: Determinar el tiempo promedio (en minutos por semana) que los estudiantes dedican actualmente a registrar, organizar y revisar sus compromisos académicos de forma manual.

Cuantificación de incidencias: Determinar la tasa base (porcentaje) de estudiantes que han olvidado o entregado tarde al menos una actividad académica durante el último mes debido a falta de organización.

Identificación de dispersión: Identificar y jerarquizar los canales de comunicación digital que generan mayor pérdida o confusión de información académica.

Evaluación de impacto: Comparar las métricas de tiempo de organización y tareas olvidadas antes y después del periodo piloto de uso de TARE-IA.

Objetivos de solución (Criterios de aceptación del MVP)

Precisión técnica: Alcanzar una precisión mínima del 80% en la extracción automática de entidades clave (fecha, materia y tipo de actividad) a partir de capturas de pantalla y textos utilizando IA.

Reducción de fricción: Permitir que la conversión de información dispersa en un evento calendarizado requiera un máximo de 3 pasos manuales (o interacciones) por parte del usuario.

Fiabilidad de integración: Lograr una tasa de éxito del 80% en la sincronización de las tareas confirmadas hacia Google Calendar. Esto se medirá verificando la correcta manipulación de la API REST de Google para la creación de recursos de tipo Event sin generar duplicidad de registros (Google for Developers, 2026).

Usabilidad probada: Obtener una calificación mínima de 70 puntos en la escala SUS (System Usability Scale) tras las pruebas con los usuarios piloto, asegurando que el sistema supera el percentil 50 de usabilidad estándar (Brooke, 2013).

Impacto directo: Reducir en al menos un 30% la cantidad de tareas reportadas como olvidadas por el grupo de prueba durante el periodo de evaluación.

4. Fuentes
Brooke, J. (2013). SUS: A Retrospective. Journal of Usability Studies, 8(2), 29-40.

Google for Developers. (2026). Google Calendar API Documentation - Overview.

Kirschner, P. A., & De Bruyckere, P. (2017). The myths of the digital native and the multitasker. Teaching and Teacher Education, 67, 135-142.

Steel, P. (2007). The nature of procrastination: A meta-analytic and theoretical review of quintessential self-regulatory failure. Psychological Bulletin, 133(1), 65-94.


