package com.example.projetoyoutube.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import com.example.projetoyoutube.components.AppBar
import com.example.projetoyoutube.components.TitleAppBar
import com.example.projetoyoutube.navigation.Screen
import com.example.projetoyoutube.ui.theme.Purple80
import com.example.projetoyoutube.ui.theme.PurpleGrey90
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projetoyoutube.components.TodoItemCard
import com.example.projetoyoutube.model.TaskModel
import com.example.projetoyoutube.ui.theme.BackgroundScaffold
import com.example.projetoyoutube.ui.theme.PurpleGrey80
import com.example.projetoyoutube.viewmodel.TaskViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeTaskView(navHostController: NavHostController, viewModel: TaskViewModel = viewModel()) {

    var selectedTask by remember { mutableStateOf<TaskModel?>(null) }
    var showDialog by remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier,
        containerColor = BackgroundScaffold,
        topBar = {

            AppBar(
                titleAppBar = TitleAppBar.LIST_TASK
            ) {}
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 10.dp
                ),
                containerColor = Color.Black,
                contentColor = Color.White,
                text = { Text(text = "Nova") },
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Criar nova Tarefa"
                    )
                },
                onClick = {
                   navHostController.navigate(Screen.SaveTaskView.route)

                }
            )
        }
    )
    {
        //Obtendo a lista de tarefas
        val taskList = viewModel.getAllTasks.collectAsState(initial = listOf())
        if (taskList.value.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Lista Vazia", color = PurpleGrey90)
            }
        }
        LazyColumn(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .fillMaxSize()
                .padding(it)
        ) {
            items(taskList.value.reversed(), key = { task -> task.id }) {
                if (selectedTask != null && selectedTask == it) {
                    AlertDialog(
                        containerColor = Color.Black,
                        textContentColor = Color.Gray,
                        titleContentColor = Color.White,
                        onDismissRequest = { selectedTask = null },
                        title = { Text(selectedTask?.title ?: "".uppercase(), fontSize = 30.sp, modifier = Modifier.padding(bottom = 10.dp)) },
                        text = {
                            Box(
                                modifier = Modifier
                                    .heightIn(min = 200.dp, max = 200.dp)
                                    .width(400.dp)
                                    .verticalScroll(
                                        rememberScrollState()
                                    )
                            ) {
                                Text(
                                    selectedTask?.description ?: "",
                                    fontSize = 16.sp,
                                    fontStyle = FontStyle.Italic
                                )
                            }
                        },
                        confirmButton = {
                            TextButton(onClick = { selectedTask = null }) {
                                Text("Fechar", color = Color.White)
                            }
                        },
                    )
                }
                TodoItemCard(
                    todoItem = it,
                    onClick = { selectedTask = it },
                    onDeleteTask = { viewModel.deleteTask(it) },
                    onStatusChange = {
                        viewModel.updateTask(
                            it.copy(
                                status = it.status
                            ))
                    }
                )
            }
        }


    }
}