package com.example.aiva

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.example.aiva.ui.theme.AivaTheme

class MainActivity : ComponentActivity() {
    lateinit var viewmodel : ChatViewModel
    @RequiresApi(35)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this)[ChatViewModel::class.java]
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            AivaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   ChatPage(modifier = Modifier.padding(innerPadding) , viewmodel)
                }
            }
        }
    }
}

