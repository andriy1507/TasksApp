package com.spaceapps.tasks.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spaceapps.tasks.core.extensions.async
import com.spaceapps.tasks.core.extensions.safeAsync
import com.spaceapps.tasks.core.model.Status
import com.spaceapps.tasks.core.repository.AuthorizationRepository
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val authRepository: AuthorizationRepository) :
    ViewModel() {

    enum class State {
        SIGN_IN,
        SIGN_UP
    }

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    private val _exists = MutableLiveData<Boolean>()
    val exists: LiveData<Boolean>
        get() = _exists

    private val _authorized = MutableLiveData<Status>()
    val authorized: LiveData<Status>
        get() = _authorized

    init {
        _exists.postValue(!authRepository.getAuthToken().isNullOrBlank())
        _state.postValue(State.SIGN_IN)
    }

    fun toggleState() {
        _state.postValue(when(state.value){
            State.SIGN_IN, null -> State.SIGN_UP
            State.SIGN_UP -> State.SIGN_IN
        })
    }

    fun requestLogin(username: String, password: String) = async {
        _authorized.postValue(Status.Loading)
        _authorized.postValue(safeAsync {
            val token = authRepository.login(username, password)
            authRepository.storeAuthorizationToken(token)
            token.isBlank()
        })
    }

    fun requestRegistration(username: String, password: String) = async {
        _authorized.postValue(Status.Loading)
        _authorized.postValue(safeAsync {
            val token = authRepository.register(username, password)
            authRepository.storeAuthorizationToken(token)
            token.isBlank()
        })
    }

    class Factory @Inject constructor(private val authRepository: AuthorizationRepository) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SignInViewModel(authRepository) as T
        }
    }
}