package com.example.projetoyoutube.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.projetoyoutube.ui.theme.Purple80
import com.example.projetoyoutube.ui.theme.PurpleGrey90

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    titleAppBar: TitleAppBar,
    onBackNavClicked: () -> Unit
) {

    TopAppBar(
        title = {
            Text(text = titleAppBar.title)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Purple80,
            titleContentColor = PurpleGrey90
        ),
        navigationIcon = {
            if (titleAppBar.title != TitleAppBar.LIST_TASK.title) {
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = PurpleGrey90
                    )
                }
            }
        }
    )
}

enum class TitleAppBar(val title: String) {
    LIST_TASK("Lista de Tarefa"),
    SAVE_TASK("Salva Tarefa")
}