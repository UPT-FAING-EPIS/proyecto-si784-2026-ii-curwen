package com.upt.tareia.data

import com.upt.tareia.domain.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface TareiaRepository {
    val tasks: StateFlow<List<TaskItem>>
    val courses: StateFlow<List<CourseItem>>
    val notifications: StateFlow<List<AppNotification>>
    fun task(id: Int): TaskItem?
    fun addTask(task: TaskItem)
    fun completeTask(id: Int)
}

object DemoRepository : TareiaRepository {
    private val _tasks = MutableStateFlow(
        listOf(
            TaskItem(1, "Informe de Base de Datos", "Realizar un informe sobre modelos de bases de datos relacionales. Incluir ejemplos y diagramas.", "Base de Datos I", "30 sep 2026", "11:59 PM", TaskType.TAREA, Priority.ALTA, TaskStatus.CONFIRMADA, 0xFFFF6678, "requisitos.pdf"),
            TaskItem(2, "Leer capítulo 4", "Lectura y resumen de arquitectura de software.", "Ingeniería de Software", "1 oct 2026", "8:00 AM", TaskType.TAREA, Priority.MEDIA, TaskStatus.CONFIRMADA, 0xFF2AC7D4),
            TaskItem(3, "Práctica de laboratorio", "Completar práctica de colecciones y persistencia.", "Programación III", "2 oct 2026", "11:59 PM", TaskType.ENTREGABLE, Priority.MEDIA, TaskStatus.CONFIRMADA, 0xFF35C985),
            TaskItem(4, "Presentación final", "Presentar avance del proyecto.", "Gestión de Proyectos", "5 oct 2026", "4:00 PM", TaskType.PROYECTO, Priority.ALTA, TaskStatus.PENDIENTE_VALIDAR, 0xFF6C4DFF),
            TaskItem(5, "Estudiar para examen", "Repasar routing, capas y protocolos.", "Redes de Computadoras", "8 oct 2026", "9:00 AM", TaskType.EXAMEN, Priority.ALTA, TaskStatus.CONFIRMADA, 0xFFFFA629)
        )
    )
    private val _courses = MutableStateFlow(
        listOf(
            CourseItem(1,"Base de Datos I","BD1","Prof. Edwin Valencia",Priority.ALTA,0xFF2797FF,"4 tareas · 1 examen"),
            CourseItem(2,"Programación III","P3","Prof. Elard Rodríguez",Priority.ALTA,0xFF35C985,"3 tareas · 2 exámenes"),
            CourseItem(3,"Ingeniería de Software","IS","Prof. Paredes Vignola",Priority.MEDIA,0xFFFFA629,"2 tareas · 1 examen"),
            CourseItem(4,"Gestión de Proyectos","GP","Prof. Paredes Vignola",Priority.MEDIA,0xFF6C4DFF,"1 tarea · 1 examen"),
            CourseItem(5,"Redes de Computadoras","RC","Prof. López",Priority.ALTA,0xFFFF6678,"2 tareas · 1 examen")
        )
    )
    private val _notifications = MutableStateFlow(
        listOf(
            AppNotification(1,"Recordatorio","Informe de BD vence hoy","Hace 1 hora","calendar"),
            AppNotification(2,"Nueva tarea","Se ha detectado una nueva tarea desde tu captura","Hace 3 horas","bell"),
            AppNotification(3,"Tarea completada","Has completado ‘Leer capítulo 4’","Ayer","check"),
            AppNotification(4,"Recordatorio de examen","Examen de Redes de Comp. en 3 días","Ayer","exam"),
            AppNotification(5,"Nueva tarea","Práctica de laboratorio añadida","Ayer","bell")
        )
    )

    override val tasks = _tasks.asStateFlow()
    override val courses = _courses.asStateFlow()
    override val notifications = _notifications.asStateFlow()
    override fun task(id: Int) = _tasks.value.firstOrNull { it.id == id }
    override fun addTask(task: TaskItem) { _tasks.value = _tasks.value + task }
    override fun completeTask(id: Int) { _tasks.value = _tasks.value.map { if (it.id == id) it.copy(status = TaskStatus.COMPLETADA) else it } }
}
