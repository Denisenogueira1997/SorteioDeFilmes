package com.example.appparasorteio

import CustomColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appparasorteio.view.Componentes.MainScreenWithBottomNav
import com.example.appparasorteio.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {

            CustomColor {

                val movieViewModel: MovieViewModel = hiltViewModel()
                MainScreenWithBottomNav(movieViewModel)
            }
        }
    }
}




