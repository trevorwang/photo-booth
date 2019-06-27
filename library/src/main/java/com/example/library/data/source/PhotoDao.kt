package com.example.library.data.source

import androidx.room.*
import com.example.library.data.Photo

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photo: Photo)

    @Query("SELECT * FROM photo")
    fun loadAllPhotos(): List<Photo>

    @Delete
    fun delete(vararg photo: Photo)

    @Update
    fun update(vararg photo: Photo)

    @Query("DELETE FROM photo")
    fun deleteAll()
}