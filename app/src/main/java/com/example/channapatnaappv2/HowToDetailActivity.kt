package com.example.channapatnaappv2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.appbar.MaterialToolbar

class HowToDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to_detail)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        val image = findViewById<ImageView>(R.id.howToImage)
        val title = findViewById<TextView>(R.id.howToTitle)
        val desc = findViewById<TextView>(R.id.howToDesc)

        val toyType = intent.getStringExtra("toy_type")

        when (toyType) {
            "doll" -> {
                toolbar.title = getString(R.string.doll_title)
                title.text = getString(R.string.doll_title)
                desc.text = getString(R.string.doll_how_to)
                image.setImageResource(R.drawable.doll)
            }
            "rattle" -> {
                toolbar.title = getString(R.string.rattle_title)
                title.text = getString(R.string.rattle_title)
                desc.text = getString(R.string.rattle_how_to)
                image.setImageResource(R.drawable.rattle)
            }
            "horse" -> {
                toolbar.title = getString(R.string.horse_title)
                title.text = getString(R.string.horse_title)
                desc.text = getString(R.string.horse_how_to)
                image.setImageResource(R.drawable.ramesh)
            }
        }
    }
}