package com.sample.addressbook

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var fnameInput: EditText
    private lateinit var lnameInput: EditText
    private lateinit var addressInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var saveButton: Button
    private lateinit var listViewAB: ListView

    private val personList = mutableListOf<Person>()
    private lateinit var adapterAB: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        fnameInput = findViewById(R.id.firstNameET)
        lnameInput = findViewById(R.id.lastNameET)
        addressInput = findViewById(R.id.addressET)
        phoneInput = findViewById(R.id.phoneET)
        saveButton = findViewById(R.id.saveB)
        listViewAB = findViewById(R.id.lvAB)

        adapterAB = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listViewAB.adapter = adapterAB

        saveButton.setOnClickListener {
            val firstName = fnameInput.text.toString()
            val lastName = lnameInput.text.toString()
            val address = addressInput.text.toString()
            val phone = phoneInput.text.toString()

            val person = Person(firstName, lastName, address, phone)
            personList.add(person)
            adapterAB.add("$firstName $lastName")
            clearInputs()
        }

        listViewAB.setOnItemClickListener { _, _, position, _ ->
            val person = personList[position]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("person", person)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun clearInputs() {
        fnameInput.text.clear()
        lnameInput.text.clear()
        addressInput.text.clear()
        phoneInput.text.clear()
    }

}