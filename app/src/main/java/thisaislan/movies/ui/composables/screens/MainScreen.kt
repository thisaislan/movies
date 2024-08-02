package thisaislan.movies.ui.composables.screens

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import thisaislan.movies.R
import thisaislan.movies.events.MainScreenEvent
import thisaislan.movies.extensions.MovieCoverAnimationState
import thisaislan.movies.extensions.getMovieItemModifier
import thisaislan.movies.extensions.rememberLazyGridStateWithPosition
import thisaislan.movies.network.models.Movie
import thisaislan.movies.states.MainScreenState
import thisaislan.movies.ui.composables.library.ClickableTopBarImage

private val TOP_BAR_HEIGHT = 56.dp

@Composable
fun MainScreen(mainScreenState: MainScreenState, onEvent: (MainScreenEvent) -> Unit) {
    Scaffold { paddingValues: PaddingValues ->
        when (mainScreenState) {
            is MainScreenState.LoadingState -> MainScreenLoadingState(
                lazyPagingItems = mainScreenState.popularMoviesFlow.collectAsLazyPagingItems(),
                paddingValues = paddingValues,
                onEvent = onEvent
            )
        }
    }
}

@Composable
fun MainScreenLoadingState(
    lazyPagingItems: LazyPagingItems<Movie>,
    paddingValues: PaddingValues,
    onEvent: (MainScreenEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        ScreenBody(lazyPagingItems = lazyPagingItems, onEvent = onEvent)
        ScreenTopBar()
    }
}

@Composable
private fun ScreenBody(lazyPagingItems: LazyPagingItems<Movie>, onEvent: (MainScreenEvent) -> Unit) {
    when (lazyPagingItems.loadState.refresh) {
        LoadState.Loading -> {
            // TODO("implement loading state")
        }

        is LoadState.Error -> {
            // TODO("implement error state")
        }

        else -> {
            // LoadState.NotLoading
            MoviesGrid(lazyPagingItems = lazyPagingItems, onEvent = onEvent)
        }
    }
}

@Composable
private fun ScreenTopBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .alpha(0.8f)
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxWidth()
            .height(TOP_BAR_HEIGHT)
    ) {
        TopBarAppIcon()
        TopBarIcons()
    }
}

@Composable
private fun TopBarAppIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = stringResource(R.string.app_name)
    )
}

@Composable
private fun TopBarIcons() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ClickableTopBarImage(
            painterResource = R.drawable.ic_search,
            description = R.string.icon_search_content_description,
            onClick = {}
        )

        ClickableTopBarImage(
            painterResource = R.drawable.ic_favorites,
            description = R.string.icon_favorites_content_description,
            onClick = {}
        )
    }
}

@Composable
private fun MoviesGrid(
    lazyPagingItems: LazyPagingItems<Movie>,
    onEvent: (MainScreenEvent) -> Unit
) {
    val lazyGridState = lazyPagingItems.rememberLazyGridStateWithPosition()

    val topPadding by animateDpAsState(
        targetValue = if (!lazyGridState.canScrollBackward) TOP_BAR_HEIGHT else 0.dp,
        animationSpec = tween(durationMillis = 300), label = "Grid top padding animation"
    )

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        state = lazyGridState,
        modifier = Modifier.padding(top = topPadding)
    ) {
        items(lazyPagingItems.itemCount) { index ->
            lazyPagingItems[index]?.let { movie ->
                MovieItem(movie = movie, onEvent = onEvent)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun MovieItem(movie: Movie, onEvent: (MainScreenEvent) -> Unit) {
    val transitionState = remember {
        MutableTransitionState(MovieCoverAnimationState.PLACING).apply {
            targetState = MovieCoverAnimationState.PLACED
        }
    }

    val transition = rememberTransition(transitionState, label = "Movie list cover transition")

    GlideImage(
        model = movie.getPosterUrl(),
        contentScale = ContentScale.Crop,
        contentDescription = movie.title,
        modifier = Modifier
            .getMovieItemModifier(transition)
            .clickable(onClick = {
                onEvent.invoke(MainScreenEvent.OnMovieClicked(movieId = movie.id))
            })
    )
}

