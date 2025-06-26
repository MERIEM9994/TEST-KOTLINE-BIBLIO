package com.example.bookstoreapp.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookstoreapp.ui.product.screens.ProductViewModel
import com.example.bookstoreapp.ui.product.component.DetailsProductScreen
import com.example.bookstoreapp.ui.product.screens.HomeScreen
import com.example.bookstoreapp.ui.screens.AccueilScreen
import com.example.bookstoreapp.ui.cart.CartScreen
import com.example.bookstoreapp.ui.cart.CartViewModel
import com.example.bookstoreapp.ui.cart.payer.PaiementScreen

object Routes {
    const val ACCUEIL = "accueil"
    const val HOME = "home"
    const val BOOK_DETAILS = "bookDetails"
    const val CART = "cart"
    const val PAYMENT = "payment"
}

@Composable
fun AppNavigation(viewModel: ProductViewModel) {
    val navController = rememberNavController()
    val cartViewModel = CartViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.ACCUEIL
    ) {
        composable(Routes.ACCUEIL) {
            AccueilScreen(navController = navController)
        }

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
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: return@composable
            val book = viewModel.getBookById(bookId) ?: return@composable

            DetailsProductScreen(
                book = book,
                navController = navController, // ✅ Passage du navController
                onBackClick = { navController.popBackStack() },
                onAddToCart = {
                    cartViewModel.addToCart(book)       // ✅ ajout au panier
                    navController.navigate(Routes.CART) // ✅ redirection vers panier
                }
            )
        }

        composable(Routes.CART) {
            CartScreen(
                cartViewModel = cartViewModel,
                onCheckoutClick = {
                    navController.navigate(Routes.PAYMENT)
                },
                onClearCartClick = {
                    cartViewModel.clearCart()
                }
            )
        }

        composable(Routes.PAYMENT) {
            val uiState = cartViewModel.uiState.collectAsState().value
            val total = if (uiState is com.example.bookstoreapp.ui.cart.CartUiState.Success) {
                uiState.total
            } else {
                0.0
            }

            PaiementScreen(
                total = total,
                onValidatePayment = {
                    cartViewModel.confirmPurchase()  // confirmer l'achat
                    cartViewModel.clearCart()        // vider panier
                    navController.popBackStack(Routes.HOME, false)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}





