package com.sample.addressbook

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sample.addressbook.R

class DetailActivity : AppCompatActivity() {

    private lateinit var firstNameView: TextView
    private lateinit var lastNameView: TextView
    private lateinit var addressView: TextView
    private lateinit var phoneView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        firstNameView = findViewById(R.id.fnameTV)
        lastNameView = findViewById(R.id.lnameTV)
        addressView = findViewById(R.id.addressTV)
        phoneView = findViewById(R.id.phoneTV)

        val person: Person? = intent.getParcelableExtra("person")

        person?.let {
            firstNameView.text = it.firstName
            lastNameView.text = it.lastName
            addressView.text = it.address
            phoneView.text = it.phone
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}