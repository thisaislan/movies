package thisaislan.movies.events

sealed class MovieDetailsScreenEvent {
    data object OnBackPressedEvent : MovieDetailsScreenEvent()
}