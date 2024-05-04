package com.example.foodiesapp

import com.example.foodiesapp.data.ProductModel

data class ProductState(
    val page: String = "main",
    val entityProduct: ProductModel? = null,
    val basket: Map<ProductModel, Int> = emptyMap(),
    val categories: List<Pair<String, String>> = emptyList(),
    val chooseCategory: String = "",
    val tags: List<Pair<String, String>> = emptyList(),
    val chooseTags: List<String> = emptyList(),
    val products: List<ProductModel> = emptyList(),
)
