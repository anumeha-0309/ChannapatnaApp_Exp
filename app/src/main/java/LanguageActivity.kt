package com.example.channapatnaappv2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

import android.view.View
import com.google.android.material.appbar.MaterialToolbar

class LanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val isChangeMode = intent.getBooleanExtra("change_mode", false)

        if (isChangeMode) {
            toolbar.visibility = View.VISIBLE
            toolbar.setNavigationOnClickListener { finish() }
        }

        // English
        findViewById<View>(R.id.btnEnglish).setOnClickListener {
            setLocale("en")
        }

        // Hindi
        findViewById<View>(R.id.btnHindi).setOnClickListener {
            setLocale("hi")
        }

        // Kannada
        findViewById<View>(R.id.btnKannada).setOnClickListener {
            setLocale("kn")
        }
    }

    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)

        val config = resources.configuration
        config.setLocale(locale)

        resources.updateConfiguration(config, resources.displayMetrics)

        val isChangeMode = intent.getBooleanExtra("change_mode", false)
        if (isChangeMode) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}