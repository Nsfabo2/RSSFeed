package com.example.rssfeed

data class Questions(val id: String, val title: String, val published: String) {
    override fun toString(): String = title!!
}
