package com.example.projetoyoutube.view

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.projetoyoutube.components.AppBar
import com.example.projetoyoutube.components.TitleAppBar
import com.example.projetoyoutube.navigation.Screen
import com.example.projetoyoutube.ui.theme.Purple80
import com.example.projetoyoutube.ui.theme.PurpleGrey90
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projetoyoutube.components.TaskItem
import com.example.projetoyoutube.model.TaskModel
import com.example.projetoyoutube.ui.theme.BackgroundScaffold
import com.example.projetoyoutube.ui.theme.PurpleGrey80
import com.example.projetoyoutube.viewmodel.TaskViewModel
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeTaskView(navHostController: NavHostController, viewModel: TaskViewModel = viewModel()) {

    var selectedTask by remember { mutableStateOf<TaskModel?>(null) }
    var isVisible by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    Scaffold(
        containerColor = BackgroundScaffold,
        topBar = {
            AppBar(
                titleAppBar = TitleAppBar.LIST_TASK
            ) {}
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                containerColor = Purple80,
                contentColor = PurpleGrey90,
                text = { Text(text = "Criar") },
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
                Text(text = "Lista Vazia", color = Purple80)
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(taskList.value.reversed(), key = { task -> task.id }) {
                if (selectedTask != null && selectedTask == it) {
                    AlertDialog(
                        onDismissRequest = { selectedTask = null },
                        title = { Text(selectedTask?.title ?: "", fontSize = 25.sp) },
                        text = {
                            Box(
                                modifier = Modifier
                                    .heightIn(min = 100.dp, max = 200.dp)
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
                                Text("Fechar")
                            }
                        },
                    )
                }
                TaskItem(
                    taskModel = it,
                    onClick = {
                        selectedTask = it
                    },
                    onDeleteTask = {
                        viewModel.deleteTask(it)
                    },
                    onEditTask = {
                        viewModel.updateTask(it)
                    }
                )
            }
        }


    }
}