package thisaislan.movies.ui.composables.library

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun SystemBarStyleColor() {
    isSystemInDarkTheme().let { isDarkMode ->
        val context = LocalContext.current as ComponentActivity

        DisposableEffect(key1 = isDarkMode) {
            val systemBarStyle = if (isDarkMode) {
                SystemBarStyle.dark(
                    scrim = android.graphics.Color.BLACK
                )
            } else {
                SystemBarStyle.light(
                    scrim = android.graphics.Color.WHITE,
                    darkScrim = android.graphics.Color.BLACK
                )
            }

            context.enableEdgeToEdge(
                statusBarStyle = systemBarStyle,
                navigationBarStyle = systemBarStyle
            )

            onDispose { }
        }
    }
}