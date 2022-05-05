package m.derakhshan.roomie.feature_home.presentation.property_description

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import m.derakhshan.roomie.feature_home.domain.model.PropertyModel


@Composable
fun PropertyDescriptionScreen(
    paddingValues: PaddingValues,
    property: PropertyModel,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {

    }

}