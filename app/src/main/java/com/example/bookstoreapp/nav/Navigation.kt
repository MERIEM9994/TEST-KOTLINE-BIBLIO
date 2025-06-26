package com.example.bookstoreapp.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookstoreapp.ui.product.screens.ProductViewModel
import com.example.bookstoreapp.ui.product.component.DetailsProductScreen
import com.example.bookstoreapp.ui.product.screens.HomeScreen

object Routes {
    const val HOME = "home"
    const val BOOK_DETAILS = "bookDetails"
}

@Composable
fun AppNavigation(viewModel: ProductViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onBookClick = { bookId ->
                    navController.navigate("${Routes.BOOK_DETAILS}/$bookId")
                }
            )
        }

        composable(
            route = "${Routes.BOOK_DETAILS}/{bookId}",
            arguments = listOf(
                navArgument("bookId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val bookIdString = backStackEntry.arguments?.getString("bookId") ?: return@composable
            val bookId = bookIdString.toIntOrNull() ?: return@composable

            val book = viewModel.getBookById(bookId) ?: return@composable
            DetailsProductScreen(
                book = book,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
