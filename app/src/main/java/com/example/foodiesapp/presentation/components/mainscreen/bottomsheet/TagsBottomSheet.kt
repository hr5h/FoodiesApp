package com.example.foodiesapp.presentation.components.mainscreen.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodiesapp.ProductState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagsBottomSheet(
    state: ProductState,
    showBottomSheet: MutableState<Boolean>,
    chooseTag: (String) -> Unit,
    unChooseTag: (String) -> Unit,
) {
    ModalBottomSheet(onDismissRequest = {
        showBottomSheet.value = false
    }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            for (tag in state.tags){
                BottomSheetItem(title = tag.second, state.chooseTags.contains(tag.first)) {
                    if(it){
                        chooseTag(tag.first)
                    }
                    else{
                        unChooseTag(tag.first)
                    }
                }
            }
        }
    }
}