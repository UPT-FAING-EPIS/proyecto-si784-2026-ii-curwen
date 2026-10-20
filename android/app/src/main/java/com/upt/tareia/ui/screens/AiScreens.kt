package com.upt.tareia.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.upt.tareia.ui.navigation.Screen
import com.upt.tareia.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun CaptureScreen(nav: NavHostController) {
    var picked by remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0f) }
    val picker = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri -> if (uri != null) picked = true }
    LaunchedEffect(picked) {
        if (picked) {
            repeat(10) { delay(110); progress = (it+1)/10f }
            nav.navigate(Screen.AiResult.route)
        }
    }
    Column(Modifier.fillMaxSize().background(Color(0xFF0F0F14)).padding(20.dp),horizontalAlignment=Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth(),verticalAlignment=Alignment.CenterVertically){IconButton(onClick={nav.popBackStack()}){Icon(Icons.Rounded.ArrowBack,null,tint=Color.White)};Text("Capturar tarea",color=Color.White,fontWeight=FontWeight.Bold,modifier=Modifier.weight(1f));Icon(Icons.Rounded.AutoAwesome,null,tint=Color.White)}
        Spacer(Modifier.height(24.dp))
        Box(Modifier.fillMaxWidth().weight(1f).clip(RoundedCornerShape(28.dp)).background(Color(0xFF29262D)).border(2.dp,Brush.linearGradient(listOf(Violet,Amber)),RoundedCornerShape(28.dp)),contentAlignment=Alignment.Center) {
            Column(horizontalAlignment=Alignment.CenterHorizontally) {
                Icon(Icons.Rounded.Description,null,tint=Color.White.copy(alpha=.92f),modifier=Modifier.size(88.dp));Spacer(Modifier.height(16.dp));Text(if(picked)"Analizando documento…" else "PDF, JPG, PNG o captura",color=Color.White,fontWeight=FontWeight.Bold)
                Text(if(picked)"La IA está extrayendo fechas y tareas" else "Selecciona un archivo o una imagen",color=Color.White.copy(alpha=.65f),fontSize=12.sp)
                if(picked){Spacer(Modifier.height(18.dp));LinearProgressIndicator(progress={progress},modifier=Modifier.width(220.dp),color=Violet,trackColor=Color.White.copy(alpha=.12f))}
            }
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick={picker.launch(arrayOf("application/pdf","image/*"))},modifier=Modifier.fillMaxWidth().height(56.dp),shape=RoundedCornerShape(18.dp)){Icon(Icons.Rounded.UploadFile,null);Spacer(Modifier.width(8.dp));Text("Elegir documento")}
        Spacer(Modifier.height(10.dp)); Text("También puedes compartir un archivo hacia TARE·IA desde otra app.",color=Color.White.copy(alpha=.55f),fontSize=11.sp)
    }
}

@Composable
fun AiResultScreen(nav: NavHostController) {
    Scaffold(containerColor=Canvas) { pad ->
        Column(Modifier.padding(pad).fillMaxSize().padding(20.dp)) {
            Row(verticalAlignment=Alignment.CenterVertically){IconButton(onClick={nav.popBackStack()}){Icon(Icons.Rounded.ArrowBack,null)};Box(Modifier.size(42.dp).clip(CircleShape).background(Lavender),contentAlignment=Alignment.Center){Icon(Icons.Rounded.AutoAwesome,null,tint=Violet)};Spacer(Modifier.width(10.dp));Column{Text("¡Tareas detectadas!",fontSize=24.sp,fontWeight=FontWeight.ExtraBold);Text("Revisa antes de confirmar",color=Muted)}}
            Spacer(Modifier.height(20.dp))
            DetectedCard("Informe de Base de Datos","Base de Datos I","30 de septiembre de 2026","Tarea",Coral)
            DetectedCard("Leer capítulo 4","Ingeniería de Software","1 de octubre de 2026","Lectura",Cyan)
            DetectedCard("Práctica de laboratorio","Programación III","2 de octubre de 2026","Tarea",Lime)
            Spacer(Modifier.weight(1f))
            Button(onClick={nav.navigate(Screen.Tasks.route){popUpTo(Screen.Capture.route){inclusive=true}}},Modifier.fillMaxWidth().height(54.dp),shape=RoundedCornerShape(17.dp)){Text("Guardar en mis tareas",fontWeight=FontWeight.Bold)}
            TextButton(onClick={nav.navigate(Screen.NewTask.route)},Modifier.fillMaxWidth()){Text("Editar información")}
        }
    }
}

@Composable private fun DetectedCard(title:String,course:String,date:String,type:String,tint:Color){
    Surface(Modifier.fillMaxWidth().padding(bottom=12.dp),shape=RoundedCornerShape(20.dp),color=Color.White,shadowElevation=2.dp){Row(Modifier.padding(16.dp)){Box(Modifier.width(5.dp).height(88.dp).clip(CircleShape).background(tint));Spacer(Modifier.width(12.dp));Column(Modifier.weight(1f)){Text(title,fontWeight=FontWeight.ExtraBold);Text("Curso: $course",fontSize=12.sp,color=Muted);Text("Entrega: $date",fontSize=12.sp,color=Muted);Text("Tipo: $type",fontSize=12.sp,color=Muted)};Checkbox(true,{})}}
}
