package com.zykrave.anirumy.feature.settings.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zykrave.anirumy.core.resources.ColorUtils.hexCode
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.composables.common.CommonColorPickerDialog

@Composable
fun CustomColorPreference(
    color: Color?,
    onColorChanged: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    val hexString = remember(color) { "#" + color?.hexCode?.drop(2).orEmpty() }
    var colorValue by remember { mutableStateOf(color) }
    var openDialog by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1C1D2C))
            .clickable { openDialog = true }
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .background(
                    color = Color(0xFF2A2B3D),
                    shape = RoundedCornerShape(12.dp)
                )
        )

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.custom_color),
                color = MaterialTheme.colorScheme.onSurface
            )

            if (color != null) {
                Text(
                    text = hexString,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp
                )
            }
        }
    }

    if (openDialog) {
        CommonColorPickerDialog(
            title = stringResource(R.string.custom_color),
            initialColor = colorValue ?: MaterialTheme.colorScheme.primary,
            onDismissRequest = { openDialog = false },
            onColorSelected = { color ->
                colorValue = color
                onColorChanged(color)
            }
        )
    }
}