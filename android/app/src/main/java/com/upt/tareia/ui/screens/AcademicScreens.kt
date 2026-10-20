package com.upt.tareia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.upt.tareia.domain.Priority
import com.upt.tareia.ui.components.*
import com.upt.tareia.ui.navigation.Screen
import com.upt.tareia.ui.theme.*
import com.upt.tareia.ui.viewmodel.CoursesViewModel
import com.upt.tareia.ui.viewmodel.TasksViewModel

@Composable fun CalendarScreen(nav:NavHostController,vm:TasksViewModel= viewModel()){
    val tasks by vm.tasks.collectAsState(); Scaffold(bottomBar={TareiaBottomBar(nav,Screen.Calendar.route)},floatingActionButton={FloatingActionButton(onClick={nav.navigate(Screen.NewTask.route)},containerColor=Violet,contentColor=Color.White){Icon(Icons.Rounded.Add,null)}}){pad->Column(Modifier.padding(pad).fillMaxSize().background(Canvas).padding(18.dp)){
        PageTitle("Calendario","Septiembre 2026");Spacer(Modifier.height(16.dp));Surface(shape=RoundedCornerShape(22.dp),color=Color.White,shadowElevation=2.dp){Column(Modifier.padding(14.dp)){Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){Text("Lun",color=Muted);Text("Mar",color=Muted);Text("Mié",color=Muted);Text("Jue",color=Muted);Text("Vie",color=Muted);Text("Sáb",color=Muted);Text("Dom",color=Muted)};Spacer(Modifier.height(10.dp));val days=listOf("28","29","30","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","1");days.chunked(7).forEach{week->Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){week.forEach{d->Box(Modifier.size(34.dp).clip(RoundedCornerShape(10.dp)).background(if(d=="30")Violet else Color.Transparent),contentAlignment=Alignment.Center){Text(d,color=if(d=="30")Color.White else Ink,fontWeight=if(d=="30")FontWeight.Bold else FontWeight.Normal)}}};Spacer(Modifier.height(7.dp))}}}
        Spacer(Modifier.height(20.dp));Text("Tareas de este día",fontSize=20.sp,fontWeight=FontWeight.ExtraBold);Spacer(Modifier.height(10.dp));tasks.take(3).forEach{TaskCard(it,{nav.navigate(Screen.TaskDetail.create(it.id))},Modifier.padding(bottom=9.dp))}
    }}
}

@Composable fun CoursesScreen(nav:NavHostController,vm:CoursesViewModel= viewModel()){
    val courses by vm.courses.collectAsState();Scaffold(bottomBar={TareiaBottomBar(nav,Screen.Courses.route)},floatingActionButton={FloatingActionButton(onClick={},containerColor=Violet,contentColor=Color.White){Icon(Icons.Rounded.Add,null)}}){pad->Column(Modifier.padding(pad).fillMaxSize().background(Canvas).padding(18.dp)){PageTitle("Mis cursos","Contexto que usa la IA");Spacer(Modifier.height(16.dp));courses.forEach{c->Surface(Modifier.fillMaxWidth().padding(bottom=10.dp),shape=RoundedCornerShape(20.dp),color=Color(c.accent).copy(alpha=.10f)){Row(Modifier.padding(15.dp),verticalAlignment=Alignment.CenterVertically){Surface(shape=RoundedCornerShape(15.dp),color=Color(c.accent)){Icon(Icons.Rounded.School,null,tint=Color.White,modifier=Modifier.padding(12.dp))};Spacer(Modifier.width(12.dp));Column(Modifier.weight(1f)){Text(c.name,fontWeight=FontWeight.ExtraBold);Text(c.professor,fontSize=12.sp,color=Muted);Text(c.summary,fontSize=11.sp,color=Muted)};Icon(Icons.Rounded.ChevronRight,null,tint=Muted)}}};Spacer(Modifier.height(6.dp));OutlinedButton(onClick={nav.navigate(Screen.Preferences.route)},Modifier.fillMaxWidth(),shape=RoundedCornerShape(16.dp)){Icon(Icons.Rounded.Tune,null);Spacer(Modifier.width(7.dp));Text("Prioridad de estudio")}}
    }
}

@Composable fun ScheduleScreen(nav:NavHostController){Scaffold(containerColor=Canvas){pad->Column(Modifier.padding(pad).padding(20.dp)){Row(verticalAlignment=Alignment.CenterVertically){IconButton(onClick={nav.popBackStack()}){Icon(Icons.Rounded.ArrowBack,null)};PageTitle("Mi horario","Evita cruces al planificar")};Spacer(Modifier.height(18.dp));listOf("Lunes · 08:00–10:00 · Base de Datos I","Martes · 16:00–18:00 · Programación III","Miércoles · 18:00–20:00 · Ingeniería de Software","Jueves · 19:00–21:00 · Gestión de Proyectos").forEach{Surface(Modifier.fillMaxWidth().padding(bottom=10.dp),shape=RoundedCornerShape(17.dp),color=Color.White){Row(Modifier.padding(15.dp),verticalAlignment=Alignment.CenterVertically){Icon(Icons.Rounded.Schedule,null,tint=Violet);Spacer(Modifier.width(10.dp));Text(it,fontWeight=FontWeight.SemiBold)}}};Button(onClick={},Modifier.fillMaxWidth(),shape=RoundedCornerShape(16.dp)){Text("Agregar bloque")}}}}

@Composable fun PreferencesScreen(nav:NavHostController){var block by remember{mutableFloatStateOf(45f)};var maxh by remember{mutableFloatStateOf(3f)};Scaffold(containerColor=Canvas){pad->Column(Modifier.padding(pad).padding(20.dp)){Row(verticalAlignment=Alignment.CenterVertically){IconButton(onClick={nav.popBackStack()}){Icon(Icons.Rounded.ArrowBack,null)};PageTitle("Preferencias","Cómo debe organizarte la IA")};Spacer(Modifier.height(20.dp));Text("Duración por bloque: ${block.toInt()} min",fontWeight=FontWeight.Bold);Slider(block,{block=it},valueRange=25f..120f,steps=18);Text("Máximo diario: ${maxh.toInt()} h",fontWeight=FontWeight.Bold);Slider(maxh,{maxh=it},valueRange=1f..8f,steps=6);Spacer(Modifier.height(10.dp));Text("Franja preferida",fontWeight=FontWeight.Bold);Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){listOf("Mañana","Tarde","Noche").forEach{AssistChip(onClick={},label={Text(it)})}};Spacer(Modifier.weight(1f));Button(onClick={nav.popBackStack()},Modifier.fillMaxWidth(),shape=RoundedCornerShape(16.dp)){Text("Guardar preferencias")}}}}
