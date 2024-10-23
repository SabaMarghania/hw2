package com.example.hw2
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _itemList = mutableListOf<String>()
    val itemList: List<String> get() = _itemList

    private fun addItem(item: String) {
        if (item.isNotEmpty()) {
            _itemList.add(0, item)
        }
    }

    private fun removeItem(position: Int) {
        if (position in _itemList.indices) {
            _itemList.removeAt(position)
        }
    }

    fun onEvent(event: MainEvent) {
        viewModelScope.launch {
            when (event) {
                is MainEvent.AddItem -> addItem(event.item)
                is MainEvent.RemoveItem -> removeItem(event.position)
            }
        }
    }
}