package com.example.foodiesapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiesapp.ProductState
import com.example.foodiesapp.R
import com.example.foodiesapp.data.ProductModel
import com.example.foodiesapp.presentation.components.basketscreen.BasketBar
import com.example.foodiesapp.presentation.components.basketscreen.BasketItem
import com.example.foodiesapp.ui.theme.Orange
import com.example.foodiesapp.ui.theme.VeryLightGrey

@Composable
fun BasketScreen(
    state: ProductState,
    getBasketPrice: () -> Int,
    changePage: (String) -> Unit,
    addToBasket: (ProductModel) -> Unit,
    deleteToBasket: (ProductModel) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(color = VeryLightGrey)
    ) {
        BasketBar(changePage)
        if (state.basket.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Пусто, выберите блюда в каталоге :)",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 80.dp, end = 80.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxHeight(0.93f)
            ) {
                itemsIndexed(state.basket.keys.toList()) { _, item ->
                    BasketItem(
                        item,
                        state.basket[item]!!,
                        addToBasket,
                        deleteToBasket,
                    )
                }
            }
            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, bottom = 0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = "Заказать за ${getBasketPrice()} р")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBasketScreen() {
    BasketScreen(ProductState().copy(
        basket = mapOf(
            Pair(
                ProductModel(
                    id = "3",
                    categoryId = "2",
                    image = R.drawable.product,
                    title = "Том Ям",
                    content = "description",
                    weight = 200,
                    foodValue = 190.5,
                    proteins = 110.3,
                    fats = 200.1,
                    carbohydrates = 100.2,
                    price = 300,
                    oldPrice = when (400) {
                        null -> 0
                        else -> 400
                    },
                    tagIds = listOf("1")
                ), 1
            ),
            Pair(
                ProductModel(
                    id = "2",
                    categoryId = "2",
                    image = R.drawable.product,
                    title = "Том Ям",
                    content = "description",
                    weight = 200,
                    foodValue = 190.5,
                    proteins = 110.3,
                    fats = 200.1,
                    carbohydrates = 100.2,
                    price = 300,
                    oldPrice = when (400) {
                        null -> 0
                        else -> 400
                    },
                    tagIds = listOf("2")
                ), 2
            ),
            Pair(
                ProductModel(
                    id = "1",
                    categoryId = "2",
                    image = R.drawable.product,
                    title = "Том Ям",
                    content = "description",
                    weight = 200,
                    foodValue = 190.5,
                    proteins = 110.3,
                    fats = 200.1,
                    carbohydrates = 100.2,
                    price = 300,
                    oldPrice = when (400) {
                        null -> 0
                        else -> 400
                    },
                    tagIds = listOf("1")
                ), 3
            )
        )
    ), { 1000 }, {}, {}, {})
}