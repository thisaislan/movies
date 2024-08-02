package thisaislan.movies.states

import thisaislan.movies.ui.composables.screens.MovieDetailsData

sealed class MovieDetailsScreenStates {
    data object LoadingState : MovieDetailsScreenStates()
    data class FinishedLoadingState(val movieDetailsData: MovieDetailsData) : MovieDetailsScreenStates()
}
