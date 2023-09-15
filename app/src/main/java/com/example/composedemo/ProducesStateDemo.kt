package com.example.composedemo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
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