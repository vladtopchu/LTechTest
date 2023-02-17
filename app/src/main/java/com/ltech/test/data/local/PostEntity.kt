package com.ltech.test.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("posts")
data class PostEntity (
    @PrimaryKey
    var id: Int,
    var title: String,
    var text: String,
    var image: String,
    var sort: Int,
    var date: String
)