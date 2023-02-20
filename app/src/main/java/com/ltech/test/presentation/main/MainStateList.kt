package com.ltech.test.presentation.main

import com.ltech.test.domain.model.Post

data class MainStateList(
    val isLoading: Boolean = false,
    val posts: List<Post>? = null,
    val error: String? = null
)