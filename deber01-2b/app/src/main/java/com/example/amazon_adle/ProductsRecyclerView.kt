package com.example.amazon_adle

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ProductsRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_recycler_view)

        // Getting products data
        val productsList = ProductsDataArrayList.productsList

        // Getting views
        val recyclerView = findViewById<RecyclerView>(R.id.rv_products)
        val searchField = findViewById<SearchView>(R.id.sv_search)
        val backImageButton = findViewById<ImageButton>(R.id.img_btn_back)
        val searchImageButton = findViewById<ImageButton>(R.id.img_btn_search)

        // Getting queries
        val query = intent.getStringExtra("query")
        searchField.setQuery(query, false)

        // Hiding or showing views when focusing search view
        searchField.setOnQueryTextFocusChangeListener { _, _ ->
            if (recyclerView.visibility == View.VISIBLE) {
                recyclerView.visibility = View.INVISIBLE
                setOnSearchViewFocus(searchImageButton)
            }
            else {
                recyclerView.visibility = View.VISIBLE
                setOffSearchViewFocus(searchImageButton)
            }
        }

        // Setting image button back action
        backImageButton.setOnClickListener {
            if (recyclerView.visibility == View.VISIBLE) {
                openActivity(MainActivity::class.java)
            }
            else {
                searchField.clearFocus()
                recyclerView.visibility = View.VISIBLE
                setOnSearchViewFocus(searchImageButton)
            }
        }

        // Handling search view query actions
        searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // Sending a string to search a product
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(
                    this@ProductsRecyclerView,
                    ProductsRecyclerView::class.java
                )
                intent.putExtra("query", query)
                startActivity(intent)
                return false
            }

            // Handling a change in the query
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        // Filtering products list according to the query
        if(query != null && query != "") {
            initializeRecyclerView(
                ArrayList(productsList.filter {it.category == query}),
                recyclerView
            )
        }
        else {
            productsList.shuffle()

            initializeRecyclerView(
                productsList,
                recyclerView
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initializeRecyclerView(list: ArrayList<Product>, recyclerView: RecyclerView) {
        val adapter = ProductsRecyclerViewAdapter(list)

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }

    private fun setOnSearchViewFocus(searchImageButton: ImageButton) {
        val sImgBtnLayoutParams = searchImageButton.layoutParams as ConstraintLayout.LayoutParams

        sImgBtnLayoutParams.width = 0
        sImgBtnLayoutParams.height = 0

        searchImageButton.layoutParams = sImgBtnLayoutParams
    }

    private fun setOffSearchViewFocus(searchImageButton: ImageButton) {
        val sImgBtnLayoutParams = searchImageButton.layoutParams as ConstraintLayout.LayoutParams

        sImgBtnLayoutParams.width = 40
        sImgBtnLayoutParams.height = 40

        searchImageButton.layoutParams = sImgBtnLayoutParams
    }

    private fun openActivity (
        activityClass: Class<*>
    ) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}