package com.example.projetoyoutube.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetoyoutube.model.TaskModel
import com.example.projetoyoutube.ui.theme.ProjetoYoutubeTheme
import com.example.projetoyoutube.ui.theme.PurpleGrey90
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TodoItemCard(
    todoItem: TaskModel,
    onClick: () -> Unit,
    onDeleteTask: () -> Unit,
    onStatusChange: () -> Unit
) {

    var isVisible by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()


    var isChecked  by remember { mutableStateOf(todoItem.status == 2) } // Estado derivado




    AnimatedVisibility(
        visible = isVisible,
        exit = fadeOut(animationSpec = tween(500)) + slideOutHorizontally(
            animationSpec = tween(
                500
            )
        )
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = { onClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Checkbox(
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        uncheckedColor = Color.Gray,
                        checkmarkColor = Color.White
                    ),
                    checked = isChecked ,
                    onCheckedChange = {
                        isChecked = it
                        todoItem.status = if (isChecked) 2 else 1
                        onStatusChange()

                    }
                )

                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        style = TextStyle(
                            textDecoration = if (todoItem.status == 2) TextDecoration.LineThrough else TextDecoration.None,
                            color = if (todoItem.status == 2) Color.Gray else Color.Black
                        ),
                        text = todoItem.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        style = TextStyle(
                            textDecoration = if (todoItem.status == 2) TextDecoration.LineThrough else TextDecoration.None,
                            color = if (todoItem.status == 2) Color.Gray else Color.Black
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        text = todoItem.description,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = todoItem.date,
                        color = Color.Gray,
                        fontSize = 10.sp
                    )

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TodoStatusChip(status = todoItem.status)
                    IconButton(onClick = {
                        isVisible = false
                        coroutineScope.launch {
                            delay(500)
                            onDeleteTask()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete",
                            tint = Color.Gray,
                            modifier = Modifier
                                .padding(start = 0.dp)
                                .size(20.dp)
                        )
                    }


                }
            }
        }
    }
}

@Composable
fun TodoStatusChip(status: Int) {
    val backgroundColor = when (status) {
        1 -> Color(0xFFFFA726)
        2 -> Color(0xFF66BB6A)
        else -> {
            Color(0xFFCE1515)
        }
    }
    val statusText = when (status) {
        1 -> "Pendente"
        2 -> "Feita"
        else -> {
            "Finalizada"
        }
    }
    Box(
        modifier = Modifier
            .background(color = backgroundColor, shape = MaterialTheme.shapes.small)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = statusText,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
    }
}

