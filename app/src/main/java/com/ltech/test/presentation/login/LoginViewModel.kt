package com.ltech.test.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ltech.test.domain.repository.LTechRepository
import com.ltech.test.utils.Constants
import com.ltech.test.utils.Constants.IO_ERROR_MESSAGE
import com.ltech.test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LTechRepository
): ViewModel() {

    private val mLoginState = MutableLiveData(LoginStateList())
    val loginState: LiveData<LoginStateList>
        get() = mLoginState

    init {

        viewModelScope.launch {
            val userData = repository.getUserData()
            if(userData == null) {
                repository.getPhoneMask().onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            val phoneMask = result.data?.phoneMask ?: ""
                            mLoginState.postValue(LoginStateList(phoneMask = phoneMask))
                        }
                        is Resource.Error -> {
                            mLoginState.postValue(LoginStateList(error = result.message ?: IO_ERROR_MESSAGE, phoneMask = Constants.DEFAULT_MASK))
                        }
                        is Resource.Loading -> {
                            mLoginState.postValue(LoginStateList(isLoading = true))
                        }
                    }
                }.launchIn(this)
            } else {
                mLoginState.postValue(
                    LoginStateList(
                        savedPhone = userData.phone,
                        savedPassword = userData.password
                    )
                )
            }
        }
    }

    fun login(phone: String, password: String) {
        repository.proceedAuth(phone, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val success = result.data?.success ?: false
                    mLoginState.postValue(LoginStateList(loginSuccess = success))
                    if (success)
                        repository.saveUserData(phone, password)
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