package com.example.appparasorteio.view.Componentes

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
                selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
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
                selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
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
                selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onSurface,
                unselectedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}







