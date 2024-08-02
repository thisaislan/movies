package thisaislan.movies.network.models.interfaces

interface IMovie : IImageMedia{
    val id: Int
    val overview: String
    val voteAverage: Double
    val genreIds: List<Int>
}