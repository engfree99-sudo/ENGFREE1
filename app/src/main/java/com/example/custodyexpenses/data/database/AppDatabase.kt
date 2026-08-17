package com.example.custodyexpenses.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.custodyexpenses.data.model.Operation
import com.example.custodyexpenses.data.model.AppSettings

@Database(entities = [Operation::class, AppSettings::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun operationDao(): OperationDao
    abstract fun settingsDao(): SettingsDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "custody_expenses_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
