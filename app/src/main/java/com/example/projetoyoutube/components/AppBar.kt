package com.example.projetoyoutube.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    titleAppBar: TitleAppBar,
    onBackNavClicked: () -> Unit
) {

    TopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(10.dp),
                text = titleAppBar.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        navigationIcon = {
            if (titleAppBar.title != TitleAppBar.LIST_TASK.title) {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.Black
                    )
                }
            }
        }
    )
}

enum class TitleAppBar(val title: String) {
    LIST_TASK("Todo List"),
    SAVE_TASK("Salva Tarefa")
}