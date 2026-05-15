package com.example.channapatnaappv2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.appbar.MaterialToolbar

class ToyDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toy_detail)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        val image = findViewById<ImageView>(R.id.detailImage)
        val name = findViewById<TextView>(R.id.detailName)
        val desc = findViewById<TextView>(R.id.detailDesc)
        val txtOrigin = findViewById<TextView>(R.id.txtOrigin)
        val txtMaterial = findViewById<TextView>(R.id.txtMaterial)
        val txtAge = findViewById<TextView>(R.id.txtAge)

        // Get data from intent
        val toyName = intent.getStringExtra("toy_name")

        name.text = toyName
        toolbar.title = toyName ?: getString(R.string.catalog)

        txtOrigin.text = "${getString(R.string.origin_label)} ${getString(R.string.channapatna_origin)}"
        txtMaterial.text = "${getString(R.string.material_label)} ${getString(R.string.wood_material)}"

        // Match toy using index to avoid translation issues
        val originalToys = resources.getStringArray(R.array.catalog_list)
        val index = originalToys.indexOf(toyName)

        when (index) {

            0 -> { // Wooden Horse
                image.setImageResource(R.drawable.ramesh)
                desc.text = getString(R.string.desc_horse)
                txtAge.text = "${getString(R.string.age_label)} ${getString(R.string.kids_age)}"
            }

            1 -> { // Spinning Top
                image.setImageResource(R.drawable.suresh)
                desc.text = getString(R.string.desc_top)
                txtAge.text = "${getString(R.string.age_label)} ${getString(R.string.kids_age)}"
            }

            2 -> { // Puzzle Toy
                image.setImageResource(R.drawable.lakshmi)
                desc.text = getString(R.string.desc_puzzle)
                txtAge.text = "${getString(R.string.age_label)} ${getString(R.string.kids_age)}"
            }

            3 -> { // Baby Rattle
                image.setImageResource(R.drawable.rattle)
                desc.text = getString(R.string.desc_rattle)
                txtAge.text = "${getString(R.string.age_label)} ${getString(R.string.safe_age)}"
            }

            4 -> { // Decorative Dolls
                image.setImageResource(R.drawable.doll)
                desc.text = getString(R.string.desc_doll)
                txtAge.text = "${getString(R.string.age_label)} ${getString(R.string.safe_age)}"
            }

            else -> {
                image.setImageResource(R.mipmap.ic_launcher)
                desc.text = getString(R.string.invalid_toy)
            }
        }
    }
}