package com.example.spacex_api

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.widget.Toast

class ConnectivityListener(private val context: Context,
                           private val callBack: (Boolean) -> Unit) {

    private val networkCallback by lazy {
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                callBack.invoke(true)
            }

            override fun onLost(network: Network) {
                callBack.invoke(false)
            }
        }
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                context.getSystemService(ConnectivityManager::class.java)
                    .registerDefaultNetworkCallback(networkCallback)
        }
    }
}