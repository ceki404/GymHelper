package com.example.gymhelper.data.local

import androidx.room.*
import com.example.gymhelper.data.model.WorkoutData
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workouts WHERE userId = :userId")
    fun getWorkoutsForUser(userId: String): Flow<List<WorkoutData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWorkout(workout: WorkoutData)

    @Update
    suspend fun updateWorkout(workout: WorkoutData)

    @Delete
    suspend fun deleteWorkout(workout: WorkoutData)
}