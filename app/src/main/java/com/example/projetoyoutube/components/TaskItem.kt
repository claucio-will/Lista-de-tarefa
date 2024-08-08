package com.example.projetoyoutube.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.projetoyoutube.ui.theme.IconRadioGreen
import com.example.projetoyoutube.ui.theme.Purple80
import com.example.projetoyoutube.ui.theme.PurpleGrey90
import com.example.projetoyoutube.viewmodel.TaskViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun TaskItem(
    taskModel: TaskModel,
    onClick: () -> Unit,
    onDeleteTask: () -> Unit,
    onEditTask: () -> Unit
) {

    var isVisible by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = isVisible,
        exit = fadeOut(animationSpec = tween(500)) + slideOutHorizontally(
            animationSpec = tween(
                500
            )
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .clickable { onClick() },
            colors = CardDefaults.cardColors(
                containerColor = Purple80,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            onClick = { onClick() }) {
            Column(modifier = Modifier.padding(16.dp)) {
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
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = "Prioridade:" , color = PurpleGrey90 )
                    Icon(
                        tint =
                            when (taskModel.priority) {
                                1 -> { IconRadioGreen }
                                2 -> { Color.Yellow }
                                else -> { Color.Red }
                            },
                        modifier = Modifier
                            .padding(0.dp, 10.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.baseline_circle_24),
                        contentDescription = "status"
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        onEditTask()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Editar",
                            tint = PurpleGrey90
                        )
                    }
                    IconButton(onClick = {
                        isVisible = false
                        coroutineScope.launch {
                            delay(500)
                            onDeleteTask()
                        }
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
}

