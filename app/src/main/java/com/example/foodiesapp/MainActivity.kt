package com.example.foodiesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.foodiesapp.presentation.screens.BasketScreen
import com.example.foodiesapp.presentation.screens.MainScreen
import com.example.foodiesapp.presentation.screens.ProductScreen
import com.example.foodiesapp.presentation.screens.SearchScreen

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by lazy {
        MainViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCategories()
        viewModel.getTags()
        viewModel.getProducts()
        setContent {
            val state by viewModel.state.collectAsState()
            //Log.d("MyLog", viewModel.toString())
            //Log.d("MyLog", state.toString())
            if (state.entityProduct != null) {
                ProductScreen(
                    state = state,
                    count = state.basket[state.entityProduct] ?: 0,
                    addToBasket = viewModel::addProductToBasket,
                    deleteToBasket = viewModel::deleteProductToBasket,
                    changePage = viewModel::changePage
                )
            } else if (state.page == "basket") {
                BasketScreen(
                    state = state,
                    getBasketPrice = viewModel::getBasketPrice,
                    changePage = viewModel::changePage,
                    addToBasket = viewModel::addProductToBasket,
                    deleteToBasket = viewModel::deleteProductToBasket
                )
            } else if (state.page == "search") {
                SearchScreen(
                    state = state,
                    changePage = viewModel::changePage,
                    chooseProduct = viewModel::chooseProduct,
                    addToBasket = viewModel::addProductToBasket,
                    deleteToBasket = viewModel::deleteProductToBasket,
                    getProducts = viewModel::getFilteredProduct
                )
            } else {
                MainScreen(
                    state = state,
                    chooseProduct = viewModel::chooseProduct,
                    addToBasket = viewModel::addProductToBasket,
                    deleteToBasket = viewModel::deleteProductToBasket,
                    getBasketPrice = viewModel::getBasketPrice,
                    changePage = viewModel::changePage,
                    chooseTag = viewModel::chooseTag,
                    unChooseTag = viewModel::unChooseTag,
                    getProducts = viewModel::getFilteredProduct,
                    chooseCategory = viewModel::chooseCategory
                )
            }
        }
    }
}

