package com.example.examplelevel3.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class RemindersViewModel : ViewModel() {
    val reminders: MutableList<String> = mutableStateListOf()
    /**
     * Add new reminder to the existing list.
     */
    fun addReminder(newReminder: String) {
        reminders.add(newReminder)
    }
}
