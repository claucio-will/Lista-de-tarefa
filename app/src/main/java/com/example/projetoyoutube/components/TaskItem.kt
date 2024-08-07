package com.example.projetoyoutube.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetoyoutube.R
import com.example.projetoyoutube.model.TaskModel
import com.example.projetoyoutube.ui.theme.Purple80
import com.example.projetoyoutube.ui.theme.PurpleGrey90
import com.example.projetoyoutube.viewmodel.TaskViewModel


@Composable
fun TaskItem(
    taskModel: TaskModel,
    onClick: () -> Unit,
    onDeleteTask: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Purple80,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        onClick = { onClick() }) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = taskModel.title,
                fontWeight = FontWeight.Bold,
                color = PurpleGrey90,
                fontSize = 30.sp
            )
            Text(
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                text = taskModel.description,
                color = PurpleGrey90,
                fontStyle = FontStyle.Italic
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Image(
                    colorFilter = ColorFilter.tint(
                        when (taskModel.priority) {
                            1 -> {
                                Color.Green
                            }

                            2 -> {
                                Color.Yellow
                            }

                            else -> {
                                Color.Red
                            }
                        }
                    ),
                    modifier = Modifier.padding(0.dp, 10.dp),
                    painter = painterResource(id = R.drawable.baseline_circle_24),
                    contentDescription = ""
                )
                IconButton(onClick = {
                   onDeleteTask()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Voltar",
                        tint = PurpleGrey90
                    )
                }
            }
        }
    }
}

