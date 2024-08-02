package thisaislan.movies.ui.composables.library

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import thisaislan.movies.R

private val primaryColorNormalIndication: Indication
    @Composable
    get() = rememberRipple(
        bounded = false,
        radius = 25.dp,
        color = MaterialTheme.colorScheme.primary
    )

private val secondaryColorSmallIndication: Indication
    @Composable
    get() = rememberRipple(
        bounded = false,
        radius = 20.dp,
        color = MaterialTheme.colorScheme.secondary
    )

@Composable
fun ClickableTopBarImage(
    @DrawableRes painterResource: Int,
    @StringRes description: Int,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = painterResource),
        contentDescription = stringResource(description),
        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = primaryColorNormalIndication,
                onClick = onClick
            )
    )
}

@Composable
fun CloseButton(topPadding: Dp, @StringRes contentDescription: Int, onClick: () -> Unit) {
    Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary,
        shadowElevation = 10.dp,
        tonalElevation = 6.dp,
        modifier = Modifier
            .wrapContentSize(align = Alignment.Center)
            .padding(top = topPadding, start = 12.dp)
            .size(size = 40.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = secondaryColorSmallIndication,
                onClick = onClick
            )
    ) {
        Image(
            painter = painterResource(R.drawable.ic_close),
            contentDescription = stringResource(contentDescription),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 4.dp)
        )
    }
}