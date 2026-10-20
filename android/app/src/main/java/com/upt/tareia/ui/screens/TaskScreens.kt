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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.upt.tareia.domain.*
import com.upt.tareia.ui.components.*
import com.upt.tareia.ui.navigation.Screen
import com.upt.tareia.ui.theme.*
import com.upt.tareia.ui.viewmodel.TasksViewModel

@Composable
fun TasksScreen(nav:NavHostController, vm:TasksViewModel= viewModel()){
    val tasks by vm.tasks.collectAsState(); var filter by remember{ mutableStateOf("Todas") }
    Scaffold(bottomBar={TareiaBottomBar(nav,Screen.Tasks.route)},floatingActionButton={FloatingActionButton(onClick={nav.navigate(Screen.NewTask.route)},containerColor=Violet,contentColor=Color.White){Icon(Icons.Rounded.Add,null)}}){pad->
        Column(Modifier.padding(pad).fillMaxSize().background(Canvas).padding(18.dp)){
            PageTitle("Mis tareas","Todo lo que tienes por hacer")
            Spacer(Modifier.height(14.dp)); OutlinedTextField("",{},Modifier.fillMaxWidth(),placeholder={Text("Buscar tarea...")},leadingIcon={Icon(Icons.Rounded.Search,null)},shape=RoundedCornerShape(16.dp))
            Spacer(Modifier.height(12.dp)); Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){listOf("Todas","Pendientes","Completadas").forEach{f->FilterChip(filter==f,{filter=f},{Text(f)})}}
            Spacer(Modifier.height(12.dp))
            val visible=tasks.filter{ when(filter){"Pendientes"->it.status!=TaskStatus.COMPLETADA;"Completadas"->it.status==TaskStatus.COMPLETADA;else->true}}
            visible.forEach{TaskCard(it,{nav.navigate(Screen.TaskDetail.create(it.id))},Modifier.padding(bottom=9.dp))}
        }
    }
}

@Composable
fun TaskDetailScreen(nav:NavHostController,id:Int,vm:TasksViewModel= viewModel()){
    val task=vm.find(id) ?: return
    Scaffold(containerColor=Canvas){pad->Column(Modifier.padding(pad).fillMaxSize().padding(20.dp)){
        Row(verticalAlignment=Alignment.CenterVertically){IconButton(onClick={nav.popBackStack()}){Icon(Icons.Rounded.ArrowBack,null)};Text(task.title,fontSize=22.sp,fontWeight=FontWeight.ExtraBold,modifier=Modifier.weight(1f));IconButton(onClick={}){Icon(Icons.Rounded.MoreVert,null)}}
        Row(horizontalArrangement=Arrangement.spacedBy(8.dp)){AssistChip({}, {Text(task.type.name.lowercase().replaceFirstChar{it.uppercase()})},leadingIcon={Icon(Icons.Rounded.TaskAlt,null,Modifier.size(16.dp))});AssistChip({}, {Text(if(task.status==TaskStatus.PENDIENTE_VALIDAR)"Pendiente" else "Confirmada")})}
        Spacer(Modifier.height(18.dp));InfoLine(Icons.Rounded.School,"Curso",task.course);InfoLine(Icons.Rounded.Description,"Descripción",task.description);InfoLine(Icons.Rounded.CalendarToday,"Fecha de entrega","${task.dueDate}\n${task.dueTime}");InfoLine(Icons.Rounded.PriorityHigh,"Prioridad",task.priority.name.lowercase().replaceFirstChar{it.uppercase()})
        if(task.attachment!=null) InfoLine(Icons.Rounded.AttachFile,"Archivo adjunto",task.attachment)
        Spacer(Modifier.weight(1f));Button(onClick={vm.complete(id)},Modifier.fillMaxWidth().height(54.dp),shape=RoundedCornerShape(16.dp)){Text("Marcar como completada",fontWeight=FontWeight.Bold)}
        Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceEvenly){TextButton(onClick={nav.navigate(Screen.NewTask.route)}){Icon(Icons.Rounded.Edit,null);Spacer(Modifier.width(6.dp));Text("Editar")};TextButton(onClick={}){Icon(Icons.Rounded.Delete,null,tint=Coral);Spacer(Modifier.width(6.dp));Text("Eliminar",color=Coral)}}
    }}
}

@Composable private fun InfoLine(icon: androidx.compose.ui.graphics.vector.ImageVector,label:String,value:String){Row(Modifier.fillMaxWidth().padding(vertical=10.dp),verticalAlignment=Alignment.Top){Surface(shape=RoundedCornerShape(12.dp),color=Lavender){Icon(icon,null,tint=Violet,modifier=Modifier.padding(9.dp).size(20.dp))};Spacer(Modifier.width(12.dp));Column{Text(label,color=Muted,fontSize=12.sp);Text(value,fontWeight=FontWeight.SemiBold,color=Ink)}}}

@Composable
fun NewTaskScreen(nav:NavHostController,vm:TasksViewModel= viewModel()){
    var title by remember{mutableStateOf("")};var desc by remember{mutableStateOf("")};var course by remember{mutableStateOf("Base de Datos I")};var date by remember{mutableStateOf("30 sep 2026")}
    Scaffold(containerColor=Canvas){pad->Column(Modifier.padding(pad).fillMaxSize().padding(20.dp)){
        Row(verticalAlignment=Alignment.CenterVertically){IconButton(onClick={nav.popBackStack()}){Icon(Icons.Rounded.Close,null)};Text("Nueva tarea",fontSize=22.sp,fontWeight=FontWeight.ExtraBold,modifier=Modifier.weight(1f));FilledIconButton(onClick={vm.create(title.ifBlank{"Nueva actividad"},desc,course,date,TaskType.TAREA,Priority.MEDIA);nav.popBackStack()}){Icon(Icons.Rounded.Check,null)}}
        Spacer(Modifier.height(12.dp));Field("Título",title,{title=it},"Ej. Informe, práctica, etc.");Field("Descripción",desc,{desc=it},"Detalles de la tarea...",true);Field("Curso",course,{course=it},"Curso");Field("Fecha de entrega",date,{date=it},"Fecha")
        Text("Tipo",fontWeight=FontWeight.Bold);Spacer(Modifier.height(6.dp));AssistChip(onClick={},label={Text("Tarea")},leadingIcon={Icon(Icons.Rounded.TaskAlt,null,Modifier.size(16.dp))});Spacer(Modifier.height(10.dp));Text("Prioridad",fontWeight=FontWeight.Bold);Spacer(Modifier.height(6.dp));AssistChip(onClick={},label={Text("Media")},leadingIcon={Icon(Icons.Rounded.Circle,null,tint=Amber,modifier=Modifier.size(12.dp))})
        Spacer(Modifier.height(18.dp));OutlinedButton(onClick={},Modifier.fillMaxWidth(),shape=RoundedCornerShape(15.dp)){Icon(Icons.Rounded.AttachFile,null);Spacer(Modifier.width(8.dp));Text("Agregar archivo")}
    }}
}
@Composable private fun Field(label:String,value:String,on:(String)->Unit,placeholder:String,multi:Boolean=false){Text(label,fontWeight=FontWeight.Bold);Spacer(Modifier.height(6.dp));OutlinedTextField(value,on,Modifier.fillMaxWidth().then(if(multi)Modifier.height(110.dp)else Modifier),placeholder={Text(placeholder)},shape=RoundedCornerShape(14.dp));Spacer(Modifier.height(12.dp))}
