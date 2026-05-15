package com.example.channapatnaappv2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class VerifyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        // Toolbar setup
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        // UI elements
        val editText = findViewById<EditText>(R.id.editToyId)
        val btnCheck = findViewById<Button>(R.id.btnCheck)
        val btnScan = findViewById<Button>(R.id.btnScan)
        val btnNfc = findViewById<Button>(R.id.btnNfc)
        val resultLayout = findViewById<View>(R.id.layoutResult)
        val resultText = findViewById<TextView>(R.id.txtResult)
        val errorText = findViewById<TextView>(R.id.txtError)
        val imageView = findViewById<ImageView>(R.id.imgArtisan)
        val progressBar = findViewById<ProgressBar>(R.id.verifyProgress)
        val btnViewProfile = findViewById<Button>(R.id.btnViewProfile)

        // Reset UI
        resultLayout.visibility = View.GONE
        errorText.visibility = View.GONE
        progressBar.visibility = View.GONE

        // ✅ MINI DATABASE
        val toyDatabase = mapOf(
            "123456" to Triple(R.string.result_ramesh, R.drawable.ramesh, "Ramesh"),
            "654321" to Triple(R.string.result_suresh, R.drawable.suresh, "Suresh"),
            "111111" to Triple(R.string.result_lakshmi, R.drawable.lakshmi, "Lakshmi")
        )

        btnScan.setOnClickListener {
            Toast.makeText(this, getString(R.string.qr_hint), Toast.LENGTH_SHORT).show()
        }

        btnNfc.setOnClickListener {
            Toast.makeText(this, getString(R.string.nfc_hint), Toast.LENGTH_SHORT).show()
        }

        btnCheck.setOnClickListener {
            val id = editText.text.toString()

            // Reset UI for new check
            resultLayout.visibility = View.GONE
            errorText.visibility = View.GONE

            if (id.isEmpty()) {
                errorText.text = getString(R.string.invalid_toy)
                errorText.visibility = View.VISIBLE
                return@setOnClickListener
            }

            // Show loading
            progressBar.visibility = View.VISIBLE
            btnCheck.isEnabled = false

            Handler(Looper.getMainLooper()).postDelayed({
                progressBar.visibility = View.GONE
                btnCheck.isEnabled = true

                val toy = toyDatabase[id]

                if (toy != null) {
                    resultLayout.visibility = View.VISIBLE
                    resultText.text = getString(toy.first)
                    imageView.setImageResource(toy.second)
                    
                    btnViewProfile.setOnClickListener {
                        val intent = Intent(this, MakerProfileActivity::class.java)
                        intent.putExtra("maker_name", toy.third)
                        startActivity(intent)
                    }
                } else {
                    errorText.text = getString(R.string.invalid_toy)
                    errorText.visibility = View.VISIBLE
                }
            }, 1500)
        }
    }
}