package thisaislan.movies.network.models

import com.squareup.moshi.Json
import thisaislan.movies.network.models.interfaces.IMovie

data class Movie(
    @Json(name = "id") override val id: Int,
    @Json(name = "poster_path") override val posterPath: String?,
    @Json(name = "backdrop_path") override val backdropPath: String?,
    @Json(name = "overview") override val overview: String,
    @Json(name = "vote_average") override val voteAverage: Double,

    @Json(name = "title") val title: String,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name = "genre_ids") override val genreIds: List<Int>,
) : IMovie
