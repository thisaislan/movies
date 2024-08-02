package thisaislan.movies.network.models.interfaces

interface IMovieDetails : IImageMedia {
    val id: Int
    val title: String
    val overview: String
    val releaseDate: String?
    val voteAverage: Double
}