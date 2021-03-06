package com.example.library.data.source

import androidx.room.*
import com.example.library.data.Photo

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photo: Photo)

    @Query("SELECT * FROM photo")
    fun loadAllUsers(): Array<Photo>

    @Delete
    fun delete(vararg photo: Photo)
}