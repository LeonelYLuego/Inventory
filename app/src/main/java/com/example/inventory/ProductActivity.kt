package com.example.inventory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.inventory.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras
        val image = bundle!!.getInt("Image")
        val name = bundle!!.getString("Name")
        val description = bundle!!.getString("Description")
        val price = bundle!!.getString("Price")

        binding.imageView.setImageResource(image)
        binding.textName.text = name
        binding.textDescription.text = description
        binding.textPrice.text = price
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}