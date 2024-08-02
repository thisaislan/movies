package thisaislan.movies.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import thisaislan.movies.events.MovieDetailsScreenEvent
import thisaislan.movies.repositories.interfaces.IMovieDetailsRepository
import thisaislan.movies.states.MovieDetailsScreenStates
import thisaislan.movies.ui.composables.screens.MovieDetailsData
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val detailsRepository: IMovieDetailsRepository) :
    ViewModel() {

    // In Android, it’s generally not recommended to directly close an activity from a ViewModel
    // It ts possible generalize that action in the future
    // For now let's use this here : )
    private val internalCloseActivity = MutableStateFlow(value = false)
    val closeActivityStateFlow: StateFlow<Boolean> = internalCloseActivity

    private val internalDetailsScreenState =
        MutableStateFlow<MovieDetailsScreenStates>(value = MovieDetailsScreenStates.LoadingState)
    val detailsScreenStateFlow: StateFlow<MovieDetailsScreenStates> = internalDetailsScreenState

    fun onEvent(movieDetailsScreenEvent: MovieDetailsScreenEvent) {
        when (movieDetailsScreenEvent) {
            is MovieDetailsScreenEvent.OnBackPressedEvent -> {
                internalCloseActivity.value = true
            }
        }
    }

    fun getDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieDetails = detailsRepository.getDetails(movieId = movieId)

            internalDetailsScreenState.value =
                MovieDetailsScreenStates.FinishedLoadingState(
                    MovieDetailsData(
                        urlBackdrop = movieDetails.getBackdropPath(),
                        contentDescription = movieDetails.title,
                        title = movieDetails.title,
                        description = movieDetails.overview
                    )
                )
        }
    }
}
