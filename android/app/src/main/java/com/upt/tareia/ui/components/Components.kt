package com.upt.tareia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.upt.tareia.domain.TaskItem
import com.upt.tareia.domain.TaskStatus
import com.upt.tareia.ui.navigation.Screen
import com.upt.tareia.ui.theme.*

@Composable
fun BrandMark(compact: Boolean = false, light: Boolean = false) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier.size(if (compact) 24.dp else 34.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(Brush.linearGradient(listOf(if (light) Color.White else Violet, if (light) Lavender else Cyan))),
            contentAlignment = Alignment.Center
        ) { Icon(Icons.Rounded.AutoAwesome, null, tint = if (light) Violet else Color.White, modifier = Modifier.size(if (compact) 15.dp else 21.dp)) }
        Spacer(Modifier.width(9.dp))
        Text("TARE·", fontWeight = FontWeight.Black, fontSize = if (compact) 18.sp else 28.sp, color = if (light) Color.White else Ink)
        Text("IA", fontWeight = FontWeight.Black, fontSize = if (compact) 18.sp else 28.sp, color = if (light) Color.White else Violet)
    }
}

@Composable
fun GradientHero(content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier.fillMaxWidth().background(
            Brush.linearGradient(listOf(Color(0xFF32236A), Violet, Color(0xFF8A65FF)))
        ).padding(20.dp),
        content = content
    )
}

@Composable
fun StatPill(value: String, label: String, tint: Color, icon: ImageVector) {
    Surface(shape = RoundedCornerShape(20.dp), color = tint.copy(alpha = .12f), modifier = Modifier.height(82.dp)) {
        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, null, tint = tint, modifier = Modifier.size(18.dp)); Spacer(Modifier.width(6.dp))
                Text(value, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = tint)
            }
            Text(label, style = MaterialTheme.typography.labelMedium, color = Ink)
        }
    }
}

@Composable
fun TaskCard(task: TaskItem, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Row(Modifier.padding(vertical = 14.dp, horizontal = 14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.width(5.dp).height(52.dp).clip(CircleShape).background(Color(task.accent)))
            Spacer(Modifier.width(12.dp))
            Box(Modifier.size(30.dp).clip(CircleShape).background(Color(task.accent).copy(alpha=.12f)), contentAlignment = Alignment.Center) {
                Icon(if (task.status == TaskStatus.COMPLETADA) Icons.Rounded.Check else Icons.Rounded.RadioButtonUnchecked, null, tint = Color(task.accent), modifier = Modifier.size(18.dp))
            }
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text(task.title, fontWeight = FontWeight.Bold, color = Ink)
                Text(task.course, fontSize = 12.sp, color = Muted)
                Text("${task.dueDate} · ${task.dueTime}", fontSize = 11.sp, color = Muted)
            }
            Icon(Icons.Rounded.ChevronRight, null, tint = Muted)
        }
    }
}

@Composable
fun TareiaBottomBar(nav: NavHostController, selected: String) {
    NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
        val items = listOf(
            Triple(Screen.Home.route, "Inicio", Icons.Rounded.Home),
            Triple(Screen.Tasks.route, "Tareas", Icons.Rounded.TaskAlt),
            Triple(Screen.Capture.route, "IA", Icons.Rounded.Add),
            Triple(Screen.Calendar.route, "Calendario", Icons.Rounded.CalendarMonth),
            Triple(Screen.Courses.route, "Cursos", Icons.Rounded.School)
        )
        items.forEach { (route, label, icon) ->
            NavigationBarItem(
                selected = selected == route,
                onClick = { nav.navigate(route) { launchSingleTop = true } },
                icon = {
                    if (label == "IA") Box(Modifier.size(46.dp).clip(CircleShape).background(Brush.linearGradient(listOf(Violet, Color(0xFF8B6EFF)))), contentAlignment = Alignment.Center) { Icon(icon, null, tint = Color.White) }
                    else Icon(icon, null)
                },
                label = { if (label != "IA") Text(label, fontSize = 10.sp) },
                colors = NavigationBarItemDefaults.colors(selectedIconColor = Violet, selectedTextColor = Violet, indicatorColor = Lavender)
            )
        }
    }
}

@Composable
fun PageTitle(title: String, subtitle: String? = null, action: @Composable (() -> Unit)? = null) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.ExtraBold, fontSize = 28.sp, color = Ink)
            if (subtitle != null) Text(subtitle, color = Muted, fontSize = 13.sp)
        }
        action?.invoke()
    }
}
