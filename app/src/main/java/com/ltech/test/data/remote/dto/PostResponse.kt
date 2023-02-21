package com.ltech.test.data.remote.dto

import com.ltech.test.domain.model.Post

data class PostResponse(
    var id: String? = null,
    var title: String? = null,
    var text: String? = null,
    var image: String? = null,
    var sort: Int? = null,
    var date: String? = null
) {
    fun toModel() = Post(
        id ?: "",
        title ?: "",
        text ?: "",
        image ?: "",
        sort ?: -1,
        date ?: ""
    )
}