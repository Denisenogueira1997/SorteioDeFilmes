package com.example.appparasorteio.view.Componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appparasorteio.R

@Composable
fun FavoriteButton(
    isSaved: Boolean,
    onToggle: () -> Unit,
) {
    val backgroundColor = if (isSaved) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceContainerLow
    }

    val borderColor = MaterialTheme.colorScheme.primary

    val iconTint = if (isSaved) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.primary
    }

    Box(
        modifier = Modifier
            .size(40.dp)
            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(8.dp))
            .clickable { onToggle() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.meusfilmes),
            contentDescription = if (isSaved) "Remover dos favoritos" else "Adicionar aos favoritos",
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(iconTint)
        )
    }
}
