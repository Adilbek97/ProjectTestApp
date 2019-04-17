package com.example.projecttestapp.startPage

import com.example.projecttestapp.startPage.network.ApiService
import com.example.projecttestapp.startPage.network.ServiceBuilder

object Constants{
    const val MSG="asd"
    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_LOGIN = "login"
    val APP_PREFERENCES_PASSWORD = "password"
    val APP_PREFERENCES_KEY = "key"
    val userService = ServiceBuilder.builService(ApiService::class.java)
}