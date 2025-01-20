package com.example.gymhelper.ui.workout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gymhelper.databinding.ActivityWorkoutBinding

class WorkoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

