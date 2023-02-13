package com.example.amazon_adle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Products button
        val productsButton = findViewById<Button>(R.id.btn_products)
        productsButton.setOnClickListener {
            openActivity(ProductsRecyclerView::class.java)
        }
    }

    private fun openActivity (
        activityClass: Class<*>
    ) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}