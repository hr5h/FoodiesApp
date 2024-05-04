package com.example.foodiesapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.foodiesapp.data.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.json.JSONArray
import org.json.JSONObject


class MainViewModel(val context: Context) : ViewModel() {
    val state = MutableStateFlow(ProductState())

    fun changePage(newPage: String) {
        Log.d("MyLog", "changePage: $newPage")
        state.update { it.copy(page = newPage) }
        unChooseProduct()
    }

    fun chooseProduct(product: ProductModel) {
        Log.d("MyLog", "chooseProduct: $product")
        state.update { it.copy(entityProduct = product) }
    }

    private fun unChooseProduct() {
        Log.d("MyLog", "unChooseProduct")
        state.update { it.copy(entityProduct = null) }
    }

    fun chooseTag(tag: String) {
        Log.d("MyLog", "chooseTag: $tag")
        state.update { it.copy(chooseTags = it.chooseTags + tag) }
    }

    fun unChooseTag(tag: String) {
        Log.d("MyLog", "unChooseTag: $tag")
        state.update { it.copy(chooseTags = it.chooseTags - tag) }
    }

    fun chooseCategory(category: String) {
        Log.d("MyLog", "chooseCategory: $category")
        if(category == state.value.chooseCategory)
            state.update { it.copy(chooseCategory = "") }
        else state.update { it.copy(chooseCategory = category) }
    }

    fun addProductToBasket(product: ProductModel) {
        Log.d("MyLog", "addProductToBasket: $product")
        if (state.value.basket.containsKey(product))
            state.update {
                it.copy(
                    basket = it.basket + Pair(product, it.basket[product]?.plus(1) ?: 1)
                )
            }
        else {
            state.update {
                it.copy(
                    basket = it.basket + Pair(product, 1)
                )
            }
        }
    }

    fun deleteProductToBasket(product: ProductModel) {
        Log.d("MyLog", "deleteProductToBasket: $product")
        if (state.value.basket.containsKey(product)) {
            if (state.value.basket[product] == 1) {
                state.update {
                    it.copy(
                        basket = it.basket - product
                    )
                }
            } else {
                state.update {
                    it.copy(
                        basket = it.basket + Pair(product, it.basket[product]?.minus(1) ?: 1)
                    )
                }
            }
        }
    }

    fun getBasketPrice(): Int {
        var sum = 0
        val basket = state.value.basket
        for (it in basket) {
            sum += it.key.price * it.value
        }
        return sum
    }

    fun getFilteredProduct(text: String = ""): List<ProductModel> {
        var list = state.value.products
        //tags
        if (state.value.chooseTags.isNotEmpty()){
            list = state.value.products.filter {
                it.tagIds == state.value.chooseTags
            }
        }
        //categories
        if (state.value.chooseCategory.isNotEmpty()){
            list = state.value.products.filter {
                it.categoryId == state.value.chooseCategory
            }
        }
        //search
        if(text.isNotEmpty()) {
            list = list.filter {
                val regex = Regex(text.lowercase())
                regex.containsMatchIn(it.title.lowercase())
            }
        }
        return list
    }

    fun getCategories() {
        val url = "https://anika1d.github.io/WorkTestServer/Categories.json"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                Log.d("MyLogResponse", "Response: $response")
                val list = mutableListOf<Pair<String, String>>()
                val mainObj = JSONArray(response)
                for (i in 0 until mainObj.length()) {
                    val item = mainObj[i] as JSONObject
                    list.add(Pair(item.getString("id"), item.getString("name")))
                }
                for (it in list) {
                    Log.d("MyLogCategory", "id = ${it.first}, name = ${it.second}")
                }
                state.update {
                    it.copy(
                        categories = list
                    )
                }
            },
            { error ->
                Log.d("MyLogError", "Error: $error")
            }
        )
        queue.add(request)
    }

    fun getTags() {
        val url = "https://anika1d.github.io/WorkTestServer/Tags.json"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                Log.d("MyLogResponse", "Response: $response")
                val list = mutableListOf<Pair<String, String>>()
                val mainObj = JSONArray(response)
                for (i in 0 until mainObj.length()) {
                    val item = mainObj[i] as JSONObject
                    list.add(Pair(item.getString("id"), item.getString("name")))
                }
                for (it in list) {
                    Log.d("MyLogTag", "id = ${it.first}, name = ${it.second}")
                }
                state.update {
                    it.copy(
                        tags = list
                    )
                }
            },
            { error ->
                Log.d("MyLogError", "Error: $error")
            }
        )
        queue.add(request)
    }

    fun getProducts() {
        val url = "https://anika1d.github.io/WorkTestServer/Products.json"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                Log.d("MyLogResponse", "Response: $response")
                val list = mutableListOf<ProductModel>()
                val mainObj = JSONArray(response)
                for (i in 0 until mainObj.length()) {
                    val item = mainObj[i] as JSONObject
                    val oldPrice = item.getString("price_old").toIntOrNull()
                    val productModel = ProductModel(
                        id = item.getString("id"),
                        categoryId = item.getString("category_id"),
                        image = R.drawable.product,
                        title = item.getString("name"),
                        content = item.getString("description"),
                        weight = item.getString("measure").toInt(),
                        foodValue = item.getString("energy_per_100_grams").toDouble(),
                        proteins = item.getString("proteins_per_100_grams").toDouble(),
                        fats = item.getString("fats_per_100_grams").toDouble(),
                        carbohydrates = item.getString("carbohydrates_per_100_grams").toDouble(),
                        price = item.getString("price_current").toInt(),
                        oldPrice = when (oldPrice) {
                            null -> 0
                            else -> oldPrice
                        },
                        tagIds = item.getString("tag_ids").map { it.toString() }.filter { it != "[" && it != "]" && it != " " && it != "," && it != "\n" }
                    )
                    list.add(productModel)
                }
                for (it in list) {
                    Log.d("MyLogProduct", "product = $it")
                }
                state.update {
                    it.copy(
                        products = list
                    )
                }
            },
            { error ->
                Log.d("MyLogError", "Error: $error")
            }
        )
        queue.add(request)
    }
}