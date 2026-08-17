package com.example.custodyexpenses.data.repository

import com.example.custodyexpenses.data.database.OperationDao
import com.example.custodyexpenses.data.database.SettingsDao
import com.example.custodyexpenses.data.model.Operation
import com.example.custodyexpenses.data.model.OperationType
import com.example.custodyexpenses.data.model.AppSettings
import kotlinx.coroutines.flow.Flow

class OperationsRepository(private val operationDao: OperationDao, private val settingsDao: SettingsDao) {
    
    val allOperations: Flow<List<Operation>> = operationDao.getAllOperations()
    
    suspend fun getAllOperationsList(): List<Operation> = operationDao.getAllOperationsList()
    
    suspend fun getOperationById(id: Long): Operation? = operationDao.getOperationById(id)
    
    suspend fun getOperationByNumber(operationNumber: String): Operation? = 
        operationDao.getOperationByNumber(operationNumber)
    
    suspend fun insertOperation(operation: Operation): Long {
        return operationDao.insertOperation(operation)
    }
    
    suspend fun updateOperation(operation: Operation) {
        operationDao.updateOperation(operation)
    }
    
    suspend fun deleteOperation(operation: Operation) {
        operationDao.deleteOperation(operation)
    }
    
    suspend fun getTotalCustody(): Double = operationDao.getTotalByType(OperationType.CUSTODY) ?: 0.0
    
    suspend fun getTotalExpenses(): Double = operationDao.getTotalByType(OperationType.EXPENSE) ?: 0.0
    
    suspend fun getCustodyCount(): Int = operationDao.getCountByType(OperationType.CUSTODY)
    
    suspend fun getExpensesCount(): Int = operationDao.getCountByType(OperationType.EXPENSE)
    
    suspend fun getOperationsByDateRange(startDate: String, endDate: String): List<Operation> =
        operationDao.getOperationsByDateRange(startDate, endDate)
    
    fun searchByRecipient(query: String): Flow<List<Operation>> = operationDao.searchByRecipient(query)
    
    fun searchByStatement(query: String): Flow<List<Operation>> = operationDao.searchByStatement(query)
    
    fun searchByCategory(query: String): Flow<List<Operation>> = operationDao.searchByCategory(query)
    
    // Settings
    suspend fun getSettings(): AppSettings? = settingsDao.getSettings()
    
    suspend fun saveSettings(settings: AppSettings) {
        settingsDao.insertSettings(settings)
    }
    
    suspend fun updateSettings(settings: AppSettings) {
        settingsDao.updateSettings(settings)
    }
}
