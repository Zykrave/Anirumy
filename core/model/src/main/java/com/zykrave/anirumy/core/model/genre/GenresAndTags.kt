package com.zykrave.anirumy.core.model.genre

data class GenresAndTags(
    val genres: List<SelectableGenre>,
    val tags: List<SelectableGenre>,
)
