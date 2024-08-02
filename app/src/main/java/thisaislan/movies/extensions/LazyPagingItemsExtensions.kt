package thisaislan.movies.extensions

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any> LazyPagingItems<T>.rememberLazyGridStateWithPosition(): LazyGridState {
    // More info: https://effbada.hashnode.dev/how-to-remember-the-scroll-position-of-lazycolumn-built-with-paging-3-cl8d0x2st01odwpnv2g2o0q57.
    return when (itemCount) {
        // Return a different LazyListState instance.
        0 -> remember(this) {
            LazyGridState(
                firstVisibleItemIndex = 0,
                firstVisibleItemScrollOffset = 0
            )
        }
        // Return rememberLazyGridState (normal case).
        else -> androidx.compose.foundation.lazy.grid.rememberLazyGridState()
    }
}