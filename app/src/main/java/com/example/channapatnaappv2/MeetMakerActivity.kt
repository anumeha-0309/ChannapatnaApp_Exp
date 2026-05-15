package com.example.channapatnaappv2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.appbar.MaterialToolbar

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText

import com.google.android.material.bottomnavigation.BottomNavigationView

class MeetMakerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meet_maker)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        val recyclerView = findViewById<RecyclerView>(R.id.makerRecyclerView)
        val searchArtisan = findViewById<TextInputEditText>(R.id.searchArtisan)
        val btnRegister = findViewById<android.widget.Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            startActivity(Intent(this, ArtistRegistrationActivity::class.java))
        }

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_makers
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    finish()
                    true
                }
                R.id.nav_catalog -> {
                    startActivity(Intent(this, CatalogActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_makers -> true
                else -> false
            }
        }

        val names = resources.getStringArray(R.array.maker_names)
        val roles = resources.getStringArray(R.array.maker_roles)
        val images = arrayOf(
            R.drawable.ramesh,
            R.drawable.suresh,
            R.drawable.lakshmi,
            R.drawable.anil,
            R.drawable.meena
        )

        val adapter = MakerAdapter(names, roles, images) { name ->
            val intent = Intent(this, MakerProfileActivity::class.java)
            intent.putExtra("maker_name", name)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        searchArtisan.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
        })
    }

    class MakerAdapter(
        private val names: Array<String>,
        private val roles: Array<String>,
        private val images: Array<Int>,
        private val onClick: (String) -> Unit
    ) : RecyclerView.Adapter<MakerAdapter.ViewHolder>() {

        private var filteredNames = names.toList()
        private var filteredRoles = roles.toList()
        private var filteredImages = images.toList()

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val image = view.findViewById<ImageView>(R.id.cardImage)
            val name = view.findViewById<TextView>(R.id.cardName)
            val role = view.findViewById<TextView>(R.id.cardRole)
            val btn = view.findViewById<View>(R.id.btnKnowStory)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.maker_card_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.image.setImageResource(filteredImages[position])
            holder.name.text = filteredNames[position]
            holder.role.text = filteredRoles[position]
            holder.btn.setOnClickListener { onClick(filteredNames[position]) }
        }

        override fun getItemCount(): Int = filteredNames.size

        fun filter(query: String) {
            val indices = names.indices.filter { 
                names[it].contains(query, ignoreCase = true) || roles[it].contains(query, ignoreCase = true)
            }
            filteredNames = indices.map { names[it] }
            filteredRoles = indices.map { roles[it] }
            filteredImages = indices.map { images[it] }
            notifyDataSetChanged()
        }
    }
}