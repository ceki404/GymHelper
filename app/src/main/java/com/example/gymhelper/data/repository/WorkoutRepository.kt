package com.example.gymhelper.data.repository

import com.example.gymhelper.data.local.WorkoutDao
import com.example.gymhelper.data.model.WorkoutData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class WorkoutRepository(
    private val workoutDao: WorkoutDao,
    private val firestore: FirebaseFirestore
) {
    fun getWorkoutsForUser(userId: String): Flow<List<WorkoutData>> {
        return workoutDao.getWorkoutsForUser(userId)
    }

    suspend fun insertWorkout(workout: WorkoutData) {
        workoutDao.insertWorkout(workout)
        syncToCloud(workout)
    }

    suspend fun updateWorkout(workout: WorkoutData) {
        workoutDao.updateWorkout(workout)
        syncToCloud(workout)
    }

    suspend fun deleteWorkout(workout: WorkoutData) {
        workoutDao.deleteWorkout(workout)
        deleteFromCloud(workout)
    }

    private suspend fun syncToCloud(workout: WorkoutData) {
        firestore.collection("workouts")
            .document(workout.id.toString())
            .set(workout)
            .await()
    }

    private suspend fun deleteFromCloud(workout: WorkoutData) {
        firestore.collection("workouts")
            .document(workout.id.toString())
            .delete()
            .await()
    }

    suspend fun syncFromCloud(userId: String) {
        val cloudWorkouts = firestore.collection("workouts")
            .whereEqualTo("userId", userId)
            .get()
            .await()

        for (document in cloudWorkouts) {
            val workout = document.toObject(WorkoutData::class.java)
            workoutDao.insertWorkout(workout)
        }
    }
}