package com.example.custodyexpenses.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class AppSettings(
    @PrimaryKey val id: Int = 1,
    val currencyName: String = "جنيه مصري",
    val lastBackupDate: Long? = null
)
