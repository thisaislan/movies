package thisaislan.movies.repositories.interfaces

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import thisaislan.movies.network.models.Movie

interface IMovieRepository {
    val popularMoviesFlow: Flow<PagingData<Movie>>
}