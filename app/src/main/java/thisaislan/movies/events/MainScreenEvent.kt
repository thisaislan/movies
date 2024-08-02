package thisaislan.movies.events

sealed class  MainScreenEvent {
    data class OnMovieClicked(val movieId: Int) : MainScreenEvent()
}