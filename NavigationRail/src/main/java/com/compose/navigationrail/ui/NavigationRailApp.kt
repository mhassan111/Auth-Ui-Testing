package com.compose.navigationrail.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compose.navigationrail.R
import com.compose.navigationrail.ui.components.BottomNavItemWithSelector

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationRailApp() {

    val galleries = GallerySections.values()
    val navController = rememberNavController()

    var currentRoute by rememberSaveable { mutableStateOf(navDestinations[0].route) }
    val updateRoute: (String) -> Unit = { newRoute -> currentRoute = newRoute }
    var imageId: Int? by rememberSaveable { mutableStateOf(null) }
    val updateImageId: (Int?) -> Unit = { updatedImageId -> imageId = updatedImageId }

    Scaffold(
        bottomBar = {
            GalleryBottomNav(
                navController = navController,
                galleries = galleries,
                updateImageId = updateImageId,
                updateRoute = updateRoute
            )
        }
    ) { paddingValues ->
        Row(Modifier.padding(paddingValues)) {
            NavHost(
                modifier = Modifier.onGloballyPositioned {
                    if (navController.currentDestination?.route != currentRoute) {
                        try {
                            navController.navigate(currentRoute)
                        } catch (e: NullPointerException) {
                            // Nav graph may be null if this is the first run through
                            Log.i(
                                "Navigation Rail Sample",
                                "Caught the following exception: ${e.message}"
                            )
                        }
                    }
                },
                navController = navController,
                startDestination = currentRoute
            ) {
                addGalleryGraph(
                    currentImageId = imageId,
                    onImageSelected = updateImageId,
                    horizontalPadding = GALLERY_HORIZ_PADDING
                )
            }
        }
    }
}


@Composable
fun GalleryBottomNav(
    navController: NavController,
    galleries: Array<GallerySections>,
    updateImageId: (Int?) -> Unit,
    updateRoute: (String) -> Unit
) {
    BottomNavigation(
        modifier = Modifier.testTag(stringResource(id = R.string.bottom_nav)),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination
        galleries.forEach { gallery ->
            BottomNavItemWithSelector(
                title = stringResource(id = gallery.title),
                icon = {
                    Icon(
                        painter = painterResource(id = gallery.icon),
                        contentDescription = stringResource(
                            id = gallery.title
                        )
                    )
                },
                label = {
                    Text(text = stringResource(id = gallery.title))
                },
                selected = currentDestination?.hierarchy?.any { it.route == gallery.route } == true,
                onClick = {
                    navItemOnClick(navController, gallery.route, updateImageId, updateRoute)
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unSelectedContentColor = MaterialTheme.colors.onPrimary
            )
        }
    }
}

private fun navItemOnClick(
    navController: NavController,
    navItem: String,
    updateImageId: (Int?) -> Unit,
    updateCurrentRoute: (String) -> Unit
) {
    navController.navigate(navItem) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
    updateCurrentRoute(navItem)
    updateImageId(null)
}