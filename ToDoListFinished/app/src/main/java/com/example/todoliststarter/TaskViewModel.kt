package com.example.todoliststarter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    /*****************/
    /* Do this second */
    /*****************/

    private val taskRepository = TaskRepository(application)

    private val _tasks = MutableStateFlow<List<String>>(emptyList())
    val tasks: StateFlow<List<String>> = _tasks

    init {
        // Load tasks from DataStore on initialization
        viewModelScope.launch {
            taskRepository.tasks.collect { savedTasks ->
                _tasks.value = savedTasks
            }
        }
    }

    fun addTask(task: String) {
        viewModelScope.launch {
            taskRepository.addTask(task)
        }
    }

    fun removeTask(task: String) {
        viewModelScope.launch {
            taskRepository.removeTask(task)
        }
    }

    fun clearTasks() {
        viewModelScope.launch {
            taskRepository.clearTasks()
        }
    }
}