package com.ltech.test.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PostEntity::class, UserDataEntity::class],
    version = 1
)
abstract class LTechDatabase: RoomDatabase() {
    abstract val dao: AppDao
}