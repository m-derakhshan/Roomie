package m.derakhshan.roomie.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import m.derakhshan.roomie.feature_home.presentation.composable.HomeScreen
import m.derakhshan.roomie.feature_map.presentation.MapScreen
import m.derakhshan.roomie.feature_profile.presentation.ProfileScreen
import m.derakhshan.roomie.feature_splash_screen.SplashScreen
import m.derakhshan.roomie.feature_wish_list.presentation.WishListScreen
import m.derakhshan.roomie.ui.theme.RoomieTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        val screens = listOf(NavGraph.Home, NavGraph.WishList, NavGraph.Map, NavGraph.Profile)
        setContent {
            val navController = rememberNavController()
            RoomieTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            screens.forEach { screen ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(
                                            screen.icon,
                                            contentDescription = stringResource(id = screen.resourceId)
                                        )
                                    },
                                    label = { Text(stringResource(screen.resourceId)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }) { padding ->
                        NavHost(
                            navController = navController,
                            startDestination = NavGraph.Home.route
                        ) {
                            composable(NavGraph.Home.route) { HomeScreen(innerPadding = padding) }
                            composable(NavGraph.Profile.route) { ProfileScreen(innerPadding = padding) }
                            composable(NavGraph.WishList.route) { WishListScreen(innerPadding = padding) }
                            composable(NavGraph.Map.route) { MapScreen(innerPadding = padding) }
                        }
                    }
                    SplashScreen(removeScreen = viewModel.removeSplashScreen.value) { viewModel.removeSplashScreen() }
                }
            }
        }
    }
}