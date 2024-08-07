package com.example.projetoyoutube.data

import com.example.projetoyoutube.model.TaskModel
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun addTask(taskEntity: TaskModel) {
        taskDao.addTask(taskEntity)
    }

     fun getAllTask(): Flow<List<TaskModel>> {
        return taskDao.getAllTasks()
    }

     fun getTaskById(id: Int): Flow<TaskModel> {
        return taskDao.getTaskById(id)
    }

    suspend fun updateTask(taskEntity: TaskModel) {
        return taskDao.updateTask(taskEntity)
    }

    suspend fun deleteTask(taskEntity: TaskModel) {
        return taskDao.deleteTask(taskEntity)
    }

}