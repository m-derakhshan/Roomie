package m.derakhshan.roomie.feature_wish_list.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import m.derakhshan.roomie.R
import m.derakhshan.roomie.core.NavGraph
import m.derakhshan.roomie.feature_home.presentation.composable.PropertyItem
import m.derakhshan.roomie.ui.theme.White
import m.derakhshan.roomie.ui.theme.add
import m.derakhshan.roomie.ui.theme.space

@Composable
fun WishListScreen(
    navController: NavController,
    viewModel: WishViewModel = hiltViewModel(),
    innerPadding: PaddingValues
) {
    val state = viewModel.wishes.value
    Scaffold(
        modifier = Modifier.padding(innerPadding),
        topBar = {
            TopAppBar {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(id = R.string.wish_list),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
        }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(state) { property ->
                PropertyItem(
                    item = property, modifier = Modifier
                        .padding(
                            start = MaterialTheme.space.medium,
                            end = MaterialTheme.space.medium,
                            bottom = MaterialTheme.space.small,
                            top = MaterialTheme.space.large
                        )
                        .shadow(2.dp, RoundedCornerShape(10.dp))
                        .clip(shape = RoundedCornerShape(10.dp))
                        .clickable {
                            navController.navigate(NavGraph.PropertyScreen.route + "/${property.id}")
                        }
                        .background(White, shape = RoundedCornerShape(10.dp))
                )

                Spacer(modifier = Modifier.padding(MaterialTheme.space.medium))
            }
        }
        if (state.isEmpty())
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.empty_wish_list),
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(top = MaterialTheme.space.large)
                        .fillMaxWidth()
                        .alpha(0.75f),
                    textAlign = TextAlign.Center
                )
                Image(
                    painter = painterResource(id = R.mipmap.no_wish_item),
                    contentDescription = null,
                    alpha = 0.25f
                )

            }
    }
}