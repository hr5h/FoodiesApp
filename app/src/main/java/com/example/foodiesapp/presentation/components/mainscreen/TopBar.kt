package com.example.foodiesapp.presentation.components.mainscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiesapp.R
import com.example.foodiesapp.ui.theme.Orange

@Composable
fun TopBar(
    changePage: (String) -> Unit,
    showBottomSheet: MutableState<Boolean>,
    count: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            IconButton(onClick = {
                showBottomSheet.value = true
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "imFilter",
                    modifier = Modifier.size(20.dp)
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 4.dp, end = 2.dp)
                    .size(15.dp)
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(80.dp))
                    .background(Orange),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$count",
                    fontSize = 8.sp,
                    color = Color.White,
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "imLogo",
            modifier = Modifier.size(80.dp)
        )
        IconButton(onClick = {
            changePage("search")
        }) {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "imSearch",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    TopBar({}, mutableStateOf(false), 0)
}