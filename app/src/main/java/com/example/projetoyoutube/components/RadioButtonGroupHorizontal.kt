package com.example.projetoyoutube.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.projetoyoutube.ui.theme.IconRadioGreen
import com.example.projetoyoutube.ui.theme.IconRadioYellow
import com.example.projetoyoutube.ui.theme.PurpleGrey90
import com.example.projetoyoutube.viewmodel.TaskViewModel

@Composable
fun RadioButtonGroupHorizontal(viewModel: TaskViewModel) {
    val options = listOf(1, 2)
//    var priority by remember { mutableStateOf(options[0]) }


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Status", color = PurpleGrey90)
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier

                    .clickable { viewModel.priority = option }
            ) {
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = when (option) {
                            1 -> {
                               IconRadioYellow
                            }

                            2 -> {
                                IconRadioGreen
                            }

                            else -> {
                                Color.Red
                            }
                        },
                        unselectedColor = when (option) {
                            1 -> {
                                IconRadioYellow
                            }

                           2 -> {
                                IconRadioGreen
                            }

                            else -> {
                                Color.Red
                            }
                        },

                        ),
                    selected = (option == viewModel.priority),
                    onClick = { viewModel.priority = option }
                )

            }
        }
    }
}