package com.ltech.test.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ltech.test.domain.model.PhoneMask
import com.ltech.test.domain.use_case.AuthUseCase
import com.ltech.test.domain.use_case.GetPhoneMaskUseCase
import com.ltech.test.utils.Constants.IO_ERROR_MESSAGE
import com.ltech.test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    getPhoneMaskUseCase: GetPhoneMaskUseCase,
    private val authUseCase: AuthUseCase
): ViewModel() {

    private val mLoginState = MutableLiveData(LoginStateList())
    val loginState: LiveData<LoginStateList>
        get() = mLoginState

    init {
        getPhoneMaskUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val phoneMask = result.data?.phoneMask ?: ""
                    mLoginState.postValue(LoginStateList(phoneMask = phoneMask))
                }
                is Resource.Error -> {
                    mLoginState.postValue(LoginStateList(error = result.message ?: IO_ERROR_MESSAGE))
                }
                is Resource.Loading -> {
                    mLoginState.postValue(LoginStateList(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun login(phone: String, password: String) {
        authUseCase(phone, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val success = result.data?.success ?: false
                    mLoginState.postValue(LoginStateList(success = success))
                }
                is Resource.Error -> {
                    mLoginState.postValue(LoginStateList(error = result.message ?: IO_ERROR_MESSAGE))
                }
                is Resource.Loading -> {
                    mLoginState.postValue(LoginStateList(isLoading = true))
                }
            }
        }.launchIn(viewModelScope)
    }


}