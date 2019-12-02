package com.app.news

import android.content.Context
import android.net.ConnectivityManager

class Utils {

    companion object {

        fun isNetworkAvailable(context: Context): Boolean {
            var isConnected = false
            var connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo?.let { isConnected = it.isConnected }
            return isConnected
        }

    }
}