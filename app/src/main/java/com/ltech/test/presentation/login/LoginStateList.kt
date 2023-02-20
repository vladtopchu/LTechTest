package com.ltech.test.presentation.login

data class LoginStateList(
    val isLoading: Boolean = false,
    val phoneMask: String? = null,
    val loginSuccess: Boolean? = null,
    val error: String? = null,

    val savedPhone: String? = null,
    val savedPassword: String? = null,
)