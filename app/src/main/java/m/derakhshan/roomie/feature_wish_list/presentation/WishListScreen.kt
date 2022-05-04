package m.derakhshan.roomie.feature_wish_list.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import m.derakhshan.roomie.R

@Composable
fun WishListScreen(innerPadding: PaddingValues) {
    Text(text = stringResource(id = R.string.wish_list))
}