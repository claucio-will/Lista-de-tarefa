package com.example.projetoyoutube.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projetoyoutube.R
import com.example.projetoyoutube.components.TodoItemCard
import com.example.projetoyoutube.model.TaskModel
import com.example.projetoyoutube.navigation.Screen
import com.example.projetoyoutube.ui.theme.BackgroundScaffold
import com.example.projetoyoutube.ui.theme.PurpleGrey90
import com.example.projetoyoutube.viewmodel.TaskViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeTaskView(navHostController: NavHostController, viewModel: TaskViewModel = viewModel()) {

    //Selecionando a tarefa para exibir o alertDialog
    var selectedTask by remember { mutableStateOf<TaskModel?>(null) }

    //Todas as tarefas do banco de dados
    val taskList = viewModel.getAllTasks.collectAsState(initial = listOf())

    // Estado para controlar a seleção do filtro
    var filterOption by remember { mutableStateOf(FilterOption.All) }

    // Estado para controlar a visibilidade do menu suspenso
    var expanded by remember { mutableStateOf(false) }

    // Filtrando as tarefas com base na opção selecionada
    val filteredTasks = when (filterOption) {
        FilterOption.All -> taskList.value
        FilterOption.Completed -> taskList.value.filter { it.status == 2 }
        FilterOption.Pending -> taskList.value.filter { it.status == 1 }
    }

    Scaffold(
        modifier = Modifier,
        containerColor = BackgroundScaffold,
        topBar = {
            TopAppBar(
                modifier = Modifier.shadow(8.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text(
                        text = "TodoList", fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                actions = {
                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                modifier = Modifier
                                    .padding(10.dp, 0.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_filter),
                                contentDescription = "filter",
                                tint = Color.Black
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background(Color.White)
                        ) {
                            DropdownMenuItem(
                                text = { Text("Todas as Tarefas", color = Color.Black) },
                                onClick = {
                                    filterOption = FilterOption.All
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Tarefas Completas",color = Color.Black) },
                                onClick = {
                                    filterOption = FilterOption.Completed
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Tarefas Pendentes",color = Color.Black) },
                                onClick = {
                                    filterOption = FilterOption.Pending
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            )
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
        //Mostra mensagem caso a lista esteja vaiza
        if (filteredTasks.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Nenhuma tarefa", color = PurpleGrey90)
            }
        }
        LazyColumn(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .fillMaxSize()
                .padding(it)
        ) {
            //Monstrando a lista de acordo com o filtro
            items(filteredTasks.reversed(), key = { task -> task.id }) {
                if (selectedTask != null && selectedTask == it) {
                    AlertDialog(
                        containerColor = Color.Black,
                        textContentColor = Color.Gray,
                        titleContentColor = Color.White,
                        onDismissRequest = { selectedTask = null },
                        title = {
                            Text(
                                selectedTask?.title ?: "".uppercase(),
                                fontSize = 30.sp,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                        },
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
                            )
                        )
                    }
                )
            }
        }

    }
}

enum class FilterOption {
    All, Completed, Pending
}