package com.upt.tareia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.upt.tareia.domain.TaskStatus
import com.upt.tareia.ui.components.*
import com.upt.tareia.ui.navigation.Screen
import com.upt.tareia.ui.theme.*
import com.upt.tareia.ui.viewmodel.DashboardViewModel

@Composable
fun HomeScreen(nav: NavHostController, vm: DashboardViewModel = viewModel()) {
    val tasks by vm.tasks.collectAsState()
    Scaffold(bottomBar={TareiaBottomBar(nav, Screen.Home.route)}) { pad ->
        Column(Modifier.padding(pad).fillMaxSize().background(Canvas)) {
            Box(Modifier.fillMaxWidth().background(Brush.linearGradient(listOf(Color(0xFF4B31A0),Violet,Color(0xFF8B6EFF)))).padding(20.dp)) {
                Column {
                    Row(verticalAlignment=Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)){ Text("Hola, Antony 👋", color=Color.White, fontSize=25.sp, fontWeight=FontWeight.ExtraBold); Text("¿Listo para un día productivo?", color=Color.White.copy(alpha=.85f)) }
                        IconButton(onClick={nav.navigate(Screen.Notifications.route)}){Icon(Icons.Rounded.Notifications,null,tint=Color.White)}
                        Box(Modifier.size(40.dp).clip(CircleShape).background(Color.White.copy(alpha=.2f)),contentAlignment=Alignment.Center){Text("A",color=Color.White,fontWeight=FontWeight.Bold)}
                    }
                    Spacer(Modifier.height(18.dp))
                    Surface(shape=RoundedCornerShape(18.dp),color=Color.White.copy(alpha=.94f)){ Row(Modifier.fillMaxWidth().padding(14.dp),verticalAlignment=Alignment.CenterVertically){Icon(Icons.Rounded.CalendarToday,null,tint=Violet);Spacer(Modifier.width(9.dp));Text("Miércoles · 30 septiembre 2026",fontWeight=FontWeight.Bold,color=Ink)} }
                }
            }
            Column(Modifier.padding(18.dp)) {
                Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(10.dp)) {
                    Box(Modifier.weight(1f)){StatPill("3","Tareas hoy",Color(0xFF2D93F5),Icons.Rounded.TaskAlt)}
                    Box(Modifier.weight(1f)){StatPill("1","Examen",Lime,Icons.Rounded.School)}
                    Box(Modifier.weight(1f)){StatPill(tasks.count{it.status==TaskStatus.PENDIENTE_VALIDAR}.toString(),"Por validar",Amber,Icons.Rounded.AutoAwesome)}
                }
                Spacer(Modifier.height(22.dp))
                Row(verticalAlignment=Alignment.CenterVertically){Text("Próximas tareas",fontSize=21.sp,fontWeight=FontWeight.ExtraBold,modifier=Modifier.weight(1f));TextButton(onClick={nav.navigate(Screen.Tasks.route)}){Text("Ver todas")}}
                tasks.take(3).forEach { TaskCard(it,{nav.navigate(Screen.TaskDetail.create(it.id))},Modifier.padding(bottom=9.dp)) }
                Spacer(Modifier.height(4.dp))
                OutlinedButton(onClick={nav.navigate(Screen.Progress.route)},modifier=Modifier.fillMaxWidth(),shape=RoundedCornerShape(16.dp)){Icon(Icons.Rounded.Insights,null);Spacer(Modifier.width(8.dp));Text("Ver mi progreso")}
            }
        }
    }
}
