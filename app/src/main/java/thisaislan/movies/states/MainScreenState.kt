package thisaislan.movies.states

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import thisaislan.movies.network.models.Movie

sealed class MainScreenState {
    data class LoadingState(val popularMoviesFlow: Flow<PagingData<Movie>>) : MainScreenState()
}