package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindings: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindings.root)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        bindings.newTaskButton.setOnClickListener{
            NewTaskSheet().show(supportFragmentManager, "newTaskTag")
        }
        taskViewModel.name.observe(this){
            bindings.taskName.text = String.format("Task Name: %s", it)
        }

        taskViewModel.desc.observe(this){
            bindings.taskDesc.text = String.format("Task Desc: %s", it)
        }
    }
}