package com.example.foodiesapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiesapp.ProductState
import com.example.foodiesapp.R
import com.example.foodiesapp.data.ProductModel
import com.example.foodiesapp.presentation.components.productscreen.CardItem
import com.example.foodiesapp.ui.theme.Orange
import com.example.foodiesapp.ui.theme.VeryLightGrey

@Composable
fun ProductScreen(
    state: ProductState,
    count: Int,
    addToBasket: (ProductModel) -> Unit,
    deleteToBasket: (ProductModel) -> Unit,
    changePage: (String) -> Unit
) {
    val product = state.entityProduct!!
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Box(){
                Image(
                    painter = painterResource(product.image),
                    contentDescription = "imProductCard",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    for(tag in product.tagIds) {
                        Image(
                            painter = painterResource(id = when(tag){
                                "1" -> R.drawable.type1
                                "2" -> R.drawable.type3
                                "3" -> R.drawable.type4
                                "4" -> R.drawable.type2
                                else -> R.drawable.type5
                            }),
                            contentDescription = "type1",
                            modifier = Modifier.padding(start = 10.dp, top = 40.dp).size(30.dp),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                }
            }
            Text(
                text = product.title,
                modifier = Modifier
                    .padding(10.dp),
                fontSize = 30.sp
            )
            Text(
                text = product.content,
                modifier = Modifier
                    .padding(start = 10.dp, end = 20.dp, bottom = 20.dp),
                color = Color.Gray
            )
            CardItem("Вес", "${product.weight} г")
            CardItem("Энерг. ценность", "${product.foodValue} ккал")
            CardItem("Белки", "${product.proteins} г")
            CardItem("Жиры", "${product.fats} г")
            CardItem("Углеводы", "${product.carbohydrates} г")
            if (count == 0) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),
                    onClick = {
                        addToBasket(product)
                    }) {
                    Text(
                        text = "В корзину за ${product.price} р",
                        fontSize = 16.sp
                    )
                    if (product.oldPrice != 0) {
                        Text(
                            text = " ${product.oldPrice} р",
                            style = TextStyle(
                                color = Color.Gray,
                                textDecoration = TextDecoration.LineThrough
                            ),
                            fontSize = 16.sp
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .background(Orange)
                            .padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp)
                            .align(Alignment.BottomCenter),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                deleteToBasket(product)
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(VeryLightGrey)
                                .size(40.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.minus),
                                contentDescription = "minus",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Text(
                            text = "$count",
                            fontSize = 18.sp,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                        )
                        IconButton(
                            onClick = {
                                addToBasket(product)
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(VeryLightGrey)
                                .size(40.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.plus),
                                contentDescription = "plus",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
    IconButton(onClick = {
        changePage("main")
    }) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductCard() {
    ProductScreen(ProductState().copy(
        entityProduct =
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
    ), 1, {}, {}, {})
}