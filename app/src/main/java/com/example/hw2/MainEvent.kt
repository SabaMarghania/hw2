package com.example.hw2

sealed class MainEvent {
    data class AddItem(val item: String) : MainEvent()
    data class RemoveItem(val position: Int) : MainEvent()
}
