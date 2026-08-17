package com.zykrave.anirumy.feature.explore.search.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.model.media.CountryOfOrigin
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.composables.chip.FilterChipWithMenu

@Composable
fun MediaSearchCountryChip(
    value: CountryOfOrigin?,
    onValueChanged: (CountryOfOrigin?) -> Unit,
) {
    FilterChipWithMenu(
        title = stringResource(R.string.country),
        values = CountryOfOrigin.entries,
        selectedValue = value,
        onValueSelected = onValueChanged,
        valueString = { it.localized() },
    )
}