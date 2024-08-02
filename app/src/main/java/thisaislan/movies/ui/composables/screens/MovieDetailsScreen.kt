package thisaislan.movies.ui.composables.screens

import android.content.res.Configuration
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.rememberTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import thisaislan.movies.R
import thisaislan.movies.events.MovieDetailsScreenEvent
import thisaislan.movies.extensions.MovieCoverAnimationState
import thisaislan.movies.extensions.getMovieItemModifier
import thisaislan.movies.states.MovieDetailsScreenStates
import thisaislan.movies.ui.composables.library.CloseButton

data class MovieDetailsData(
    val urlBackdrop: String,
    val contentDescription: String,
    val title: String,
    val description: String
)

@Composable
fun MovieDetailsScreen(
    movieDetailsScreenStates: MovieDetailsScreenStates,
    onEvent: (MovieDetailsScreenEvent) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues: PaddingValues ->
        var orientation by remember { mutableIntStateOf(Configuration.ORIENTATION_PORTRAIT) }
        val configuration = LocalConfiguration.current

        LaunchedEffect(configuration) {
            snapshotFlow { configuration.orientation }
                .collect { orientation = it }
        }

        when (movieDetailsScreenStates) {
            is MovieDetailsScreenStates.LoadingState -> DetailsScreenLoadingState(
                paddingValues = paddingValues,
                orientation = orientation
            )
            is MovieDetailsScreenStates.FinishedLoadingState -> DetailsScreenFinishedLoadingState(
                movieDetailsData = movieDetailsScreenStates.movieDetailsData,
                paddingValues = paddingValues,
                orientation = orientation
            )
        }

        CloseButton(
            topPadding = paddingValues.calculateTopPadding(),
            contentDescription = R.string.close_button_content_description
        ) {
            onEvent.invoke(MovieDetailsScreenEvent.OnBackPressedEvent)
        }
    }
}

@Composable
private fun DetailsScreenLoadingState(
    paddingValues: PaddingValues,
    orientation: Int
) {
    // TODO("implement")
}

@Composable
private fun DetailsScreenFinishedLoadingState(
    movieDetailsData: MovieDetailsData,
    paddingValues: PaddingValues,
    orientation: Int
) {
    when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> LandscapeContentFinishedLoadingState(
            movieDetailsData = movieDetailsData,
            paddingValues = paddingValues
        )

        else -> PortraitContentFinishedLoadingState(movieDetailsData = movieDetailsData)
    }
}

@Composable
private fun PortraitContentFinishedLoadingState(movieDetailsData: MovieDetailsData) {
    Column {
        movieDetailsData.apply {
            MovieDetailsImage(
                urlBackdrop = urlBackdrop,
                contentDescription = contentDescription,
                modifier = Modifier.height(height = 400.dp)
            )

            MovieDetails(
                title = title,
                description = description
            )
        }
    }
}

@Composable
private fun LandscapeContentFinishedLoadingState(movieDetailsData: MovieDetailsData, paddingValues: PaddingValues) {
    movieDetailsData.apply {
        Row {
            MovieDetailsImage(
                urlBackdrop = urlBackdrop,
                contentDescription = contentDescription,
                Modifier.width(width = 360.dp)
            )

            Column(modifier = Modifier.padding(paddingValues = paddingValues)) {
                MovieDetails(
                    title = title,
                    description = description
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MovieDetailsImage(urlBackdrop: String, contentDescription: String, modifier: Modifier) {
    val transitionState = remember {
        MutableTransitionState(MovieCoverAnimationState.PLACING).apply {
            targetState = MovieCoverAnimationState.PLACED
        }
    }

    val transition = rememberTransition(transitionState, label = "Movie details list cover transition")

    GlideImage(
        model = urlBackdrop,
        contentScale = ContentScale.Crop,
        contentDescription = contentDescription,
        modifier = modifier.getMovieItemModifier(transition)
    )
}

@Composable
private fun MovieDetails(title: String, description: String) {
    MovieTitle(title = title)
    MovieDescription(description = description)
}

@Composable
private fun MovieTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(12.dp)
    )
}

@Composable
private fun MovieDescription(description: String) {
    val scroll = rememberScrollState(0)

    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 20.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .padding(vertical = 10.dp, horizontal = 8.dp)
            .verticalScroll(scroll)
    )
}
