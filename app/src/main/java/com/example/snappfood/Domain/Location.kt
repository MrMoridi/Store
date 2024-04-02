package com.example.snappfood.Domain

data class Location(val id: Int, var loc: String) {

    // Override toString() for a more informative representation (optional)
    override fun toString(): String = "Location(id=$id, loc='$loc')"
}