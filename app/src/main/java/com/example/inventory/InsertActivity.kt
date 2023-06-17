package com.example.inventory

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast


class InsertActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etDescription: EditText
    private lateinit var etPrice: EditText
    private lateinit var dbManager: DBManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        etName = findViewById(R.id.etName)
        etDescription = findViewById(R.id.etDescription)
        etPrice = findViewById(R.id.etPrice)

        dbManager = DBManager(this)
    }

    fun saveProduct(view: View) {
        val name = etName.text.toString().trim()
        val description = etDescription.text.toString().trim()
        val price = etPrice.text.toString().trim()

        if (name.isNotEmpty() && description.isNotEmpty() && price.isNotEmpty()) {
            dbManager.insertProduct(0, name, description, price)
            Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show()

            val resultIntent = Intent()
            resultIntent.putExtra("productAdded", true)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
        }
    }
}