package com.unidagontor.franctionapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.unidagontor.franctionapp.viewmodel.MateriViewModel
import com.unidagontor.franctionapp.viewmodel.MateriViewModelFactory
import com.unidagontor.franctionapp.AppNavHost
import com.unidagontor.franctionapp.CekKoneksiWrapper
import com.unidagontor.franctionapp.ui.theme.FrancComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FrancComposeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val context = LocalContext.current
                    val navController = rememberNavController()

                    val materiViewModel: MateriViewModel = viewModel(
                        factory = MateriViewModelFactory(context.applicationContext as Application)
                    )
                    CekKoneksiWrapper {
                        AppNavHost(
                            navController = navController,
                            materiViewModel = materiViewModel
                        )
                    }
                }
            }
        }

    }
}