package com.zykrave.anirumy.core.model.character

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.network.CharacterDetailsQuery
import com.zykrave.anirumy.core.resources.R

@Composable
fun CharacterDetailsQuery.Character.genderLocalized() = when (gender) {
    "Male" -> stringResource(R.string.male)
    "Female" -> stringResource(R.string.female)
    "Non-binary" -> stringResource(R.string.non_binary)
    else -> gender
}