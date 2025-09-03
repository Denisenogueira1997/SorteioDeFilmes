package com.example.appparasorteio.view.Componentes

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appparasorteio.R


@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    onInicioClick: () -> Unit,
    onMeusFilmesClick: () -> Unit,
    onFilmesassistidosClick: () -> Unit,
    selectedIndex: Int = -1
) {
    NavigationBar(
        modifier = modifier,
        tonalElevation = 8.dp,
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.inicio),
                    contentDescription = "Início"
                )
            },
            label = { Text("Início") },
            selected = selectedIndex == 0,
            onClick = onInicioClick,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onSecondary,
                unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                selectedTextColor = MaterialTheme.colorScheme.secondary,
                unselectedTextColor = MaterialTheme.colorScheme.secondary
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.meusfilmes),
                    contentDescription = "Meus Filmes"
                )
            },
            label = { Text("Meus Filmes") },
            selected = selectedIndex == 1,
            onClick = onMeusFilmesClick,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onSurface,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                selectedTextColor = MaterialTheme.colorScheme.onSurface,
                unselectedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.filmesassistidos),
                    contentDescription = "Filmes assistidos"
                )
            },
            label = { Text("Filmes assistidos") },
            selected = selectedIndex == 2,
            onClick = onFilmesassistidosClick,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.onSurface,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                selectedTextColor = MaterialTheme.colorScheme.onSurface,
                unselectedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}







