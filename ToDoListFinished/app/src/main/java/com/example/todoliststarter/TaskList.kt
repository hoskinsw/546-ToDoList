package com.example.todoliststarter

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskList(
    tasks: List<String>,
    onDeleteTask: (String) -> Unit,
    onEditTask: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    /*****************/
    /* Do this fourth */
    /*****************/

    LazyColumn (
        modifier = modifier
    ) {
        items(tasks) { task ->
            TaskItem(
                taskText = task,
                onDelete = { onDeleteTask(task) },
                onEdit = { onEditTask(task) }
            )
        }
    }
}