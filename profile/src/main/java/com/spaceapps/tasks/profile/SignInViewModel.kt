package com.spaceapps.tasks.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spaceapps.tasks.core.extensions.asyncIO
import com.spaceapps.tasks.core.repository.AuthorizationRepository
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val authRepository: AuthorizationRepository) :
    ViewModel() {

    fun requestLogin(username: String, password: String) = asyncIO {
        val token = authRepository.login(username, password)
        authRepository.storeAuthorizationToken(token)
        Log.d(this::class.java.simpleName, token)
    }

    class Factory @Inject constructor(private val authRepository: AuthorizationRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SignInViewModel(authRepository) as T
        }
    }
}