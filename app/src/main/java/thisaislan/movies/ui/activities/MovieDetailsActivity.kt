package thisaislan.movies.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import thisaislan.movies.ui.composables.screens.MovieDetailsScreen
import thisaislan.movies.ui.theme.MoviesTheme
import thisaislan.movies.viewmodels.MovieDetailsViewModel

@AndroidEntryPoint
class MovieDetailsActivity : ComponentActivity() {

    companion object {
        const val MOVIE_ID_PARAM = "movie_id_param"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MoviesTheme {
                val movieDetailsViewModel = hiltViewModel<MovieDetailsViewModel>()

                val shouldCloseActivity = movieDetailsViewModel.closeActivityStateFlow.collectAsStateWithLifecycle().value
                val detailsScreenState = movieDetailsViewModel.detailsScreenStateFlow.collectAsStateWithLifecycle().value

                MovieDetailsScreen(detailsScreenState) { detailsScreenEvent ->
                    movieDetailsViewModel.onEvent(detailsScreenEvent)
                }

                getMoviesData(movieDetailsViewModel)
                closeActivity(shouldCloseActivity)
            }
        }
    }

    private fun closeActivity(shouldCloseActivity: Boolean) {
        if (shouldCloseActivity) {
            finish()
        }
    }

    private fun getMoviesData(movieDetailsViewModel: MovieDetailsViewModel) {
        intent.getIntExtra(MOVIE_ID_PARAM, 0).let { movieId: Int ->
            if (movieId != 0) {
                movieDetailsViewModel.getDetails(movieId = movieId)
            }
        }
    }
}
