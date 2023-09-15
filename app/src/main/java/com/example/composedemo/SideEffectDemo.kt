package com.example.composedemo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import com.example.composedemo.util.AnalyticsHelper
import com.example.composedemo.util.User

@Composable
fun rememberFirebaseAnalytics(user: User): AnalyticsHelper {
    val analytics: AnalyticsHelper = remember {
        AnalyticsHelper()
    }

    // On every successful composition, update AnalyticsHelper with
    // the userType from the current User, ensuring that future analytics
    // events have this metadata attached
    SideEffect {
        analytics.setUserType(user.userType)
    }

    return analytics
}