package thisaislan.movies.network.services.interfaces

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import thisaislan.movies.network.models.Movie
import thisaislan.movies.network.models.MovieDetails
import thisaislan.movies.network.models.Page

interface ITmdbService {

    @GET("movie/popular")
    suspend fun fetchPopularMovies(@Query("page") page: Int): Page<Movie>

    @GET("movie/{id}?append_to_response=similar")
    suspend fun fetchMovieDetails(@Path("id") movieId: Int): MovieDetails
}
