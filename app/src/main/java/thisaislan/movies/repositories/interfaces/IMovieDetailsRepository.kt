package thisaislan.movies.repositories.interfaces

import thisaislan.movies.network.models.MovieDetails

interface IMovieDetailsRepository {
    suspend fun getDetails(movieId: Int): MovieDetails
}