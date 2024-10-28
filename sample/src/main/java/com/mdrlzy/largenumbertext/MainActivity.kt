package com.mdrlzy.largenumbertext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mdrlzy.largenumbertext.ui.theme.LargeNumberTextTheme
import java.math.BigDecimal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LargeNumberTextTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Content(modifier: Modifier) {
    var numberInput by remember {
        mutableStateOf("")
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.padding(horizontal = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = numberInput,
                onValueChange = { numberInput = it },
                keyboardOptions =
                KeyboardOptions.Default
                    .copy(keyboardType = KeyboardType.Number),
                singleLine = true
            )
            Spacer(Modifier.height(30.dp))
            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                VerticalDivider(color = Color.Red)
                LargeNumberText(
                    modifier = Modifier.weight(1f),
                    number = BigDecimal(numberInput.ifEmpty { "0" }),
                    buildText = { formattedNumber ->
                        "Large number text (maxLines = 2) goes $formattedNumber"
                    },
                    maxLines = 2,
                )
                VerticalDivider(color = Color.Red)
            }
        }
    }
}