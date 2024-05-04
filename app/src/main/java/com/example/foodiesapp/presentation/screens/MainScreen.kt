package com.example.foodiesapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiesapp.ProductState
import com.example.foodiesapp.R
import com.example.foodiesapp.data.ProductModel
import com.example.foodiesapp.presentation.components.mainscreen.catalog.Catalog
import com.example.foodiesapp.presentation.components.mainscreen.bottomsheet.TagsBottomSheet
import com.example.foodiesapp.presentation.components.mainscreen.TopBar
import com.example.foodiesapp.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: ProductState,
    chooseProduct: (ProductModel) -> Unit,
    addToBasket: (ProductModel) -> Unit,
    deleteToBasket: (ProductModel) -> Unit,
    getBasketPrice: () -> Int,
    changePage: (String) -> Unit,
    chooseTag: (String) -> Unit,
    unChooseTag: (String) -> Unit,
    getProducts: () -> List<ProductModel>,
    chooseCategory: (String) -> Unit
) {
    val showBottomSheet = remember {
        mutableStateOf(false)
    }
    Column {
        Spacer(modifier = Modifier.height(50.dp))
        Catalog(
            state = state,
            chooseProduct = chooseProduct,
            addToBasket = addToBasket,
            deleteToBasket = deleteToBasket,
            getProducts = getProducts,
            chooseCategory = chooseCategory
        )
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(changePage, showBottomSheet, state.chooseTags.size)
        if (state.basket.isNotEmpty()) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                onClick = {
                    changePage("basket")
                }) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.basket),
                        contentDescription = "imBasket",
                        modifier = Modifier
                            .padding()
                            .size(15.dp)
                    )
                    Text(
                        text = "${state.basket.size}",
                        fontSize = 8.sp,
                        modifier = Modifier
                            .padding(bottom = 15.dp, start = 2.dp, end = 5.dp)
                    )
                    Text(text = "${getBasketPrice()} р")
                }
            }
        } else {
            Button(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                onClick = {
                    changePage("basket")
                }) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.basket),
                        contentDescription = "imBasket",
                        modifier = Modifier
                            .padding()
                            .size(15.dp)
                    )
                    Text(
                        text = "0",
                        fontSize = 8.sp,
                        modifier = Modifier
                            .padding(bottom = 15.dp, start = 2.dp)
                    )
                }
            }
        }
    }
    if(showBottomSheet.value){
        TagsBottomSheet(
            state = state,
            showBottomSheet = showBottomSheet,
            chooseTag = chooseTag,
            unChooseTag = unChooseTag
            )
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(ProductState().copy(
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
    ), {}, {}, {}, {1000}, {}, {}, {}, {emptyList()}, {}
    )
}