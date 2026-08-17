package com.example.custodyexpenses.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.custodyexpenses.ui.theme.*
import com.example.custodyexpenses.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: MainViewModel, context: Context) {
    var totalCustody by remember { mutableStateOf(0.0) }
    var totalExpenses by remember { mutableStateOf(0.0) }
    var currentBalance by remember { mutableStateOf(0.0) }
    var currency by remember { mutableStateOf("جنيه مصري") }
    var showNegativeWarning by remember { mutableStateOf(false) }
    
    val scope = rememberCoroutineScope()
    
    LaunchedEffect(Unit) {
        scope.launch {
            totalCustody = viewModel.getTotalCustody()
            totalExpenses = viewModel.getTotalExpenses()
            currentBalance = viewModel.getCurrentBalance()
            viewModel.getSettings()?.let { currency = it.currencyName }
            showNegativeWarning = currentBalance < 0
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "إدارة العهد والمصروفات",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        SummaryCard(
            title = "إجمالي العهد",
            amount = totalCustody,
            currency = currency,
            color = CustodyGreen,
            icon = "+"
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        SummaryCard(
            title = "إجمالي المصروفات",
            amount = totalExpenses,
            currency = currency,
            color = ExpenseRed,
            icon = "-"
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        SummaryCard(
            title = "الرصيد الحالي",
            amount = currentBalance,
            currency = currency,
            color = if (currentBalance >= 0) BalanceBlue else Error,
            icon = "=",
            isBalance = true
        )
        
        if (showNegativeWarning) {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Warning.copy(alpha = 0.2f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⚠️ تنبيه: المصروفات تجاوزت قيمة العهد",
                        color = Color(0xFFE65100),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        LargeButton(
            text = "+ إضافة عهد",
            onClick = { },
            backgroundColor = CustodyGreen,
            textColor = Color.White
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        LargeButton(
            text = "- إضافة مصروف",
            onClick = { },
            backgroundColor = ExpenseRed,
            textColor = Color.White
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        LargeButton(
            text = "كشف الحساب",
            onClick = { },
            backgroundColor = Primary,
            textColor = Color.White
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        LargeButton(
            text = "تصدير Excel",
            onClick = { },
            backgroundColor = Success,
            textColor = Color.White
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary)
        ) {
            Text(text = "⚙️ الإعدادات", fontSize = 18.sp)
        }
    }
}

@Composable
fun SummaryCard(
    title: String,
    amount: Double,
    currency: String,
    color: Color,
    icon: String,
    isBalance: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = TextSecondary,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = String.format("%,.2f", amount) + " $currency",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = color
                )
            }
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(color.copy(alpha = 0.1f), shape = MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = icon,
                    fontSize = 32.sp,
                    color = color,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun LargeButton(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
    ) {
        Text(text = text, fontSize = 18.sp, color = textColor)
    }
}
