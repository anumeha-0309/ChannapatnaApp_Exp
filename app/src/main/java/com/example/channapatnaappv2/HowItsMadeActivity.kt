package com.example.channapatnaappv2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.widget.Button

import com.google.android.material.appbar.MaterialToolbar

class HowItsMadeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_its_made)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        findViewById<Button>(R.id.btnDollDetail).setOnClickListener {
            openDetail("doll")
        }

        findViewById<Button>(R.id.btnRattleDetail).setOnClickListener {
            openDetail("rattle")
        }

        findViewById<Button>(R.id.btnHorseDetail).setOnClickListener {
            openDetail("horse")
        }
    }

    private fun openDetail(type: String) {
        val intent = Intent(this, HowToDetailActivity::class.java)
        intent.putExtra("toy_type", type)
        startActivity(intent)
    }
}