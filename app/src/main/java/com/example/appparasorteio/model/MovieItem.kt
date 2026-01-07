package com.example.appparasorteio.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
            .width(380.dp)
            .height(140.dp)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        shape = RoundedCornerShape(12.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val posterUrl = movie.poster_path?.let { "https://image.tmdb.org/t/p/w200$it" }
            if (posterUrl != null) {
                Image(
                    painter = rememberAsyncImagePainter(posterUrl),
                    contentDescription = movie.title,
                    modifier = Modifier
                        .width(69.68.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()

            ) {
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 2
                )


                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = movie.release_date?.take(4) ?: "?",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            FavoriteButton(
                isSaved = isSaved,
                onToggle = {
                    if (isSaved) onDeleteClick(movie) else onAddClick(movie)
                }
            )
        }
    }
}