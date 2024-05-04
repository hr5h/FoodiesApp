package com.example.foodiesapp.presentation.components.mainscreen.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiesapp.ui.theme.LightGrey
import com.example.foodiesapp.ui.theme.Orange

@Composable
fun BottomSheetItem(
    title: String,
    isChoose: Boolean,
    chooseTag: (Boolean) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 10.dp)
            )
            Checkbox(checked = isChoose, onCheckedChange = {
                chooseTag(it)
            },
                colors = CheckboxDefaults.colors(checkedColor = Orange)
            )
        }
    }
    Divider(color = LightGrey, thickness = 1.dp)
}

@Preview(showBackground = true)
@Composable
fun PreviewBottomSheetItme() {
    BottomSheetItem("Со скидкой", true, {})
}