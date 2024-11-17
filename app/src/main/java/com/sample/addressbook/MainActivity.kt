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

    private lateinit var firstNameInput: EditText
    private lateinit var lastNameInput: EditText
    private lateinit var addressInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var saveButton: Button
    private lateinit var listView: ListView

    private val personList = mutableListOf<Person>()
    private lateinit var adapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        firstNameInput = findViewById(R.id.firstNameET)
        lastNameInput = findViewById(R.id.lastNameET)
        addressInput = findViewById(R.id.addressET)
        phoneInput = findViewById(R.id.phoneET)
        saveButton = findViewById(R.id.saveB)
        listView = findViewById(R.id.lvAB)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        saveButton.setOnClickListener {
            val firstName = firstNameInput.text.toString()
            val lastName = lastNameInput.text.toString()
            val address = addressInput.text.toString()
            val phone = phoneInput.text.toString()

            val person = Person(firstName, lastName, address, phone)
            personList.add(person)
            adapter.add("$firstName $lastName")
            clearInputs()
        }

        listView.setOnItemClickListener { _, _, position, _ ->
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
        firstNameInput.text.clear()
        lastNameInput.text.clear()
        addressInput.text.clear()
        phoneInput.text.clear()
    }

}