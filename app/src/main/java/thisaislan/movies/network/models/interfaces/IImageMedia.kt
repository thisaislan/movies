package thisaislan.movies.network.models.interfaces

import thisaislan.movies.constants.MediaConts.IMAGE_BASE_URL

interface IImageMedia {
    enum class ImageSize(val value: String){
        NORMAL("w500"),
        ORIGINAL("original")
    }

    val posterPath: String?
    val backdropPath: String?

    fun getPosterUrl(size: ImageSize = ImageSize.NORMAL): String {
        return "${IMAGE_BASE_URL}${size.value}${posterPath}"
    }

    fun getBackdropPath(size: ImageSize = ImageSize.NORMAL): String {
        return "${IMAGE_BASE_URL}${size.value}${backdropPath}"
    }
}
