package com.example.composedemo

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun producesStateDemo (countupTo: Int): State<Int> {
    return produceState(initialValue = 0) {
        while (value < countupTo) {
            delay(1000L)
            value++
        }
    }
}

@Preview
@Composable
fun ProduceStateUsage(
    modifier: Modifier = Modifier
) {

    val counter = producesStateDemo(countupTo = 5)

    Text(
        text = "Count: ${counter.value}",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(16.dp)
    )

}