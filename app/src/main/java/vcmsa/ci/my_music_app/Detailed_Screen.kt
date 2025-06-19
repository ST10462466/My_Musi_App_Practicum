// Samuella Shako Kitwa
// ST19462466
package com.example.travelpackingapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import vcmsa.ci.my_music_app.R

class DetailedScreen : AppCompatActivity() {

    // Function to format a full song detail
    private fun formatItem(index: Int): String {
        return """
            • songName: ${MainScreen.songsTitle[index]}
              songArtist: ${MainScreen.artistsName[index]}
              songRating: ${MainScreen.ratings[index]}
              songComments: ${MainScreen.userComments[index]}
        """.trimIndent()
    }

    // "displaySongs" is a function that displays all the songs in the TextView
    private fun displaySongs(textView: TextView) {
        if (MainScreen.songsTitle.isEmpty()) {
            textView.text = "No title's song found. Please add a title!"
            Toast.makeText(this, "The playlist is empty!", Toast.LENGTH_SHORT).show()
            Log.w("My_Music_App", "Tried to show the playlist but the title's song was empty.")
            return
        }

        val details = buildString {
            MainScreen.songsTitle.indices.forEach { index ->
                appendLine(formatItem(index))
                appendLine()
            }
        }

        textView.text = details
        Log.d("My_Music_App", "Displayed the playlist successfully!")
    }
    // Function to calculate the average rating
    private fun displayAverageRate(): Double {
        if (MainScreen.ratings.isEmpty()) {
            return 0.0  // Return 0 if no ratings exist
        }

        val totalRatings = MainScreen.ratings.sum() // Sum all ratings
        val averageR = totalRatings.toDouble() / MainScreen.ratings.size // Divide the total ratings by the number of ratings
        return averageR
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_screen)

        // Declaring and initialing variables
        val playlistBox: TextView = findViewById(R.id.playlistBox)
        val showMore: Button = findViewById(R.id.showButton)
        val averageButton: Button = findViewById(R.id.averageButton)
        val backButton: Button = findViewById(R.id.backButton)

        // Calling the function "displaySongs" to allow the user to enter the details of a song
        showMore.setOnClickListener {
            displaySongs(playlistBox)
        }
        // averageButton clicked to calculate and display the average rating
        averageButton.setOnClickListener {
            val averageRating = displayAverageRate()
            if (averageRating == 0.0) {
                Toast.makeText(this, "No ratings available to calculate average.", Toast.LENGTH_SHORT).show()
            } else {
                // Display the average rating in a toast or textview
                Toast.makeText(this, "Average Rating: $averageRating", Toast.LENGTH_SHORT).show()
                Log.d("My_Music_App", "Average Rating: $averageRating")
            }
        }

        // button clicked to go back to the main screen
        backButton.setOnClickListener {
            startActivity(Intent(this, MainScreen::class.java))
            Log.i("My_Music_App", "Returning to the main screen.")
        }
    }
}
