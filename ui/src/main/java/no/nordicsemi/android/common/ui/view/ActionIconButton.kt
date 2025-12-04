@file:Suppress("unused")

package no.nordicsemi.android.common.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

/**
 * Common outlined button with an icon.
 *
 * @param modifier     Modifier to be used for the button.
 * @param isInProgress Boolean flag that will indicate if the action is in progress and
 *                     this will show a progress indicator in front of the button text.
 * @param icon         ImageVector to be shown int he button icon.
 * @param iconTint     Color to be used as tint for the button icon.
 * @param onClick      Action to be performed on button click.
 * @param enabled      Flag to enable or disable the button.
 */
@Composable
fun ActionIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    iconTint: Color? = null,
    onClick: () -> Unit,
    isInProgress: Boolean = false,
    enabled: Boolean = true,
) {
    IconButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
    ) {
        when {
            isInProgress -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(size = 24.dp),
                    color = iconTint ?: ProgressIndicatorDefaults.circularColor
                )
            }
            else -> {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = iconTint ?: LocalContentColor.current,
                )
            }
        }
    }
}
/**
 * Common outlined button with an icon.
 *
 * @param modifier     Modifier to be used for the button.
 * @param isInProgress Boolean flag that will indicate if the action is in progress and
 *                     this will show a progress indicator in front of the button text.
 * @param icon         ImageVector to be shown int he button icon.
 * @param iconTint     Color to be used as tint for the button icon.
 * @param onClick      Action to be performed on button click.
 * @param enabled      Flag to enable or disable the button.
 */
@Composable
fun ActionIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconTint: Color? = null,
    onClick: () -> Unit,
    isInProgress: Boolean = false,
    enabled: Boolean = true,
) {
    ActionIconButton(
        modifier = modifier,
        icon = rememberVectorPainter(image = icon),
        iconTint = iconTint,
        isInProgress = isInProgress,
        onClick = onClick,
        enabled = enabled,
    )
}