package com.example.snappfood.Domain

data class Price(val id: Int, val value: String) {

     // بازنویسی toString() برای نمایش جزئیات بیشتر
     override fun toString(): String {
          return "Price(id=$id, value='$value')"
     }
}