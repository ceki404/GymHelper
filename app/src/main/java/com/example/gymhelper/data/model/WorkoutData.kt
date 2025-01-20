package com.example.gymhelper.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val name: String,
    val date: Long,
    val exercises: List<Exercise>
)

data class Exercise(
    val name: String,
    val sets: List<Set>
)

data class Set(
    val weight: Float,
    val reps: Int
)