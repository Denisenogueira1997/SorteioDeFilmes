import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val userBaseColor = Color(0xFF3C6090)

@Composable
fun CustomColor(content: @Composable () -> Unit) {
    val darkTheme = isSystemInDarkTheme()
    val colorScheme = if (darkTheme) darkColorScheme(
        primary = userBaseColor,
        onPrimary = Color(0xFFFFFFFF),
        secondary = Color(0xFF545F71),
        onSecondary = Color.White,
        background = Color(0xFF121212),
        onBackground = Color.White,
        surface = Color(0xFF1E1E1E),
        onSurface = Color(0xFF191C20),
        outlineVariant = Color(0xFF545F71),
        onSurfaceVariant = Color(0xFF43474E),
        secondaryContainer = Color(0xFFD8E3F8),
        surfaceContainerLow = Color(0xFFF3F3FA),
        surfaceContainer = Color(0xFFEDEDF4),
        onSecondaryContainer = Color(0xFF3D4758)
    ) else lightColorScheme(
        primary = userBaseColor,
        onPrimary = Color(0xFFFFFFFF),
        secondary = Color(0xFF545F71),
        onSecondary = Color.White,
        background = Color(0xFF121212),
        onBackground = Color.White,
        surface = Color(0xFF1E1E1E),
        onSurface = Color(0xFF191C20),
        outlineVariant = Color(0xFF545F71),
        onSurfaceVariant = Color(0xFF43474E),
        secondaryContainer = Color(0xFFD8E3F8),
        surfaceContainerLow = Color(0xFFF3F3FA),
        surfaceContainer = Color(0xFFEDEDF4),
        onSecondaryContainer = Color(0xFF3D4758)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
