package thisaislan.movies.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import thisaislan.movies.events.MainScreenEvent
import thisaislan.movies.repositories.interfaces.IMovieRepository
import thisaislan.movies.states.MainScreenState
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val mediaRepository: IMovieRepository) :
    ViewModel() {

    private val popularMoviesFlow = mediaRepository.popularMoviesFlow.cachedIn(scope = viewModelScope)

    // In Android, a ViewModel should not directly handle UI-related tasks like starting an Activity.
    // It ts possible generalize that action in the future
    // For now let's use this here : )
    private val internalMovieIdToGetDetail = MutableStateFlow<Int?>(value = null)
    val movieIdToGetDetail: StateFlow<Int?> = internalMovieIdToGetDetail

    private val internalMainScreenState =
        MutableStateFlow<MainScreenState>(value = MainScreenState.LoadingState(popularMoviesFlow = popularMoviesFlow))
    val mainScreenState: StateFlow<MainScreenState> = internalMainScreenState

    fun onEvent(mainScreenEvent: MainScreenEvent) {
        when (mainScreenEvent) {
            is MainScreenEvent.OnMovieClicked -> {
                internalMovieIdToGetDetail.value = mainScreenEvent.movieId
            }
        }
    }

    fun resetMovieIdToGetDetail() {
        internalMovieIdToGetDetail.value = null
    }
}
