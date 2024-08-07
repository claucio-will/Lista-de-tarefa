package com.example.projetoyoutube.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projetoyoutube.components.AppBar
import com.example.projetoyoutube.components.RadioButtonGroupHorizontal
import com.example.projetoyoutube.components.TitleAppBar
import com.example.projetoyoutube.model.TaskModel
import com.example.projetoyoutube.ui.theme.BackgroundScaffold
import com.example.projetoyoutube.ui.theme.ProjetoYoutubeTheme
import com.example.projetoyoutube.ui.theme.Purple80
import com.example.projetoyoutube.ui.theme.PurpleGrey90
import com.example.projetoyoutube.viewmodel.TaskViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SaveTaskView(navHostController: NavHostController, viewModel: TaskViewModel) {
    val context = LocalContext.current
    Scaffold(
        containerColor = BackgroundScaffold,
        topBar = {
            AppBar(titleAppBar = TitleAppBar.SAVE_TASK, onBackNavClicked = {
                navHostController.navigateUp()
            })
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            OutlinedTextField(
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Gray,
                    unfocusedTextColor = Color.Gray
                ),
                label = { Text(text = "Titulo") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 13.dp),
                value = viewModel.title,
                onValueChange = {
                    viewModel.onChangedTitle(it)

                })

            OutlinedTextField(
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Gray,
                    unfocusedTextColor = Color.Gray
                ),
                label = { Text(text = "Descrição") },
                singleLine = false,
                maxLines = 3,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp, max = 300.dp)
                    .padding(16.dp, 0.dp),
                value = viewModel.description,
                onValueChange = {
                    viewModel.onChangedDescription(it)

                })
            Spacer(modifier = Modifier.height(5.dp))
            RadioButtonGroupHorizontal(viewModel)
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple80,
                    contentColor = PurpleGrey90,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    if (viewModel.title.isNotEmpty() && viewModel.description.isNotEmpty()) {
                        viewModel.addTask(
                            TaskModel(
                                title = viewModel.title,
                                description = viewModel.description,
                                priority = viewModel.priority
                            )
                        )
                        navHostController.navigateUp()
                    } else {
                        Toast.makeText(context, "Preencha os campos", Toast.LENGTH_SHORT).show()
                    }

                },
            ) {
                Text(text = "Salvar")
            }
        }

    }
}

@Preview
@Composable
private fun SaveTaskPreview() {
    ProjetoYoutubeTheme {
        //SaveTaskView(navHostController = rememberNavController())

    }

}