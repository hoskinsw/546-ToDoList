package com.example.todoliststarter

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.navigation.NavHostController

// Task List Screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavHostController, taskViewModel: TaskViewModel) {
    /*****************/
    /* Do this sixth */
    /*****************/

    val tasks by taskViewModel.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todo List") },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Yellow)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_task") }) {
                Text("+")
            }
        }
    ) { padding ->
        TaskList(
            tasks,
            { input -> taskViewModel.removeTask(input) },
            { /*input -> taskViewModel.editTask(input)*/ },
            modifier = Modifier.padding(padding)
        )
    }
}