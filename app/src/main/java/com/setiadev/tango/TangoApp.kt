package com.setiadev.tango

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.setiadev.tango.ui.navigation.NavigationItem
import com.setiadev.tango.ui.navigation.Screen
import com.setiadev.tango.ui.screen.detail.DetailScreen
import com.setiadev.tango.ui.screen.home.HomeScreen
import com.setiadev.tango.ui.screen.profile.ProfileScreen
import com.setiadev.tango.ui.screen.team.TeamScreen
import com.setiadev.tango.ui.theme.TangoTheme

@ExperimentalFoundationApi
@Composable
fun TangoApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailPlayer.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { playerId ->
                        navController.navigate(Screen.DetailPlayer.createRoute(playerId)
                        )
                    }
                )
            }
            composable(Screen.Team.route) {
                TeamScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailPlayer.route,
                arguments = listOf(navArgument("playerId") {type = NavType.LongType}),
            ) {
                val id = it.arguments?.getLong("playerId") ?: -1L
                DetailScreen(
                    playerId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                icon = Icons.Default.Home,
                title = stringResource(R.string.menu_home),
                screen = Screen.Home,
            ),
            NavigationItem(
                icon = Icons.Default.Star,
                title = stringResource(R.string.menu_team),
                screen = Screen.Team
            ),
            NavigationItem(
                icon = Icons.Default.AccountCircle,
                title = stringResource(R.string.menu_profile),
                screen = Screen.Profile
            )
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                           Icon(
                               imageVector = item.icon,
                               contentDescription = item.title
                           )
                    },
                    label = {Text(item.title)},
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@ExperimentalFoundationApi
@Composable
fun TangoAppPreview() {
    TangoTheme {
        TangoApp()
    }
}