package thisaislan.movies.network.models

import com.squareup.moshi.Json
import thisaislan.movies.network.models.interfaces.IMovieDetails

data class MovieDetails(
    @Json(name = "id") override val id: Int,
    @Json(name = "title") override val title: String,
    @Json(name = "backdrop_path") override val backdropPath: String?,
    @Json(name = "poster_path") override val posterPath: String?,
    @Json(name = "overview") override val overview: String,
    @Json(name = "release_date") override val releaseDate: String?,
    @Json(name = "vote_average") override val voteAverage: Double,
    @Json(name = "runtime") val runtime: Int?,
    @Json(name = "similar") val similar: Page<Movie>
) : IMovieDetails