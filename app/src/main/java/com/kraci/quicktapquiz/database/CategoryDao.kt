package com.kraci.quicktapquiz.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Insert
    fun insert(category: Category): Long

    @Query("DELETE FROM categories")
    fun deleteAll()

}