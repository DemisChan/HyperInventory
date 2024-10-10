package com.hyperio.hyperinventory.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hyperio.hyperinventory.ui.inventoryEntry.EntryDetailScreen
import com.hyperio.hyperinventory.ui.inventoryEntry.EntryDetailsDestination
import com.hyperio.hyperinventory.ui.inventoryEntry.EntryEditDestination
import com.hyperio.hyperinventory.ui.inventoryEntry.EntryEditScreen
import com.hyperio.hyperinventory.ui.inventoryEntry.EntryInputDestination
import com.hyperio.hyperinventory.ui.inventoryEntry.EntryScreen
import com.hyperio.hyperinventory.ui.start.HomeDestination
import com.hyperio.hyperinventory.ui.start.StartScreen

@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.routeName,
        modifier = modifier
    ) {
        composable(route = HomeDestination.routeName) {
            StartScreen(
                navigateToItemEntry = { navController.navigate(EntryInputDestination.routeName) },
                navigateToItemUpdate = {
                    navController.navigate("${EntryDetailsDestination.routeName}/${it}")
                }
            )
        }
        composable(route = EntryInputDestination.routeName) {
            EntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = EntryDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(EntryDetailsDestination.entryIdArg) {
                type = NavType.IntType
            })
        ) {
            EntryDetailScreen(
                navigateToEditEntry = { navController.navigate("${EntryEditDestination.routeName}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = EntryEditDestination.routeWithArgs,
            arguments = listOf(navArgument(EntryEditDestination.entryIdArg) {
                type = NavType.IntType }
            )
        ) {
            EntryEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}