package com.example.appparasorteio.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.appparasorteio.network.Movie
import com.example.appparasorteio.view.Componentes.FavoriteButton

@Composable
fun MovieItem(
    movie: Movie, isSaved: Boolean, onAddClick: (Movie) -> Unit, onDeleteClick: (Movie) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                val posterUrl = movie.poster_path?.let { "https://image.tmdb.org/t/p/w200$it" }
                if (posterUrl != null) {
                    Image(
                        painter = rememberAsyncImagePainter(posterUrl),
                        contentDescription = movie.title,
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))

                FavoriteButton(
                    isSaved = isSaved, onToggle = {
                        if (isSaved) onDeleteClick(movie) else onAddClick(movie)
                    })
            }


            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movie.release_date?.take(4) ?: "?",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = if (movie.poster_path != null) 96.dp else 0.dp)

            )
        }
    }
}
