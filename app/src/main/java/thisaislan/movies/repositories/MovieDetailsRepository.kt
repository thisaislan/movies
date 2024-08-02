package thisaislan.movies.repositories

import thisaislan.movies.network.models.MovieDetails
import thisaislan.movies.network.services.interfaces.ITmdbService
import thisaislan.movies.repositories.interfaces.IMovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(private val tmdbService: ITmdbService): IMovieDetailsRepository{

    override suspend fun getDetails(movieId: Int) : MovieDetails {
        return tmdbService.fetchMovieDetails(movieId = movieId)
    }
}