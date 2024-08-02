package thisaislan.movies.extensions

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

enum class MovieCoverAnimationState { PLACING, PLACED }

data class MovieCoverAnimationArgs(
    val fromScale: Float,
    val toScale: Float,
    val fromAlpha: Float,
    val toAlpha: Float
)

@Composable
fun Modifier.getMovieItemModifier(transition: Transition<MovieCoverAnimationState>): Modifier {
    val movieCoverAnimationArgs = MovieCoverAnimationArgs(
        fromScale = 2f,
        toScale = 1f,
        fromAlpha = 0f,
        toAlpha = 1f
    )

    val animation = tween<Float>(
        durationMillis = 300,
        delayMillis = 200,
        easing = LinearOutSlowInEasing
    )

    val scale = getMovieItemScaleAnimation(
        movieCoverAnimationArgs = movieCoverAnimationArgs,
        animation = animation,
        transition = transition
    )

    val alpha = getMovieItemAlphaAnimation(
        movieCoverAnimationArgs = movieCoverAnimationArgs,
        animation = animation,
        transition = transition
    )

    return this then Modifier.graphicsLayer(
        alpha = alpha,
        scaleX = scale,
        scaleY = scale
    )
}

@Composable
private fun getMovieItemScaleAnimation(
    movieCoverAnimationArgs: MovieCoverAnimationArgs,
    animation: FiniteAnimationSpec<Float>,
    transition: Transition<MovieCoverAnimationState>
): Float {
    val scale by transition.animateFloat(
        transitionSpec = { animation },
        label = "Movie cover scale animation"
    ) { state: MovieCoverAnimationState ->
        when (state) {
            MovieCoverAnimationState.PLACING -> movieCoverAnimationArgs.fromScale
            MovieCoverAnimationState.PLACED -> movieCoverAnimationArgs.toScale
        }
    }

    return scale
}

@Composable
private fun getMovieItemAlphaAnimation(
    movieCoverAnimationArgs: MovieCoverAnimationArgs,
    animation: FiniteAnimationSpec<Float>,
    transition: Transition<MovieCoverAnimationState>
): Float {
    val alpha by transition.animateFloat(
        transitionSpec = { animation },
        label = "Movie cover alpha animation"
    ) { state ->
        when (state) {
            MovieCoverAnimationState.PLACING -> movieCoverAnimationArgs.fromAlpha
            MovieCoverAnimationState.PLACED -> movieCoverAnimationArgs.toAlpha
        }
    }

    return alpha
}