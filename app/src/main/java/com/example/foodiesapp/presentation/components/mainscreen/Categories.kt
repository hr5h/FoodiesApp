package com.example.foodiesapp.presentation.components.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodiesapp.ProductState
import com.example.foodiesapp.R
import com.example.foodiesapp.data.ProductModel
import com.example.foodiesapp.ui.theme.Orange

@Composable
fun Categories(
    state: ProductState,
    chooseCategory: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        itemsIndexed(state.categories) { _, item ->
            ItemCategory(item, chooseCategory, state.chooseCategory == item.first)
        }
    }
}

@Composable
fun ItemCategory(
    category: Pair<String, String>,
    chooseCategory: (String) -> Unit,
    isChoose: Boolean
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = if(isChoose) Orange else Color.White),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 10.dp,
                bottom = 10.dp
            )
            .clickable {
                chooseCategory(category.first)
            }
    ) {
        Text(
            text = category.second,
            color = if(isChoose) Color.White else Color.Black,
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 10.dp,
                    bottom = 10.dp
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategories() {
    Categories(
        ProductState().copy(
            products = listOf(
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
                ),
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
                ),
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
                    tagIds = listOf("3")
                ),
            )
        ), { "1" })
}