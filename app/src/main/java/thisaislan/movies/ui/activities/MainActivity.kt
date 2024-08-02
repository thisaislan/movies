package thisaislan.movies.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import thisaislan.movies.events.MainScreenEvent
import thisaislan.movies.ui.composables.library.SystemBarStyleColor
import thisaislan.movies.ui.composables.screens.MainScreen
import thisaislan.movies.ui.theme.MoviesTheme
import thisaislan.movies.viewmodels.MoviesViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                val moviesViewModel = hiltViewModel<MoviesViewModel>()

                SystemBarStyleColor()

                MainScreen(mainScreenState = moviesViewModel.mainScreenState.collectAsStateWithLifecycle().value) { mainScreenEvent: MainScreenEvent ->
                    moviesViewModel.onEvent(mainScreenEvent)
                }

                moviesViewModel.movieIdToGetDetail.collectAsStateWithLifecycle().value?.let { movieId: Int ->
                    callDetailsActivity(movieId)
                    moviesViewModel.resetMovieIdToGetDetail()
                }
            }
        }
    }

    private fun callDetailsActivity(movieId: Int) {
        val intent = Intent(this@MainActivity, MovieDetailsActivity::class.java)
        intent.putExtra(MovieDetailsActivity.MOVIE_ID_PARAM, movieId)

        startActivity(intent)
    }
}
