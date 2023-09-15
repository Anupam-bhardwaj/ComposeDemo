package com.example.composedemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun DerivedStateDemo(
    modifier: Modifier = Modifier
) {

    val items = (1..100).toList()
    MessageList(items = items)

}


// When the messages parameter changes, the MessageList
// composable recomposes. derivedStateOf does not
// affect this recomposition.
@Composable
fun MessageList(items: List<Int>) {
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        val listState = rememberLazyListState()

        val coroutineScope = rememberCoroutineScope()

        LazyColumn(state = listState) {
            itemsIndexed(items) { index, item ->
                Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Text(text = item.toString(), style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        // Show the button if the first visible item is past
        // the first item. We use a remembered derived state to
        // minimize unnecessary compositions
        val showButton by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 5
            }
        }

        AnimatedVisibility(
            visible = showButton,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkHorizontally(),
            modifier = Modifier.align(Alignment.BottomEnd).padding(18.dp)
        ) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }

                },
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "ArrowUp")
            }
        }
    }
}