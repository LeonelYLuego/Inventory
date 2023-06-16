package com.example.inventory

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import com.example.inventory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var adapter: ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productList: ArrayList<Product> = ArrayList()
        productList.add(Product(R.drawable.apple, "Manzana", "Fruta", "$ 123"))

        adapter = ProductAdapter(this, productList)
        binding.listView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val handler = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        search.setSearchableInfo(handler.getSearchableInfo(componentName))
        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            R.id.add_menu -> {
                // var intent = Intent(this, AddActivity::class.java)
                // startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}