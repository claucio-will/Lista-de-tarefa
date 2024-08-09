package com.example.projetoyoutube.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetoyoutube.R
import com.example.projetoyoutube.ui.theme.IconRadioGreen
import com.example.projetoyoutube.ui.theme.Purple80
import com.example.projetoyoutube.ui.theme.PurpleGrey90

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    titleAppBar: TitleAppBar,
    onBackNavClicked: () -> Unit
) {

//    var showAlertDialog by remember { mutableStateOf(false) }
//
//    if (showAlertDialog) {
//        AlertDialog(
//            onDismissRequest = { showAlertDialog = false },
//            title = { Text("Ordem de Prioridades") },
//            text = {
//                Column(
//
//                    verticalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceEvenly,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            tint = IconRadioGreen,
//                            modifier = Modifier
//                                .padding(5.dp, 0.dp)
//                                .clip(CircleShape),
//                            painter = painterResource(id = R.drawable.baseline_circle_24),
//                            contentDescription = "status"
//                        )
//                        Text(text = "Baixa")
//                    }
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceEvenly,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            tint = Color.Yellow,
//                            modifier = Modifier
//                                .padding(5.dp, 0.dp)
//                                .clip(CircleShape),
//                            painter = painterResource(id = R.drawable.baseline_circle_24),
//                            contentDescription = "status"
//                        )
//                        Text(text = "Media")
//                    }
//                    Row(
//                        horizontalArrangement = Arrangement.SpaceEvenly,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            tint = Color.Red,
//                            modifier = Modifier
//                                .padding(5.dp, 0.dp)
//                                .clip(CircleShape),
//                            painter = painterResource(id = R.drawable.baseline_circle_24),
//                            contentDescription = "status"
//                        )
//                        Text(text = "Alta")
//                    }
//                }
//            },
//            confirmButton = {
//                TextButton(onClick = { showAlertDialog = false }) {
//                    Text("OK")
//                }
//            },
//        )
//    }

    TopAppBar(
//        actions = {
//            IconButton(onClick = {
//                showAlertDialog = true
//            }) {
//                Icon(
//                    tint = PurpleGrey90,
//                    painter = painterResource(id = R.drawable.baseline_help_24),
//                    contentDescription = "Buscar"
//                )
//            }
//        },

        title = {
            Text(text = titleAppBar.title, fontWeight = FontWeight.Bold, fontSize = 24.sp)
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
                        tint = PurpleGrey90
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