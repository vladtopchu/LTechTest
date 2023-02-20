package com.ltech.test.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ltech.test.domain.model.UserData

@Entity("user")
data class UserDataEntity (
    @PrimaryKey
    var phone: String,
    var password: String
) {
    fun toModel(): UserData {
        return UserData(
            this.phone,
            this.password
        )
    }
}