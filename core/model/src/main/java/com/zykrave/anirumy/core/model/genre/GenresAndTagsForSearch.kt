package com.zykrave.anirumy.core.model.genre

data class GenresAndTagsForSearch(
    val genreIn: Set<String> = emptySet(),
    val genreNot: Set<String> = emptySet(),
    val tagIn: Set<String> = emptySet(),
    val tagNot: Set<String> = emptySet(),
    val minimumTagPercentage: Int = 18,
)
