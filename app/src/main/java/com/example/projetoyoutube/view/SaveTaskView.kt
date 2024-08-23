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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.projetoyoutube.model.TaskModel
import com.example.projetoyoutube.ui.theme.BackgroundScaffold
import com.example.projetoyoutube.ui.theme.ProjetoYoutubeTheme
import com.example.projetoyoutube.viewmodel.TaskViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SaveTaskView(navHostController: NavHostController, viewModel: TaskViewModel) {
    val context = LocalContext.current
    Scaffold(
        containerColor = BackgroundScaffold,
        topBar = {

            TopAppBar(
                modifier = Modifier.shadow(8.dp),
                title = {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "Salvar tarefa",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.Black
                        )
                    }

                }
            )
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
                    unfocusedTextColor = Color.Gray,
                    focusedBorderColor = Color.Black,
                ),
                label = { Text(text = "Titulo", color = Color.Black) },
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
                    unfocusedTextColor = Color.Gray,
                    focusedBorderColor = Color.Black,

                    ),
                label = { Text(text = "Descrição", color = Color.Black) },
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
            //RadioButtonGroupHorizontal(viewModel)
            ElevatedButton(
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    if (viewModel.title.isNotEmpty()) {
                        viewModel.addTask(
                            TaskModel(
                                title = viewModel.title,
                                description = viewModel.description,
                                status = viewModel.priority,
                                date = viewModel.getDateTime()
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