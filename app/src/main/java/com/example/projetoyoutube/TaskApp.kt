package com.example.projetoyoutube

import android.app.Application
import com.example.projetoyoutube.data.Graph

class TaskApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}