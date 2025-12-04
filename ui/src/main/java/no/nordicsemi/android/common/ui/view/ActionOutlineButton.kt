@file:Suppress("unused")

package no.nordicsemi.android.common.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
 * Common outlined button with an icon, that can change to a progress indicator.
 *
 * @param modifier     Modifier to be used for the button.
 * @param text         Text to be shown on the button.
 * @param textColor    Color of the text.
 * @param icon         A painter to be shown in the button icon.
 * @param iconTint     Color of the icon, defaults to the current content color.
 * @param isInProgress A flag that will indicate if the action is in progress and
 *                     this will show a progress indicator in front of the button text instead
 *                     of the icon.
 * @param onClick      Action to be performed on button click.
 * @param enabled      Flag to enable or disable the button.
 * @param border       Border to be used for the button.
 */
@Composable
fun ActionOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Unspecified,
    icon: Painter,
    iconTint: Color? = null,
    isInProgress: Boolean = false,
    onClick: () -> Unit,
    enabled: Boolean = true,
    border: BorderStroke? = ButtonDefaults.outlinedButtonBorder(enabled = enabled),
) {
    OutlinedButton(
        modifier = modifier.defaultMinSize(minWidth = 120.dp),
        border = border,
        enabled = enabled && !isInProgress,
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        onClick = onClick,
    ) {
        when {
            isInProgress -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(size = 24.dp),
                    strokeWidth = 2.dp,
                    color = iconTint ?: ProgressIndicatorDefaults.circularColor,
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
        Text(
            modifier = modifier.padding(start = 8.dp),
            text = text,
            color = textColor,
        )
    }
}

/**
 * Common outlined button with an icon, that can change to a progress indicator.
 *
 * @param modifier     Modifier to be used for the button.
 * @param text         Text to be shown on the button.
 * @param textColor    Color of the text.
 * @param icon         A ImageVector to be shown in the button icon.
 * @param iconTint     Color of the icon, defaults to the current content color.
 * @param isInProgress A flag that will indicate if the action is in progress and
 *                     this will show a progress indicator in front of the button text instead
 *                     of the icon.
 * @param onClick      Action to be performed on button click.
 * @param enabled      Flag to enable or disable the button.
 * @param border       Border to be used for the button.
 */
@Composable
fun ActionOutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Unspecified,
    icon: ImageVector,
    iconTint: Color? = null,
    isInProgress: Boolean = false,
    onClick: () -> Unit,
    enabled: Boolean = true,
    border: BorderStroke? = ButtonDefaults.outlinedButtonBorder(enabled = enabled),
) {
    ActionOutlinedButton(
        modifier = modifier,
        text = text,
        textColor = textColor,
        icon = rememberVectorPainter(image = icon),
        iconTint = iconTint,
        isInProgress = isInProgress,
        onClick = onClick,
        enabled = enabled,
        border = border,
    )
}