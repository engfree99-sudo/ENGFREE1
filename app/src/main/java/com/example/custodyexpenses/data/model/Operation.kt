package com.example.custodyexpenses.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operations")
data class Operation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    val operationNumber: String,
    val type: OperationType,
    val statement: String,
    val expenseCategory: String? = null,
    val recipientName: String,
    val amount: Double,
    val paymentMethod: String? = null,
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

enum class OperationType {
    CUSTODY,  // عهد (Credit/دائن)
    EXPENSE   // مصروف (Debit/مدين)
}
