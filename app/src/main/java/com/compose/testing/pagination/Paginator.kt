package com.compose.testing.pagination

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}