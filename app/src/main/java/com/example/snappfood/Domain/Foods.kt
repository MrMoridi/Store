package com.example.snappfood.Domain

data class Foods(
    val categoryId: Int,
    val description: String,
    val bestFood: Boolean,
    val id: Int,
    val locationId: Int,
    val Price: Double,
    val imagePath: String,
    val priceId: Int,
    val star: Double,
    val timeId: Int,
    val timeValue: Int,
    val Title: String,
    var numberInCart: Int
) {

    // Override toString() for a more informative representation (optional)
    override fun toString(): String {
        return "Foods(categoryId=$categoryId, description='$description', bestFood=$bestFood, id=$id, locationId=$locationId, price=$Price, imagePath='$imagePath', priceId=$priceId, star=$star, timeId=$timeId, timeValue=$timeValue, title='$Title', numberInChart=$numberInCart)"
    }
}