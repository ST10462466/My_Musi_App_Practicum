
// Samuella Shako Kitwa
// ST19462466package com.example.travelpackingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.travelpackingapp.DetailedScreen
import vcmsa.ci.my_music_app.R


class MainScreen : AppCompatActivity() {

    companion object {
        val songsTitle = ArrayList<String>()
        val artistsName = ArrayList<String>()
        val ratings = ArrayList<Int>()
        val userComments = ArrayList<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        // Declaring and initialing variables
        val songNameBox= findViewById<EditText>(R.id.songtitle)
        val artistNameBox = findViewById<EditText>(R.id.artistname)
        val songRatingBox = findViewById<EditText>(R.id.ratingBox)
        val songCommentsBox = findViewById<EditText>(R.id.commentsBox)
        val songDetails = findViewById<Button>(R.id.addButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        songDetails.setOnClickListener {
            // The user enters the details of his song
            val songTitleInput = songNameBox.text.toString().trim()
            val artistNameInput = artistNameBox.text.toString().trim()
            val songRatingInput = songRatingBox.text.toString().trim()
            val commentsInput = songCommentsBox.text.toString().trim()

            // Validation of what the user entered
            if (songTitleInput.isEmpty() || artistNameInput.isEmpty() || songRatingInput.isEmpty()) {
                Toast.makeText(this, "Please fill all the required fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Input validation for the rating of the song
            val rating = songRatingInput.toIntOrNull()
            if (rating == null || rating <1 || rating > 5) {
                Toast.makeText(this, "Please enter a rating between 1 and 5.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Add to inputs to parallel arrays
            songsTitle.add(songTitleInput)
            artistsName.add(artistNameInput)
            ratings.add(rating)
            userComments.add(commentsInput)

            Log.d("My_Music_App", "Song added successfully!: $songTitleInput, $artistNameInput, $rating, $commentsInput")

            Toast.makeText(this, "Item added!", Toast.LENGTH_SHORT).show()

            // Clear all the input boxes
            songNameBox.text.clear()
            artistNameBox.text.clear()
            songRatingBox.text.clear()
            songCommentsBox.text.clear()
        }

        nextButton.setOnClickListener {
            startActivity(Intent(this, DetailedScreen::class.java)) // Move unto the next screen
        }

        exitButton.setOnClickListener {
            finishAffinity() // exit completely the app and clean everything
        }
    }
}
