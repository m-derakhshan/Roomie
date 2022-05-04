package m.derakhshan.roomie.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import m.derakhshan.roomie.R

sealed class NavGraph(
    val resourceId: Int, val route: String, val icon: ImageVector
) {
    object Home : NavGraph(resourceId = R.string.home, route = "home", icon = Icons.Default.Home)
    object Profile :
        NavGraph(resourceId = R.string.profile, route = "profile", icon = Icons.Default.Person)

    object Map : NavGraph(resourceId = R.string.map, route = "map", icon = Icons.Default.Map)
    object WishList : NavGraph(
        resourceId = R.string.wish_list,
        route = "wish_list",
        icon = Icons.Default.Favorite
    )
}
