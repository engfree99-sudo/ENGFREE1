package com.example.custodyexpenses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.custodyexpenses.ui.screens.HomeScreen
import com.example.custodyexpenses.ui.theme.CustodyExpensesTheme
import com.example.custodyexpenses.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    
    private lateinit var viewModel: MainViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        viewModel = MainViewModel(application)
        
        setContent {
            CustodyExpensesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        viewModel = viewModel,
                        context = this
                    )
                }
            }
        }
    }
}
