package com.example.foodiesapp.presentation.components.mainscreen.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.foodiesapp.R
import com.example.foodiesapp.data.ProductModel
import com.example.foodiesapp.ui.theme.VeryLightGrey

@Composable
fun CatalogItem(
    product: ProductModel,
    count: Int,
    chooseProduct: (ProductModel) -> Unit,
    addToBasket: (ProductModel) -> Unit,
    deleteToBasket: (ProductModel) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    chooseProduct(product)
                }
                .background(color = VeryLightGrey)
                .padding(5.dp),
        ) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = "imCatalogItem",
            )
            Text(
                text = product.title,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = "${product.weight} г",
                modifier = Modifier.padding(start = 10.dp)
            )
            if (count == 0) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(
                            start = 5.dp,
                            end = 5.dp
                        ),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    onClick = {
                        addToBasket(product)
                    }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${product.price} р",
                            color = Color.Black
                        )
                        if (product.oldPrice != 0) {
                            Text(
                                text = " ${product.oldPrice} р",
                                style = TextStyle(
                                    color = Color.Gray,
                                    textDecoration = TextDecoration.LineThrough
                                )
                            )
                        }
                    }
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
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
                            .background(VeryLightGrey)
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
                    modifier = Modifier.padding(10.dp).size(30.dp),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCatalogItem() {
    CatalogItem(product = ProductModel(
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
    ), count = 0, chooseProduct = {}, addToBasket = {}, deleteToBasket = {})
}