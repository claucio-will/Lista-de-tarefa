package com.example.projetoyoutube.navigation

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
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
    val window = (LocalView.current.context as ComponentActivity).window

    SideEffect {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.White.toArgb()

        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true // Define ícones escuros para fundo claro
    }


    NavHost(navController = navHostController, startDestination = Screen.HomeView.route){
        composable(Screen.HomeView.route){
            HomeTaskView(navHostController = navHostController)
        }
        composable(Screen.SaveTaskView.route){
            SaveTaskView(navHostController = navHostController, viewModel)
        }
    }

}