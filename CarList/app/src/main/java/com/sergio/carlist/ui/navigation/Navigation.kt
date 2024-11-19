package com.sergio.carlist.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sergio.carlist.ui.screens.AddCarScreen
import com.sergio.carlist.ui.screens.CarListScreen
import com.sergio.carlist.ui.screens.CarDetailScreen
import com.sergio.carlist.ui.viewmodel.CarViewModel

sealed class Screen(val route: String) {
    object CarList : Screen("carList")
    object CarDetail : Screen("carDetail/{carIndex}")
    object AddCar : Screen("addCar")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: CarViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.CarList.route) {
        composable(Screen.CarList.route) {
            CarListScreen(
                cars = viewModel.cars,
                onCarClick = { index ->
                    navController.navigate("carDetail/$index")
                },
                onAddClick = {
                    navController.navigate(Screen.AddCar.route)
                }
            )
        }

        composable(
            Screen.CarDetail.route,
            arguments = listOf(navArgument("carIndex") { type = NavType.IntType })
        ) { backStackEntry ->
            val carIndex = backStackEntry.arguments?.getInt("carIndex") ?: 0
            if (carIndex < viewModel.cars.size) {
                CarDetailScreen(
                    car = viewModel.cars[carIndex],
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        composable(Screen.AddCar.route) {
            AddCarScreen(
                onCarAdded = { car ->
                    viewModel.addCar(car)
                    navController.popBackStack()
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
