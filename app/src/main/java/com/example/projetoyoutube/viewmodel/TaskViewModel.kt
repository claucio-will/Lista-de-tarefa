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
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository = Graph.taskRepository) :
    ViewModel() {

    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var priority by mutableStateOf(1)


    private val _tasks = MutableStateFlow<List<TaskModel>>(listOf())




    lateinit var getAllTasks: Flow<List<TaskModel>>

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

    fun updateTaskPriority(task: TaskModel) {
        viewModelScope.launch {
            // Aqui você deve atualizar a tarefa no repositório ou banco de dados
            // Exemplo:
            val updatedTasks = _tasks.value.map {
                if (it.id == task.id) it.copy(priority = task.priority) else it
            }
            _tasks.value = updatedTasks
        }
    }

    fun addTask(task: TaskModel) {
        viewModelScope.launch {
            taskRepository.addTask(task)
            title = ""
            description = ""
            priority = false
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


