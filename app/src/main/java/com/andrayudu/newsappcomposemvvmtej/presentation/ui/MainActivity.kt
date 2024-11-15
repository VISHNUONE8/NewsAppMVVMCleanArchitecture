package com.andrayudu.newsappcomposemvvmtej.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.andrayudu.newsappcomposemvvmtej.presentation.ui.screens.NewsNavigation
import com.andrayudu.newsappcomposemvvmtej.presentation.ui.theme.NewsAppComposeMVVMTejTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppComposeMVVMTejTheme {
                Surface(color = MaterialTheme.colorScheme.background){

                    NewsNavigation()

                }
            }
        }
    }
}


