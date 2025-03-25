package com.example.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FontSelectorApp()
        }
    }
}

@Composable
fun FontSelectorApp() {
    var textState by remember { mutableStateOf(TextFieldValue()) }
    var selectedFontSize by remember { mutableStateOf(16.sp) }
    var displayedText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val fontSizes = listOf(12.sp, 16.sp, 20.sp, 24.sp)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Виберіть розмір шрифту:")
        fontSizes.forEach { size ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (selectedFontSize == size),
                    onClick = { selectedFontSize = size }
                )
                Text(text = "${size.value.toInt()}sp", fontSize = size)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text("Введіть текст") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = {
                if (textState.text.isEmpty()) {
                    showDialog = true
                } else {
                    displayedText = textState.text
                }
            }) {
                Text("OK")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                textState = TextFieldValue()
                displayedText = ""
            }) {
                Text("Cancel")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(displayedText, fontSize = selectedFontSize)
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            },
            title = { Text("Помилка") },
            text = { Text("Будь ласка, введіть текст перед натисканням 'OK'.") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFontSelectorApp() {
    FontSelectorApp()
}
