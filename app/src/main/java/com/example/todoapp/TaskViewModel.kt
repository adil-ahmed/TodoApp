package com.example.todoapp

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

class TaskViewModel: ViewModel() {
    /*var name = MutableLiveData<String>()
    var desc = MutableLiveData<String>()*/
    var taskItems = MutableLiveData<MutableList<TaskItem>>()

    init {
        taskItems.value = mutableListOf()
    }
    fun addTaskItem(newTask: TaskItem)
    {
        val list = taskItems.value
        list!!.add(newTask)
        taskItems.postValue(list)
    }

    fun updateTaskItem(id: UUID, name: String, desc: String, dueTime: LocalTime?)
    {
        val list = taskItems.value
        val task = list!!.find{ it.id == id}!!
        task.name = name
        task.desc = desc
        task.dueTime = dueTime
        taskItems.postValue(list)
    }

    fun setCompleted(taskItem: TaskItem)
    {
        val list = taskItems.value
        val task = list!!.find {it.id == taskItem.id}!!
        if (task.completedDate == null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                task.completedDate = LocalDate.now()
            }
        taskItems.postValue(list)

    }
}