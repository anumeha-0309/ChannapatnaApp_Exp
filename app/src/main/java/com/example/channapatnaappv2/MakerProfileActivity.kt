package com.example.channapatnaappv2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.appbar.MaterialToolbar

class MakerProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maker_profile)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        val profilePic = findViewById<ImageView>(R.id.profilePic)
        val profileName = findViewById<TextView>(R.id.profileName)
        val profileRole = findViewById<TextView>(R.id.profileRole)
        val profileStory = findViewById<TextView>(R.id.profileStory)
        val workImg1 = findViewById<ImageView>(R.id.workImg1)
        val workImg2 = findViewById<ImageView>(R.id.workImg2)
        val workImg3 = findViewById<ImageView>(R.id.workImg3)

        val name = intent.getStringExtra("maker_name") ?: "Ramesh"
        profileName.text = name

        // Match artisan using index to avoid translation issues
        val originalNames = resources.getStringArray(R.array.maker_names)
        val index = originalNames.indexOf(name)

        when (index) {
            0 -> { // Ramesh
                profilePic.setImageResource(R.drawable.ramesh)
                profileRole.text = getString(R.string.role_expert)
                profileStory.text = getString(R.string.ramesh_story)
                workImg1.setImageResource(R.drawable.doll)
                workImg2.setImageResource(R.drawable.ramesh)
                workImg3.setImageResource(R.drawable.rattle)
            }
            1 -> { // Suresh
                profilePic.setImageResource(R.drawable.suresh)
                profileRole.text = getString(R.string.role_puzzle)
                profileStory.text = getString(R.string.suresh_story)
                workImg1.setImageResource(R.drawable.rattle)
                workImg2.setImageResource(R.drawable.suresh)
                workImg3.setImageResource(R.drawable.doll)
            }
            2 -> { // Lakshmi
                profilePic.setImageResource(R.drawable.lakshmi)
                profileRole.text = getString(R.string.role_artist)
                profileStory.text = getString(R.string.lakshmi_story)
                workImg1.setImageResource(R.drawable.doll)
                workImg2.setImageResource(R.drawable.lakshmi)
                workImg3.setImageResource(R.drawable.ramesh)
            }
            3 -> { // Anil
                profilePic.setImageResource(R.drawable.anil)
                profileRole.text = getString(R.string.role_craftsman)
                profileStory.text = getString(R.string.anil_story)
                workImg1.setImageResource(R.drawable.rattle)
                workImg2.setImageResource(R.drawable.anil)
                workImg3.setImageResource(R.drawable.doll)
            }
            4 -> { // Meena
                profilePic.setImageResource(R.drawable.meena)
                profileRole.text = getString(R.string.role_designer)
                profileStory.text = getString(R.string.meena_story)
                workImg1.setImageResource(R.drawable.doll)
                workImg2.setImageResource(R.drawable.meena)
                workImg3.setImageResource(R.drawable.ramesh)
            }
            else -> {
                profilePic.setImageResource(R.drawable.ramesh)
                profileRole.text = getString(R.string.role_expert)
                profileStory.text = getString(R.string.ramesh_story)
                workImg1.setImageResource(R.drawable.doll)
                workImg2.setImageResource(R.drawable.ramesh)
                workImg3.setImageResource(R.drawable.rattle)
            }
        }
    }
}