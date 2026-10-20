package com.upt.tareia.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.upt.tareia.ui.components.BrandMark
import com.upt.tareia.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onDone: () -> Unit) {
    LaunchedEffect(Unit) { delay(1350); onDone() }
    Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(Color(0xFF20194D), Color(0xFF5336A7), Color(0xFF7A55F6))))) {
        Box(Modifier.size(260.dp).offset(x = 180.dp, y = (-40).dp).clip(CircleShape).background(Color.White.copy(alpha=.06f)))
        Box(Modifier.size(190.dp).offset(x = (-70).dp, y = 580.dp).clip(CircleShape).background(Cyan.copy(alpha=.12f)))
        Column(Modifier.fillMaxSize().padding(30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Icon(Icons.Rounded.School, null, tint = Color.White, modifier = Modifier.size(58.dp))
            Spacer(Modifier.height(18.dp))
            BrandMark(light = true)
            Spacer(Modifier.height(12.dp))
            Text("Tus tareas, ahora con inteligencia.", color = Color.White.copy(alpha=.92f), fontSize = 17.sp)
            Spacer(Modifier.height(58.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                listOf(Icons.Rounded.Description, Icons.Rounded.AutoAwesome, Icons.Rounded.CalendarMonth).forEach { icon ->
                    Box(Modifier.size(64.dp).clip(RoundedCornerShape(20.dp)).background(Color.White.copy(alpha=.12f)), contentAlignment = Alignment.Center) { Icon(icon, null, tint = Color.White, modifier = Modifier.size(30.dp)) }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLogin: () -> Unit, onRegister: () -> Unit) {
    var email by remember { mutableStateOf("antony@upt.edu.pe") }
    var pass by remember { mutableStateOf("tareia2026") }
    Column(Modifier.fillMaxSize().background(Canvas).padding(26.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(38.dp)); BrandMark(); Spacer(Modifier.height(8.dp))
        Text("Tu asistente académico inteligente", color = Muted)
        Spacer(Modifier.height(32.dp))
        OutlinedTextField(email, { email=it }, modifier=Modifier.fillMaxWidth(), label={Text("Correo institucional o personal")}, leadingIcon={Icon(Icons.Rounded.Mail,null)}, shape=RoundedCornerShape(16.dp))
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(pass, { pass=it }, modifier=Modifier.fillMaxWidth(), label={Text("Contraseña")}, leadingIcon={Icon(Icons.Rounded.Lock,null)}, visualTransformation=PasswordVisualTransformation(), shape=RoundedCornerShape(16.dp))
        TextButton(onClick={}) { Text("¿Olvidaste tu contraseña?") }
        Button(onClick=onLogin, modifier=Modifier.fillMaxWidth().height(54.dp), shape=RoundedCornerShape(16.dp)) { Text("Iniciar sesión", fontWeight=FontWeight.Bold) }
        Spacer(Modifier.height(18.dp)); Text("o continúa con", color=Muted, fontSize=12.sp); Spacer(Modifier.height(18.dp))
        OutlinedButton(onClick=onLogin, modifier=Modifier.fillMaxWidth().height(52.dp), shape=RoundedCornerShape(16.dp)) { Icon(Icons.Rounded.AccountCircle,null); Spacer(Modifier.width(10.dp)); Text("Continuar con Google") }
        Spacer(Modifier.height(10.dp))
        OutlinedButton(onClick=onLogin, modifier=Modifier.fillMaxWidth().height(52.dp), shape=RoundedCornerShape(16.dp)) { Icon(Icons.Rounded.Apps,null); Spacer(Modifier.width(10.dp)); Text("Continuar con Microsoft") }
        Spacer(Modifier.height(20.dp)); Row(verticalAlignment=Alignment.CenterVertically) { Text("¿No tienes cuenta? ", color=Muted); TextButton(onClick=onRegister) { Text("Regístrate") } }
    }
}

@Composable
fun RegisterScreen(onRegister: () -> Unit, onBack: () -> Unit) {
    var name by remember { mutableStateOf("Antony Solorzano") }; var mail by remember { mutableStateOf("antony@upt.edu.pe") }; var p1 by remember { mutableStateOf("") }; var p2 by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().background(Canvas).padding(24.dp), horizontalAlignment=Alignment.CenterHorizontally) {
        Row(Modifier.fillMaxWidth(), verticalAlignment=Alignment.CenterVertically) { IconButton(onClick=onBack){Icon(Icons.Rounded.ArrowBack,null)}; Spacer(Modifier.weight(1f)) }
        BrandMark(); Text("Crea tu cuenta para comenzar", fontWeight=FontWeight.Bold, fontSize=18.sp); Text("Comienza a organizar tu vida académica.", color=Muted)
        Spacer(Modifier.height(22.dp))
        OutlinedTextField(name,{name=it},Modifier.fillMaxWidth(),label={Text("Nombre completo")},leadingIcon={Icon(Icons.Rounded.Person,null)},shape=RoundedCornerShape(16.dp))
        Spacer(Modifier.height(10.dp)); OutlinedTextField(mail,{mail=it},Modifier.fillMaxWidth(),label={Text("Correo institucional")},leadingIcon={Icon(Icons.Rounded.Mail,null)},shape=RoundedCornerShape(16.dp))
        Spacer(Modifier.height(10.dp)); OutlinedTextField(p1,{p1=it},Modifier.fillMaxWidth(),label={Text("Contraseña")},leadingIcon={Icon(Icons.Rounded.Lock,null)},visualTransformation=PasswordVisualTransformation(),shape=RoundedCornerShape(16.dp))
        Spacer(Modifier.height(10.dp)); OutlinedTextField(p2,{p2=it},Modifier.fillMaxWidth(),label={Text("Confirmar contraseña")},leadingIcon={Icon(Icons.Rounded.Lock,null)},visualTransformation=PasswordVisualTransformation(),shape=RoundedCornerShape(16.dp))
        Spacer(Modifier.height(16.dp)); Row(verticalAlignment=Alignment.CenterVertically){ Checkbox(true,{}); Text("Acepto los Términos y la Política de Privacidad",fontSize=12.sp,color=Muted) }
        Spacer(Modifier.height(12.dp)); Button(onClick=onRegister,Modifier.fillMaxWidth().height(54.dp),shape=RoundedCornerShape(16.dp)){Text("Registrarme",fontWeight=FontWeight.Bold)}
    }
}
