package com.example.composedemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.LaunchedEffectDemoNavHost.FirstScreenRoute
import com.example.composedemo.LaunchedEffectDemoNavHost.SecondScreenRoute
import kotlinx.coroutines.delay

@Composable
fun LaunchedEffectDemo(
    modifier: Modifier = Modifier
) {
    LaunchedEffectDemoNavHost(modifier = modifier)
}

@Composable
fun LaunchedEffectDemoFirstScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .then(modifier)
    ) {
        Text(text = "This is first Screen", style = MaterialTheme.typography.bodyMedium)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
        Button(onClick = { navHostController.navigate(SecondScreenRoute.route) }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Forward Button",
                tint = Color.White
            )
        }
    }
}

@Composable
fun LaunchedEffectDemoSecondScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    val DELAY_TIMER_VALUE = 5000L

    LaunchedEffect(key1 = Unit) {
        delay(DELAY_TIMER_VALUE)
        navHostController.popBackStack()
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
            .then(modifier)
    ) {
        Text(
            text = "This is second Screen which uses LaunchedEffect to go back to previous screen after 5 sec.",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun LaunchedEffectDemoNavHost(
    modifier: Modifier = Modifier
) {
    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = FirstScreenRoute.route) {
        composable(FirstScreenRoute.route) {
            LaunchedEffectDemoFirstScreen(modifier = modifier, navHostController = navHostController)
        }
        composable(SecondScreenRoute.route) {
            LaunchedEffectDemoSecondScreen(modifier = modifier, navHostController = navHostController)
        }
    }
}

@Preview
@Composable
fun LaunchedEffectDemoPreview(
    modifier: Modifier = Modifier
) {
    LaunchedEffectDemo()
}

sealed class LaunchedEffectDemoNavHost(val route: String) {
    object FirstScreenRoute : LaunchedEffectDemoNavHost("first_screen")
    object SecondScreenRoute : LaunchedEffectDemoNavHost("second_screen")
}