package com.example.projetoyoutube.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetoyoutube.data.Graph
import com.example.projetoyoutube.data.TaskRepository
import com.example.projetoyoutube.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TaskViewModel(private val taskRepository: TaskRepository = Graph.taskRepository) :
    ViewModel() {

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var priority by mutableStateOf(1)


   // private val _tasks = MutableStateFlow<List<TaskModel>>(listOf())


    lateinit var getAllTasks: Flow<List<TaskModel>>

    fun getCompletedTasks(): Flow<List<TaskModel>> {
        return getAllTasks.map { tasks ->
            tasks.filter { task -> task.status == 2 }
        }
    }


    init {
        viewModelScope.launch {
            getAllTasks = taskRepository.getAllTask()
        }
    }

    fun onChangedTitle(newTitle: String) {
        title = newTitle
    }

    fun onChangedDescription(newDescription: String) {
        description = newDescription
    }

     fun getDateTime(): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        return LocalDateTime.now().format(formatter)
    }

    fun addTask(task: TaskModel) {
        viewModelScope.launch {
            taskRepository.addTask(task)
            title = ""
            description = ""
            priority = 1

        }
    }

    fun getTaskById(id: Int) {
        viewModelScope.launch {
            taskRepository.getTaskById(id)
        }
    }

    fun updateTask(task: TaskModel) {
        viewModelScope.launch {
            taskRepository.updateTask(task)
        }
    }

    fun deleteTask(task: TaskModel) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }

    fun validateFields() : Boolean{
        return (title.isEmpty() && description.isEmpty())
    }
}


