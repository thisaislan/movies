package thisaislan.movies.constants

import androidx.paging.PagingConfig

object MediaRepositoryConts {

    private const val PAGE_SIZE = 20

    val defaultPagingConfig = PagingConfig(
        pageSize = PAGE_SIZE,
        enablePlaceholders = false
    )
}
