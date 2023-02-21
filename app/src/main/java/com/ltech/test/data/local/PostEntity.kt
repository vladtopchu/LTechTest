package com.ltech.test.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ltech.test.domain.model.Post

@Entity("posts")
data class PostEntity (
    @PrimaryKey
    var id: String,
    var title: String,
    var text: String,
    var image: String,
    var sort: Int,
    var date: String
) {
    fun toModel(): Post {
        return Post(
            id = this.id,
            title = this.title,
            text = this.text,
            image = this.image,
            sort = this.sort,
            date = this.date
        )
    }
}