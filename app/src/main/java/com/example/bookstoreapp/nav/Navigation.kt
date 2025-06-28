package com.example.bookstoreapp.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.bookstoreapp.ui.product.screens.ProductViewModel
import com.example.bookstoreapp.ui.product.component.DetailsProductScreen
import com.example.bookstoreapp.ui.product.screens.HomeScreen
import com.example.bookstoreapp.ui.screens.AccueilScreen
import com.example.bookstoreapp.ui.cart.CartScreen
import com.example.bookstoreapp.ui.cart.CartViewModel
import com.example.bookstoreapp.ui.cart.payer.PaiementScreen
import com.example.bookstoreapp.ui.cart.payer.ConfirmationScreen
import com.example.bookstoreapp.viewmodel.UserViewModel
import com.example.bookstoreapp.ui.auth.LoginScreen
import com.example.bookstoreapp.ui.auth.RegisterScreen
import com.example.bookstoreapp.ui.profile.ProfileScreen
import com.example.bookstoreapp.ui.admin.AdminDashboard

object Routes {
    const val ACCUEIL = "accueil"
    const val HOME = "home"
    const val BOOK_DETAILS = "bookDetails"
    const val CART = "cart"
    const val PAYMENT = "payment"
    const val CONFIRMATION = "confirmation"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val PROFILE = "profile"
    const val ADMIN_DASHBOARD = "admin_dashboard"
}

@Composable
fun AppNavigation(viewModel: ProductViewModel, userViewModel: UserViewModel = viewModel()) {
    val navController = rememberNavController()
    val cartViewModel = CartViewModel()
    val userState = userViewModel.currentUser.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = Routes.ACCUEIL
    ) {

        composable(Routes.ACCUEIL) {
            AccueilScreen(navController = navController)
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                viewModel = userViewModel,
                onLoginSuccess = {
                    when (userViewModel.currentUser.value?.role) {
                        "admin" -> navController.navigate(Routes.ADMIN_DASHBOARD)
                        else -> navController.navigate(Routes.HOME)
                    }
                }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                viewModel = userViewModel,
                onRegisterSuccess = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen(userViewModel)
        }

        composable(Routes.ADMIN_DASHBOARD) {
            AdminDashboard()
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
                navController = navController,
                onBackClick = { navController.popBackStack() },
                onAddToCart = {
                    cartViewModel.addToCart(book)
                    navController.navigate(Routes.CART)
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
                    cartViewModel.confirmPurchase()
                    navController.navigate(Routes.CONFIRMATION)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.CONFIRMATION) {
            ConfirmationScreen(
                onBackToHome = {
                    viewModel.fetchBooks() // recharge stock
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.ACCUEIL) { inclusive = false }
                    }
                }
            )
        }
    }
}








