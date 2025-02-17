package com.example.todoliststarter

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Create DataStore instance
private val Context.dataStore by preferencesDataStore(name = "tasks")

class TaskRepository(private val context: Context) {
    /*****************/
    /* Do this first */
    /*****************/

    // Define a key to store tasks as a String Set
    private val TASKS_KEY = stringSetPreferencesKey("tasks_key")

    // Retrieve tasks from DataStore
    val tasks: Flow<List<String>> = context.dataStore.data.map { preferences ->
        preferences[TASKS_KEY]?.toList() ?: emptyList()
    }

    // Save a new task to DataStore
    suspend fun addTask(task: String) {
        context.dataStore.edit { preferences ->
            val currentTasks = preferences[TASKS_KEY] ?: emptySet()
            preferences[TASKS_KEY] = currentTasks + task
        }
    }

    suspend fun removeTask(task: String) {
        context.dataStore.edit { preferences ->
            // Get the current list of tasks
            val currentTasks = preferences[TASKS_KEY]?.toMutableList() ?: mutableListOf()

            // Remove the specified task
            currentTasks.remove(task)

            // Save the updated list back to the preferences
            preferences[TASKS_KEY] = currentTasks.toSet()
        }
    }

    // Optionally: Clear tasks
    suspend fun clearTasks() {
        context.dataStore.edit { preferences ->
            preferences[TASKS_KEY] = emptySet()
        }
    }
}
