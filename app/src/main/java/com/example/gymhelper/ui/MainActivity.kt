package com.example.gymhelper.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gymhelper.databinding.ActivityMainBinding
import com.example.gymhelper.ui.login.LoginActivity
import com.example.gymhelper.ui.workout.WorkoutActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser
        binding.welcomeText.text = "Welcome, ${user?.displayName}"

        binding.startWorkoutButton.setOnClickListener {
            startActivity(Intent(this, WorkoutActivity::class.java))
        }

        binding.signOutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

