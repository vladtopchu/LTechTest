package com.ltech.test.data.local

import androidx.room.Entity

@Entity
data class UserConfigEntity (
    var phone: String,
    var password: String
)