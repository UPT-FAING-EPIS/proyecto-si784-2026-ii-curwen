package com.upt.tareia.domain

enum class TaskStatus { PENDIENTE_VALIDAR, CONFIRMADA, COMPLETADA, EN_PAPELERA }
enum class TaskType { TAREA, PROYECTO, EXAMEN, ENTREGABLE, OTRO }
enum class Priority { BAJA, MEDIA, ALTA }

data class TaskItem(
    val id: Int,
    val title: String,
    val description: String,
    val course: String,
    val dueDate: String,
    val dueTime: String,
    val type: TaskType = TaskType.TAREA,
    val priority: Priority = Priority.MEDIA,
    val status: TaskStatus = TaskStatus.CONFIRMADA,
    val accent: Long = 0xFF6C4DFF,
    val attachment: String? = null
)

data class CourseItem(
    val id: Int,
    val name: String,
    val shortName: String,
    val professor: String,
    val priority: Priority,
    val accent: Long,
    val summary: String
)

data class AppNotification(
    val id: Int,
    val title: String,
    val message: String,
    val whenText: String,
    val kind: String
)

data class AiDetectedTask(
    val title: String,
    val course: String,
    val dueDate: String,
    val type: String,
    val description: String
)
