package com.example.foodiesapp.presentation.components.mainscreen.catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
import com.example.foodiesapp.presentation.components.mainscreen.Categories

@Composable
fun Catalog(
    state: ProductState,
    chooseProduct: (ProductModel) -> Unit,
    addToBasket: (ProductModel) -> Unit,
    deleteToBasket: (ProductModel) -> Unit,
    getProducts: () -> List<ProductModel>,
    chooseCategory: (String) -> Unit
) {
    val products = getProducts()
    if (products.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Categories(state, chooseCategory)
            Text(
                text = "Таких блюд нет :(" +
                        "\nПопробуйте изменить фильтры",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 60.dp, end = 60.dp),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Gray
            )
        }
    } else {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            columns = GridCells.Fixed(2)
        ) {
            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                Categories(state, chooseCategory)
            }
            itemsIndexed(products) { _, item ->
                CatalogItem(
                    item,
                    state.basket[item] ?: 0,
                    chooseProduct,
                    addToBasket,
                    deleteToBasket
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCatalog() {
    Catalog(ProductState().copy(
        products = listOf(
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
            ), ProductModel(
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
                tagIds = listOf("2")
            ), ProductModel(
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
            )
        )
    ), {}, {}, {}, {emptyList()}, {}
    )
}
