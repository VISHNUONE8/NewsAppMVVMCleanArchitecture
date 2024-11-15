package com.andrayudu.newsappcomposemvvmtej.presentation.common.networkhelper

class TestNetworkHelper : NetworkHelper {
    override fun isNetworkConnected(): Boolean {
        return true
    }
}