package thisaislan.movies.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import thisaislan.movies.constants.PagingSourcesConts.STARTING_PAGE_INDEX
import thisaislan.movies.network.models.Movie
import thisaislan.movies.network.services.interfaces.ITmdbService
import java.io.IOException
import javax.inject.Inject

class PopularMoviesPagingSource @Inject constructor(private val tmdbService: ITmdbService) :
    PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = tmdbService.fetchPopularMovies(page)
            val results = response.results

            LoadResult.Page(
                data = results,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (results.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(throwable = exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(throwable = exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition = anchorPosition)?.prevKey?.plus(other = 1)
                ?: state.closestPageToPosition(anchorPosition = anchorPosition)?.nextKey?.minus(other = 1)
        }
    }
}
