package com.example.custodyexpenses.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.custodyexpenses.data.database.AppDatabase
import com.example.custodyexpenses.data.model.AppSettings
import com.example.custodyexpenses.data.model.Operation
import com.example.custodyexpenses.data.model.OperationType
import com.example.custodyexpenses.data.repository.OperationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    
    private val database = AppDatabase.getDatabase(application)
    private val repository: OperationsRepository
    
    init {
        val settingsDao = database.settingsDao()
        // Initialize settings if not exists
        viewModelScope.launch {
            if (settingsDao.getSettings() == null) {
                settingsDao.insertSettings(AppSettings())
            }
        }
        repository = OperationsRepository(database.operationDao(), settingsDao)
    }
    
    val allOperations: Flow<List<Operation>> = repository.allOperations
    
    suspend fun getAllOperationsList(): List<Operation> = repository.getAllOperationsList()
    
    suspend fun getOperationById(id: Long): Operation? = repository.getOperationById(id)
    
    suspend fun getOperationByNumber(operationNumber: String): Operation? = 
        repository.getOperationByNumber(operationNumber)
    
    fun addOperation(operation: Operation, onComplete: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try {
                // Check for duplicate operation number
                val existing = repository.getOperationByNumber(operation.operationNumber)
                if (existing != null && existing.id != operation.id) {
                    onComplete(false, "error_duplicate_operation")
                    return@launch
                }
                
                if (operation.amount <= 0) {
                    onComplete(false, "error_invalid_amount")
                    return@launch
                }
                
                repository.insertOperation(operation)
                onComplete(true, null)
            } catch (e: Exception) {
                onComplete(false, e.message)
            }
        }
    }
    
    fun updateOperation(operation: Operation, onComplete: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try {
                // Check for duplicate operation number
                val existing = repository.getOperationByNumber(operation.operationNumber)
                if (existing != null && existing.id != operation.id) {
                    onComplete(false, "error_duplicate_operation")
                    return@launch
                }
                
                if (operation.amount <= 0) {
                    onComplete(false, "error_invalid_amount")
                    return@launch
                }
                
                repository.updateOperation(operation)
                onComplete(true, null)
            } catch (e: Exception) {
                onComplete(false, e.message)
            }
        }
    }
    
    fun deleteOperation(operation: Operation, onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.deleteOperation(operation)
            onComplete()
        }
    }
    
    suspend fun getTotalCustody(): Double = repository.getTotalCustody()
    
    suspend fun getTotalExpenses(): Double = repository.getTotalExpenses()
    
    suspend fun getCurrentBalance(): Double {
        return repository.getTotalCustody() - repository.getTotalExpenses()
    }
    
    suspend fun getCustodyCount(): Int = repository.getCustodyCount()
    
    suspend fun getExpensesCount(): Int = repository.getExpensesCount()
    
    suspend fun getSettings(): AppSettings? = repository.getSettings()
    
    fun saveSettings(settings: AppSettings) {
        viewModelScope.launch {
            repository.saveSettings(settings)
        }
    }
    
    fun updateSettings(settings: AppSettings) {
        viewModelScope.launch {
            repository.updateSettings(settings)
        }
    }
}
