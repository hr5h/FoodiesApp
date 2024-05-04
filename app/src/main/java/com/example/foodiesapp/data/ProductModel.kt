package com.example.foodiesapp.data

import com.example.foodiesapp.R

data class ProductModel(
    val id: String,
    val categoryId: String,
    val image: Int = R.drawable.product,
    val title: String,
    val content: String,
    val weight: Int,
    val foodValue: Double,
    val proteins: Double,
    val fats: Double,
    val carbohydrates: Double,
    val price: Int,
    val oldPrice: Int? = 0,
    val tagIds: List<String>
)