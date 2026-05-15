package com.example.channapatnaappv2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Language toggle
        findViewById<ImageView>(R.id.btnLanguage).setOnClickListener {
            val intent = Intent(this, LanguageActivity::class.java)
            intent.putExtra("change_mode", true)
            startActivity(intent)
        }

        // Verify
        val btnVerify = findViewById<Button>(R.id.btnVerify)
        btnVerify.setOnClickListener {
            startActivity(Intent(this, VerifyActivity::class.java))
        }

        // How It's Made
        val btnHow = findViewById<Button>(R.id.btnHow)
        btnHow.setOnClickListener {
            startActivity(Intent(this, HowItsMadeActivity::class.java))
        }

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_catalog -> {
                    startActivity(Intent(this, CatalogActivity::class.java))
                    false
                }
                R.id.nav_makers -> {
                    startActivity(Intent(this, MeetMakerActivity::class.java))
                    false
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        findViewById<BottomNavigationView>(R.id.bottomNavigation).selectedItemId = R.id.nav_home
    }
}