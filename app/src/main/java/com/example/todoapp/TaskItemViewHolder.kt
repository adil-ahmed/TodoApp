package com.example.todoapp

import android.content.Context
import android.graphics.Paint
import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListenter
): RecyclerView.ViewHolder(binding.root) {
    private val timeFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        DateTimeFormatter.ofPattern("HH:mm")
    } else {
        TODO("VERSION.SDK_INT < O")
    }

    fun bindTaskItem(taskItem: TaskItem)
    {
        binding.name.text = taskItem.name
        if (taskItem.isCompleted()){
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        binding.completeButton.setImageResource(taskItem.imageResource())
        binding.completeButton.setColorFilter(taskItem.imageColor(context))

        binding.completeButton.setOnClickListener {
            clickListener.completeTaskItem(taskItem)
        }
        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItem(taskItem)
        }

        if(taskItem.dueTime != null)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                binding.dueTime.text = timeFormat.format(taskItem.dueTime)
            }
        }
        else
        {
            binding.dueTime.text = ""
        }
    }
}