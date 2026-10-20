package com.upt.tareia.ui.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object Capture : Screen("capture")
    data object AiResult : Screen("ai_result")
    data object Calendar : Screen("calendar")
    data object Tasks : Screen("tasks")
    data object NewTask : Screen("new_task")
    data object Courses : Screen("courses")
    data object Progress : Screen("progress")
    data object Notifications : Screen("notifications")
    data object Profile : Screen("profile")
    data object Organization : Screen("organization")
    data object Schedule : Screen("schedule")
    data object Preferences : Screen("preferences")
    data object TaskDetail : Screen("task/{id}") { fun create(id: Int) = "task/$id" }
}
