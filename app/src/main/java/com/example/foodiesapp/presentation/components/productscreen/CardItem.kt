package com.example.foodiesapp.presentation.components.productscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodiesapp.ui.theme.LightGrey

@Composable
fun CardItem(string1: String, string2: String){
    Divider(color = LightGrey, thickness = 1.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,

        ) {
        Text(text = string1, color = Color.Gray)
        Text(text = string2)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardItem(){
    CardItem("Вес", "400 г")
}
