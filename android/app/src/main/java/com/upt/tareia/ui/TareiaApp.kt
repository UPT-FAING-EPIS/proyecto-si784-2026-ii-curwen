package com.upt.tareia.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.upt.tareia.ui.navigation.Screen
import com.upt.tareia.ui.screens.*

@Composable
fun TareiaApp() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) { SplashScreen { nav.navigate(Screen.Login.route) { popUpTo(Screen.Splash.route) { inclusive = true } } } }
        composable(Screen.Login.route) { LoginScreen(onLogin = { nav.navigate(Screen.Home.route) { popUpTo(Screen.Login.route) { inclusive = true } } }, onRegister = { nav.navigate(Screen.Register.route) }) }
        composable(Screen.Register.route) { RegisterScreen(onRegister = { nav.navigate(Screen.Home.route) { popUpTo(Screen.Login.route) { inclusive = true } } }, onBack = { nav.popBackStack() }) }
        composable(Screen.Home.route) { HomeScreen(nav) }
        composable(Screen.Capture.route) { CaptureScreen(nav) }
        composable(Screen.AiResult.route) { AiResultScreen(nav) }
        composable(Screen.Calendar.route) { CalendarScreen(nav) }
        composable(Screen.Tasks.route) { TasksScreen(nav) }
        composable(Screen.NewTask.route) { NewTaskScreen(nav) }
        composable(Screen.Courses.route) { CoursesScreen(nav) }
        composable(Screen.Progress.route) { ProgressScreen(nav) }
        composable(Screen.Notifications.route) { NotificationsScreen(nav) }
        composable(Screen.Profile.route) { ProfileScreen(nav) }
        composable(Screen.Organization.route) { OrganizationScreen(nav) }
        composable(Screen.Schedule.route) { ScheduleScreen(nav) }
        composable(Screen.Preferences.route) { PreferencesScreen(nav) }
        composable(Screen.TaskDetail.route, arguments = listOf(navArgument("id") { type = NavType.IntType })) { entry ->
            TaskDetailScreen(nav, entry.arguments?.getInt("id") ?: 1)
        }
    }
}
