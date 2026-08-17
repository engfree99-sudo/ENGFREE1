package com.example.custodyexpenses.data.database

import androidx.room.*
import com.example.custodyexpenses.data.model.AppSettings

@Dao
interface SettingsDao {
    
    @Query("SELECT * FROM settings WHERE id = 1")
    suspend fun getSettings(): AppSettings?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSettings(settings: AppSettings)
    
    @Update
    suspend fun updateSettings(settings: AppSettings)
}
