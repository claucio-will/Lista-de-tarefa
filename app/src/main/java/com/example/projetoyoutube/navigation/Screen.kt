package com.example.projetoyoutube.navigation

sealed class Screen(val route: String){
    object HomeView: Screen(route = "home_view")
    object SaveTaskView: Screen(route = "save_task_view")
}