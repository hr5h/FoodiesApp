package com.example.foodiesapp.presentation.components.basketscreen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiesapp.R
import com.example.foodiesapp.data.ProductModel
import com.example.foodiesapp.ui.theme.LightGrey
import com.example.foodiesapp.ui.theme.VeryLightGrey

@Composable
fun BasketItem(
    product: ProductModel,
    count: Int,
    addToBasket: (ProductModel) -> Unit,
    deleteToBasket: (ProductModel) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(product.image),
                contentDescription = "imProductBasketItem",
                modifier = Modifier
                    .size(180.dp)
                    .padding(30.dp)
            )
            Column {
                Text(
                    text = product.title,
                    modifier = Modifier
                        .padding(5.dp),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(80.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                deleteToBasket(product)
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(VeryLightGrey)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.minus),
                                contentDescription = "minus",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Text(
                            text = "$count",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                        )
                        IconButton(
                            onClick = {
                                addToBasket(product)
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(VeryLightGrey),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.plus),
                                contentDescription = "plus",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(end = 10.dp),
                    ) {
                        Text(
                            text = "${product.price} р",
                            fontSize = 20.sp
                        )
                        if (product.oldPrice != 0) {
                            Text(
                                text = " ${product.oldPrice} р",
                                style = TextStyle(
                                    color = Color.Gray,
                                    textDecoration = TextDecoration.LineThrough
                                ),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
        Divider(color = LightGrey, thickness = 1.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBasketItem1() {
    BasketItem(ProductModel(
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
    ), 0, {}, {})
}