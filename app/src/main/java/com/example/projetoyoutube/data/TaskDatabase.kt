package com.example.projetoyoutube.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projetoyoutube.model.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}