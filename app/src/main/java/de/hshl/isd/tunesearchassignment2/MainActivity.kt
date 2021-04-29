package de.hshl.isd.tunesearchassignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent(viewModel = mainViewModel)
        }
    }
}

@Composable
fun MainContent(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavigationHost(navController,viewModel)
}