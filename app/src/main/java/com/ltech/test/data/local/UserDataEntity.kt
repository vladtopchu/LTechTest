package com.ltech.test.data.local

import androidx.room.Entity

@Entity("userData")
data class UserDataEntity (
    var phone: String,
    var password: String
)