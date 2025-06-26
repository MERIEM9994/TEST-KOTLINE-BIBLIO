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
import com.example.bookstoreapp.ui.screens.AccueilScreen // 👈 nouveau import

object Routes {
    const val ACCUEIL = "accueil"
    const val HOME = "home"
    const val BOOK_DETAILS = "bookDetails"
}

@Composable
fun AppNavigation(viewModel: ProductViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.ACCUEIL // 👈 on démarre par l'écran d'accueil
    ) {
        // 👋 Nouvel écran d'accueil
        composable(Routes.ACCUEIL) {
            AccueilScreen(navController = navController)
        }

        // 📚 Liste des livres
        composable(Routes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onBookClick = { bookId ->
                    navController.navigate("${Routes.BOOK_DETAILS}/$bookId")
                }
            )
        }

        // 📖 Détails d’un livre
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
