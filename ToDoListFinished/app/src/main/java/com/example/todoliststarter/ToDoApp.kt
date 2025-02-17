package com.example.todoliststarter

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Main App
@Composable
fun TodoApp() {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()

    NavHost(navController, startDestination = "task_list") {
        composable("task_list") {
            TaskListScreen(
                navController = navController,
                taskViewModel = taskViewModel
            )
        }
        composable("add_task") {
            AddTaskScreen(
                navController = navController,
                taskViewModel = taskViewModel
            )
        }
    }
}