package com.example.channapatnaappv2

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText

import com.google.android.material.bottomnavigation.BottomNavigationView

class CatalogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        val recyclerView = findViewById<RecyclerView>(R.id.catalogRecyclerView)
        val searchBox = findViewById<TextInputEditText>(R.id.searchBox)

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_catalog
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    finish()
                    true
                }
                R.id.nav_catalog -> true
                R.id.nav_makers -> {
                    startActivity(Intent(this, MeetMakerActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }

        val toys = resources.getStringArray(R.array.catalog_list).toList()
        val adapter = CatalogAdapter(toys) { toyName ->
            val intent = Intent(this, ToyDetailActivity::class.java)
            intent.putExtra("toy_name", toyName)
            startActivity(intent)
        }

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter

        searchBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
        })
    }

    class CatalogAdapter(
        private val fullList: List<String>,
        private val onClick: (String) -> Unit
    ) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

        private var filteredList = fullList

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val image: ImageView = view.findViewById(R.id.toyImage)
            val name: TextView = view.findViewById(R.id.toyName)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.toy_card_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val toyName = filteredList[position]
            holder.name.text = toyName
            
            // Map image using string comparison on the English values
            // or by checking the index in the original array
            val originalToys = holder.itemView.context.resources.getStringArray(R.array.catalog_list)
            val index = originalToys.indexOf(toyName)
            
            val imgRes = when (index) {
                0 -> R.drawable.ramesh // Wooden Horse
                1 -> R.drawable.suresh // Spinning Top
                2 -> R.drawable.lakshmi // Puzzle Toy
                3 -> R.drawable.rattle // Baby Rattle
                4 -> R.drawable.doll // Decorative Dolls
                else -> R.drawable.ic_launcher_foreground
            }
            holder.image.setImageResource(imgRes)
            holder.itemView.setOnClickListener { onClick(toyName) }
        }

        override fun getItemCount(): Int = filteredList.size

        fun filter(query: String) {
            filteredList = if (query.isEmpty()) {
                fullList
            } else {
                fullList.filter { it.contains(query, ignoreCase = true) }
            }
            notifyDataSetChanged()
        }
    }
}