package com.example.projetoyoutube.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.projetoyoutube.viewmodel.TaskViewModel

@Composable
fun AlertDialogComponent(viewModel: TaskViewModel) {
    
    AlertDialog(
        title = { Text(text = viewModel.title)},
        text = { Text(text = viewModel.description)},
        onDismissRequest = {  }, 
        confirmButton = { }
    )
}