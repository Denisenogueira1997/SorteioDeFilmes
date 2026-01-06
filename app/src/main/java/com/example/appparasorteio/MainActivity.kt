package com.example.appparasorteio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appparasorteio.ui.theme.AppParaSorteioTheme
import com.example.appparasorteio.ui.theme.system.SetupSystemBars
import com.example.appparasorteio.view.Componentes.MainScreenWithBottomNav
import com.example.appparasorteio.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {

            AppParaSorteioTheme(darkTheme = false) {
                SetupSystemBars()

                val movieViewModel: MovieViewModel = hiltViewModel()
                MainScreenWithBottomNav(movieViewModel)
            }
        }
    }
}




