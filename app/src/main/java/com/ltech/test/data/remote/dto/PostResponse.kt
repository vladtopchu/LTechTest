package com.ltech.test.data.remote.dto

import com.ltech.test.domain.model.Post

data class PostResponse(
    var id: Int? = null,
    var title: String? = null,
    var text: String? = null,
    var image: String? = null,
    var sort: Int? = null,
    var date: String? = null
) {
    fun toModel() = Post(
        id ?: -1,
        title ?: "",
        text ?: "",
        image ?: "",
        sort ?: -1,
        date ?: ""
    )
}