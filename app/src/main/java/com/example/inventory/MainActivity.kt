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
    private lateinit var dbManager: DBManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbManager = DBManager(this)

        val productList: ArrayList<Product> = getAllProducts()
        //productList.add(Product(R.drawable.apple, "Manzana", "Fruta", "$ 123"))

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
                searchProductsByName(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        search.setOnCloseListener {
            // Show the original list when search view is closed
            adapter?.updateProductList(getAllProducts())
            false
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun searchProductsByName(query: String?) {
        val productList: ArrayList<Product> = ArrayList()

        if (!query.isNullOrEmpty()) {
            val projection = arrayOf(dbManager.id, dbManager.image, dbManager.name, dbManager.description, dbManager.price)
            val selection = "${dbManager.name} LIKE ?"
            val selectionArgs = arrayOf("%$query%")
            val orderBy = "Name"

            val cursor = dbManager.getProducts(projection, selection, selectionArgs, orderBy)

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val image = cursor.getInt(cursor.getColumnIndexOrThrow(dbManager.image))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(dbManager.name))
                    val description = cursor.getString(cursor.getColumnIndexOrThrow(dbManager.description))
                    val price = cursor.getString(cursor.getColumnIndexOrThrow(dbManager.price))

                    productList.add(Product(image, name, description, price))
                }
                cursor.close()
                if (productList.isEmpty()) {
                    Toast.makeText(this, "No se encontraron productos.", Toast.LENGTH_LONG).show()
                }
            }
        }

        adapter?.updateProductList(productList)
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

    private fun getAllProducts(): ArrayList<Product> {
        val projection = arrayOf(dbManager.id, dbManager.image, dbManager.name, dbManager.description, dbManager.price)
        val selection = ""
        val selectionArgs = arrayOf<String>()
        val orderBy = "Name" // Set your desired order by clause here if needed

        val cursor = dbManager.getProducts(projection, selection, selectionArgs, orderBy)
        val productList: ArrayList<Product> = ArrayList()

        if (cursor != null) {
            while (cursor.moveToNext()) {
                val image = cursor.getInt(cursor.getColumnIndexOrThrow(dbManager.image))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(dbManager.name))
                val description = cursor.getString(cursor.getColumnIndexOrThrow(dbManager.description))
                val price = cursor.getString(cursor.getColumnIndexOrThrow(dbManager.price))

                productList.add(Product(image, name, description, price))
            }
            cursor.close()
        }else{
            Toast.makeText(this, "No se encontraron productos.", Toast.LENGTH_LONG).show()
        }

        return productList
    }


}