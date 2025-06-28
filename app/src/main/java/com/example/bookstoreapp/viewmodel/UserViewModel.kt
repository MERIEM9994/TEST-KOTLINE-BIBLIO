package com.example.bookstoreapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstoreapp.data.userremote.*
import com.example.bookstoreapp.data.usermodel.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState> = _loginState

    private val _registerState = MutableStateFlow(RegisterUiState())
    val registerState: StateFlow<RegisterUiState> = _registerState

    fun login(username: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = AuthService.api.login(LoginRequest(username, password))

                // Construire un User local
                val user = User(
                    id = response.userId,
                    username = response.username,
                    email = "", // non retourné
                    password = "", // non stocké
                    address = response.address,
                    role = response.role
                )
                _currentUser.value = user
                _loginState.value = LoginUiState(message = "Connexion réussie", isError = false)
                onSuccess()

            } catch (e: Exception) {
                Log.e("LoginError", "Erreur pendant la connexion", e)
                _loginState.value = LoginUiState(message = "Échec de la connexion", isError = true)
            }
        }
    }

    fun register(username: String, password: String, email: String, address: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = AuthService.api.register(
                    RegisterRequest(username, password, email, address)
                )

                _registerState.value = RegisterUiState(message = "Inscription réussie", isError = false)
                onSuccess()

            } catch (e: Exception) {
                Log.e("RegisterError", "Erreur pendant l'inscription", e)
                _registerState.value = RegisterUiState(message = "Échec de l'inscription", isError = true)
            }
        }
    }
}

// Pour l'état de connexion
data class LoginUiState(
    val message: String = "",
    val isError: Boolean = false
)

// Pour l'état d'inscription
data class RegisterUiState(
    val message: String = "",
    val isError: Boolean = false
)


