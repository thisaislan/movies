package thisaislan.movies.repositories

import androidx.paging.Pager
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import thisaislan.movies.network.models.Movie
import thisaislan.movies.repositories.interfaces.IMovieRepository
import javax.inject.Inject

class MovieRepository @Inject constructor(
    popularMoviesPager: Pager<Int, Movie>) :
    IMovieRepository {

    override val popularMoviesFlow: Flow<PagingData<Movie>> = popularMoviesPager.flow
}
