package com.example.inventory

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.inventory.databinding.ProductMoldBinding

class ProductAdapter(private val context: Context, var productList: ArrayList<Product>) : BaseAdapter() {
    override fun getCount(): Int {
        return productList.size;
    }

    override fun getItem(id: Int): Any {
        return productList[id];
    }

    override fun getItemId(id: Int): Long {
        return id.toLong();
    }

    override fun getView(id: Int, convertView: View?, parent: ViewGroup?): View {
        var product = productList[id]
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val myView = ProductMoldBinding.inflate(inflater, parent, false)
        val view = myView.root
        myView.imageView.setImageResource(product.image!!)
        myView.name.text = product.name!!
        myView.description.text = product.description!!
        myView.price.text = product.price!!

        myView.root.setOnClickListener {
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra("Image", product.image!!)
            intent.putExtra("Name", product.name!!)
            intent.putExtra("Description", product.description!!)
            intent.putExtra("Price", product.price!!)
            context!!.startActivity(intent)
        }

        return view
    }
}