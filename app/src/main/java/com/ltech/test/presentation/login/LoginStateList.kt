package com.ltech.test.presentation.login

data class LoginStateList(
    val isLoading: Boolean = false,
    val phoneMask: String? = null,
    val success: Boolean? = null,
    val error: String? = null
)