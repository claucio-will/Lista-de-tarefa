package com.example.projetoyoutube.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projetoyoutube.view.HomeTaskView
import com.example.projetoyoutube.view.SaveTaskView
import com.example.projetoyoutube.viewmodel.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SetupNavigation(
    navHostController: NavHostController =  rememberNavController(),
    viewModel: TaskViewModel = viewModel(),

    ) {


    NavHost(navController = navHostController, startDestination = Screen.HomeView.route){
        composable(Screen.HomeView.route){
            HomeTaskView(navHostController = navHostController)
        }
        composable(Screen.SaveTaskView.route){
            SaveTaskView(navHostController = navHostController, viewModel)
        }
    }

}