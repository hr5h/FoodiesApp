package com.example.foodiesapp.presentation.components.basketscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasketBar(
    changePage: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            changePage("main")
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
        }
        Text(
            text = "Корзина",
            modifier = Modifier
                .padding(start = 15.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewBasketBar()
{
    BasketBar({})
}