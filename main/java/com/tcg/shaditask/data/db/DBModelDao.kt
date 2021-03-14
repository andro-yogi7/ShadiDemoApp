package com.tcg.shaditask.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tcg.shaditask.data.db.entities.DBModel

@Dao
interface DBModelDao {
    //to insert row...if present it will ignore and return -1
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWithOnConflictDBModel(dbModel: DBModel): Long

    //get all notification
    @Query("SELECT * FROM DBModel")
    fun getAllDBModel(): LiveData<List<DBModel>>

    @Update
    fun updateDBModel(dbModel: DBModel):Int
}