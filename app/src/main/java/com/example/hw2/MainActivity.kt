package com.example.hw2

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextItem: EditText
    private lateinit var buttonAdd: Button
    private lateinit var listViewItems: ListView
    private val itemList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextItem = findViewById(R.id.et_item)
        buttonAdd = findViewById(R.id.btn_add)
        listViewItems = findViewById(R.id.lv_items)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList)
        listViewItems.adapter = adapter

        buttonAdd.setOnClickListener {
            addItem()
        }

        listViewItems.setOnItemLongClickListener { parent, view, position, id ->
            removeItem(position)
            true
        }
    }

    private fun addItem() {
        val newItem = editTextItem.text.toString().trim()
        if (newItem.isNotEmpty()) {
            itemList.add(0, newItem)
            adapter.notifyDataSetChanged()
            editTextItem.text.clear()
        } else {
            Toast.makeText(this, "enter an item", Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeItem(position: Int) {
        itemList.removeAt(position)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Item removed", Toast.LENGTH_SHORT).show()
    }
}