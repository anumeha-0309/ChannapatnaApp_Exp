package com.example.channapatnaappv2

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText

class ArtistRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_registration)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        val editName = findViewById<TextInputEditText>(R.id.editName)
        val editExpertise = findViewById<TextInputEditText>(R.id.editExpertise)
        val editExperience = findViewById<TextInputEditText>(R.id.editExperience)
        val editPhone = findViewById<TextInputEditText>(R.id.editPhone)
        val editAddress = findViewById<TextInputEditText>(R.id.editAddress)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnUpload = findViewById<Button>(R.id.btnUpload)

        btnUpload.setOnClickListener {
            Toast.makeText(this, "Gallery opened (Simulated)", Toast.LENGTH_SHORT).show()
        }

        btnSubmit.setOnClickListener {
            val name = editName.text.toString()
            if (name.isEmpty()) {
                editName.error = "Name is required"
                return@setOnClickListener
            }
            
            // Simulating submission
            Toast.makeText(this, getString(R.string.registration_success), Toast.LENGTH_LONG).show()
            finish()
        }
    }
}