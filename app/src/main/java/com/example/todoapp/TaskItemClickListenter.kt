package com.example.todoapp

interface TaskItemClickListenter {
    fun editTaskItem(taskItem: TaskItem)
    fun completeTaskItem(taskItem: TaskItem)
}