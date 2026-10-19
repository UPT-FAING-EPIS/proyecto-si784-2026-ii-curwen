package com.tareia.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TareIAApp() }
    }
}

data class Task(val title:String, val course:String, val date:String, val done:Boolean=false)

data class Course(val name:String, val priority:String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareIAApp(){
    var section by remember { mutableStateOf("Inicio") }
    var tasks by remember { mutableStateOf(listOf(
        Task("Informe laboratorio SQL","Base de Datos II","05/10/2026"),
        Task("Proyecto Android","Soluciones Moviles I","20/10/2026")
    )) }
    var courses by remember { mutableStateOf(listOf(
        Course("Base de Datos II","Alta"),
        Course("Programacion III","Alta"),
        Course("Soluciones Moviles I","Media")
    )) }

    Scaffold(
        topBar={ TopAppBar(title={Text("TARE·IA")}) },
        bottomBar={
            NavigationBar{
                listOf("Inicio","Apuntes","Tareas","Calendario","Perfil").forEach{ item ->
                    NavigationBarItem(
                        selected = section==item,
                        onClick={section=item},
                        icon={},
                        label={Text(item)}
                    )
                }
            }
        }
    ){ padding ->
        Column(Modifier.padding(padding).padding(16.dp)){
            when(section){
                "Inicio" -> Home(tasks)
                "Apuntes" -> Notes()
                "Tareas" -> Tasks(tasks){ tasks=tasks + it }
                "Calendario" -> CalendarView(tasks)
                else -> Profile(courses)
            }
        }
    }
}

@Composable
fun Home(tasks:List<Task>){
    Text("Hola Eduardo 👋", style=MaterialTheme.typography.headlineSmall)
    Spacer(Modifier.height(15.dp))
    Card { Column(Modifier.padding(18.dp)){ 
        Text("Resumen academico")
        Text("${tasks.size} actividades registradas")
        Text("4 pendientes esta semana")
    }}
    Spacer(Modifier.height(20.dp))
    Text("Proximas entregas")
    tasks.forEach{
        Card(Modifier.fillMaxWidth().padding(vertical=5.dp)){
            Column(Modifier.padding(15.dp)){
                Text(it.course)
                Text(it.title)
                Text("Fecha: ${it.date}")
            }
        }
    }
}

@Composable
fun Notes(){
    Text("📚 Mis apuntes", style=MaterialTheme.typography.headlineSmall)
    Spacer(Modifier.height(15.dp))
    Card { Column(Modifier.padding(18.dp)){
        Text("Base de Datos II")
        Text("📄 Laboratorio SQL.pdf")
        Text("🖼 Captura clase.png")
    }}
    Spacer(Modifier.height(15.dp))
    Button(onClick={}){Text("Importar PDF / Imagen")}
}

@Composable
fun Tasks(tasks:List<Task>, add:(Task)->Unit){
    var show by remember { mutableStateOf(false) }
    Text("✅ Actividades", style=MaterialTheme.typography.headlineSmall)
    LazyColumn{
        items(tasks){ task ->
            Card(Modifier.fillMaxWidth().padding(5.dp)){
                Column(Modifier.padding(15.dp)){
                    Text(task.title)
                    Text(task.course)
                    Text(task.date)
                }
            }
        }
    }
    Button(onClick={show=true}){Text("Nueva actividad")}
    if(show){
        AlertDialog(
            onDismissRequest={show=false},
            confirmButton={Button(onClick={add(Task("Nueva actividad","BD2","10/10/2026"));show=false}){Text("Guardar")}},
            title={Text("Actividad creada")},
            text={Text("Ejemplo preparado para CRUD real")}
        )
    }
}

@Composable
fun CalendarView(tasks:List<Task>){
    Text("📅 Calendario", style=MaterialTheme.typography.headlineSmall)
    Spacer(Modifier.height(10.dp))
    tasks.forEach{ Text("• ${it.date}: ${it.title}") }
    Spacer(Modifier.height(20.dp))
    Text("Google Calendar API preparada en arquitectura")
}

@Composable
fun Profile(courses:List<Course>){
    Text("👤 Eduardo Flores", style=MaterialTheme.typography.headlineSmall)
    Text("Ingenieria de Sistemas")
    Spacer(Modifier.height(15.dp))
    Text("Cursos")
    courses.forEach{ Text("• ${it.name} - Prioridad ${it.priority}") }
}
