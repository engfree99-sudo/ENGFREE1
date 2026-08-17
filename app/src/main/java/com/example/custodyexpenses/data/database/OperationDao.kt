package com.example.custodyexpenses.data.database

import androidx.room.*
import com.example.custodyexpenses.data.model.Operation
import com.example.custodyexpenses.data.model.OperationType
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {
    
    @Query("SELECT * FROM operations ORDER BY date DESC, createdAt DESC")
    fun getAllOperations(): Flow<List<Operation>>
    
    @Query("SELECT * FROM operations ORDER BY date DESC, createdAt DESC")
    suspend fun getAllOperationsList(): List<Operation>
    
    @Query("SELECT * FROM operations WHERE id = :id")
    suspend fun getOperationById(id: Long): Operation?
    
    @Query("SELECT * FROM operations WHERE operationNumber = :operationNumber")
    suspend fun getOperationByNumber(operationNumber: String): Operation?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOperation(operation: Operation): Long
    
    @Update
    suspend fun updateOperation(operation: Operation)
    
    @Delete
    suspend fun deleteOperation(operation: Operation)
    
    @Query("SELECT SUM(amount) FROM operations WHERE type = :type")
    suspend fun getTotalByType(type: OperationType): Double?
    
    @Query("SELECT COUNT(*) FROM operations WHERE type = :type")
    suspend fun getCountByType(type: OperationType): Int
    
    @Query("SELECT * FROM operations WHERE date BETWEEN :startDate AND :endDate ORDER BY date ASC, createdAt ASC")
    suspend fun getOperationsByDateRange(startDate: String, endDate: String): List<Operation>
    
    @Query("SELECT * FROM operations WHERE type = :type ORDER BY date DESC, createdAt DESC")
    fun getOperationsByType(type: OperationType): Flow<List<Operation>>
    
    @Query("SELECT * FROM operations WHERE recipientName LIKE '%' || :query || '%' ORDER BY date DESC, createdAt DESC")
    fun searchByRecipient(query: String): Flow<List<Operation>>
    
    @Query("SELECT * FROM operations WHERE statement LIKE '%' || :query || '%' ORDER BY date DESC, createdAt DESC")
    fun searchByStatement(query: String): Flow<List<Operation>>
    
    @Query("SELECT * FROM operations WHERE expenseCategory LIKE '%' || :query || '%' ORDER BY date DESC, createdAt DESC")
    fun searchByCategory(query: String): Flow<List<Operation>>
}
