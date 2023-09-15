package com.example.composedemo.util

class AnalyticsHelper {

    private var userType: String? = ""

    fun setUserType(type: String) {
        userType = type
    }

}


data class User (val name: String, val age: Int, val mobileNumber: String, val userType: String)