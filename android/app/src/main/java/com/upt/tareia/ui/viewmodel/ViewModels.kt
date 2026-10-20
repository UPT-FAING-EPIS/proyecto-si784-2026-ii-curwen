package com.upt.tareia.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.upt.tareia.data.DemoRepository
import com.upt.tareia.domain.*
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel : ViewModel() {
    val tasks: StateFlow<List<TaskItem>> = DemoRepository.tasks
}
class TasksViewModel : ViewModel() {
    val tasks = DemoRepository.tasks
    fun find(id: Int) = DemoRepository.task(id)
    fun complete(id: Int) = DemoRepository.completeTask(id)
    fun create(title: String, description: String, course: String, date: String, type: TaskType, priority: Priority) {
        val next = (tasks.value.maxOfOrNull { it.id } ?: 0) + 1
        DemoRepository.addTask(TaskItem(next, title, description, course, date, "11:59 PM", type, priority))
    }
}
class CoursesViewModel : ViewModel() { val courses = DemoRepository.courses }
class NotificationsViewModel : ViewModel() { val notifications = DemoRepository.notifications }
