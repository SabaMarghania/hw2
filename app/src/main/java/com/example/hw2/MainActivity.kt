package com.example.hw2

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    private lateinit var editTextItem: EditText
    private lateinit var buttonAdd: Button
    private lateinit var listViewItems: ListView
    private lateinit var adapter: ArrayAdapter<String>

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextItem = findViewById(R.id.et_item)
        buttonAdd = findViewById(R.id.btn_add)
        listViewItems = findViewById(R.id.lv_items)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, viewModel.itemList)
        listViewItems.adapter = adapter

        buttonAdd.setOnClickListener {
            val newItem = editTextItem.text.toString().trim()
            if (newItem.isNotEmpty()) {
                viewModel.onEvent(MainEvent.AddItem(newItem))
                adapter.notifyDataSetChanged()
                editTextItem.text.clear()
            } else {
                Toast.makeText(this, "Enter an item", Toast.LENGTH_SHORT).show()
            }
        }

        listViewItems.setOnItemLongClickListener { _, _, position, _ ->
            viewModel.onEvent(MainEvent.RemoveItem(position))
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Item removed", Toast.LENGTH_SHORT).show()
            true
        }
    }
}