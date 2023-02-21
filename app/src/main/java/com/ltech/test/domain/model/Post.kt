package com.ltech.test.domain.model

data class Post(
    var id: String,
    var title: String,
    var text: String,
    var image: String,
    var sort: Int,
    var date: String
)