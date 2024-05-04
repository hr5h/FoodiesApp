package com.example.foodiesapp.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiesapp.ProductState
import com.example.foodiesapp.data.ProductModel
import com.example.foodiesapp.presentation.components.mainscreen.catalog.CatalogItem

@Composable
fun SearchScreen(
    state: ProductState,
    changePage: (String) -> Unit,
    chooseProduct: (ProductModel) -> Unit,
    addToBasket: (ProductModel) -> Unit,
    deleteToBasket: (ProductModel) -> Unit,
    getProducts: (String) -> List<ProductModel>
) {
    val textState = remember {
        mutableStateOf("")
    }
    //tags
    val list = getProducts(textState.value)
    for (it in list) {
        Log.d("MyLog", it.toString())
    }
    Column {
        Spacer(modifier = Modifier.height(50.dp))
        if(list.isNotEmpty()) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                columns = GridCells.Fixed(2)
            ) {
                itemsIndexed(list) { _, item ->
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
        else if(textState.value == "") {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Введите название блюда, которое ищете",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 80.dp, end = 80.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        }
        else{
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Ничего не нашлось :(",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 80.dp, end = 80.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            changePage("main")
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
        }
        TextField(
            value = textState.value, onValueChange = {
                textState.value = it
            },
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
            label = {
                Text(text = "Найти блюдо", color = Color.Gray, fontSize = 14.sp)
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 14.sp)
        )
        IconButton(onClick = {
            textState.value = ""
        }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    SearchScreen(ProductState(), {}, {}, {}, {}, {emptyList()})
}