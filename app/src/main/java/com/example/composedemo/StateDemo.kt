package com.example.composedemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StateDemo() {
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(
            onClick = { counter++ },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Increment")
        }
        Text(
            text = "Count: $counter",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun StateDemoPreview(
    modifier: Modifier = Modifier
) {
    StateDemo()
}