package com.example.snappfood.Domain


data class Time(val id: Int, var value: String) {

    // Override toString() for a more descriptive representation (optional)
    override fun toString(): String {
        return "Time(id=$id, value='$value')"
    }
}