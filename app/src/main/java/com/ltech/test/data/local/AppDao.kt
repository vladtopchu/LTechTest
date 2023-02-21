package com.ltech.test.data.local

import androidx.room.*

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserData(userEntity: UserDataEntity)

    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUserData(): UserDataEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Query("SELECT * FROM posts")
    suspend fun getPosts(): List<PostEntity>

    @Query("DELETE FROM posts")
    suspend fun clearPosts()
    @Query("SELECT * FROM posts WHERE id=:postId LIMIT 1")
    suspend fun getPostById(postId: String): PostEntity
}