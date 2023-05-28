package com.iqbaltio.jetpacksubmission

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
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
import com.iqbaltio.jetpacksubmission.ui.navigation.NavigationItem
import com.iqbaltio.jetpacksubmission.ui.navigation.Screen
import com.iqbaltio.jetpacksubmission.ui.screen.about.AboutContent
import com.iqbaltio.jetpacksubmission.ui.screen.about.RoundedCornerBox
import com.iqbaltio.jetpacksubmission.ui.screen.detail.DetailScreen
import com.iqbaltio.jetpacksubmission.ui.screen.home.HomeScreen
import com.iqbaltio.jetpacksubmission.ui.theme.JetpackSubmissionTheme

@Composable
fun JetGamesApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailGame.route) {
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
                    navigateToDetail = { gameId ->
                        navController.navigate(Screen.DetailGame.createRoute(gameId))
                    }
                )
            }
            composable(Screen.About.route) {
                RoundedCornerBox()
                AboutContent(
                    image = R.drawable.profil,
                    name = "Iqbal Tio Ardiansyah",
                    email = "tiarharsono2001@gmail.com"
                )
            }
            composable(
                route = Screen.DetailGame.route,
                arguments = listOf(navArgument("gameId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("gameId") ?: -1L
                DetailScreen(
                    gameId = id
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Default.AccountCircle,
                screen = Screen.About
            ),
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
                    label = { Text(item.title) },
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

@Preview(showBackground = true)
@Composable
fun JetGamesAppPreview() {
    JetpackSubmissionTheme {
        JetGamesApp()
    }
}