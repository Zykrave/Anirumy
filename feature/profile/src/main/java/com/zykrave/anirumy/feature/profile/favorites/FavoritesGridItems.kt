package com.zykrave.anirumy.feature.profile.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zykrave.anirumy.core.model.FavoritesType
import com.zykrave.anirumy.core.network.UserFavoritesAnimeQuery
import com.zykrave.anirumy.core.network.UserFavoritesCharacterQuery
import com.zykrave.anirumy.core.network.UserFavoritesMangaQuery
import com.zykrave.anirumy.core.network.UserFavoritesStaffQuery
import com.zykrave.anirumy.core.network.UserFavoritesStudioQuery
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.composables.defaultPlaceholder
import com.zykrave.anirumy.core.ui.composables.media.MediaItemVertical
import com.zykrave.anirumy.core.ui.composables.media.MediaItemVerticalPlaceholder
import com.zykrave.anirumy.core.ui.composables.person.PersonItemVertical
import com.zykrave.anirumy.core.ui.composables.person.PersonItemVerticalPlaceholder
import sh.calvin.reorderable.ReorderableItem
import sh.calvin.reorderable.ReorderableLazyGridState

internal fun LazyGridScope.favoritesItems(
    type: FavoritesType,
    anime: List<UserFavoritesAnimeQuery.Node>,
    manga: List<UserFavoritesMangaQuery.Node>,
    characters: List<UserFavoritesCharacterQuery.Node>,
    staff: List<UserFavoritesStaffQuery.Node>,
    studios: List<UserFavoritesStudioQuery.Node>,
    isLoading: Boolean,
    blurAdult: Boolean,
    reorderableState: ReorderableLazyGridState? = null,
    onMediaClick: (Int) -> Unit = {},
    onCharacterClick: (Int) -> Unit = {},
    onStaffClick: (Int) -> Unit = {},
    onStudioClick: (Int) -> Unit = {}
) {
    when (type) {
        FavoritesType.ANIME -> {
            items(
                items = anime,
                key = { it.id },
                contentType = { it }
            ) { item ->
                if (reorderableState != null) {
                    ReorderableItem(reorderableState, key = item.id) {
                        MediaItemVertical(
                            title = item.title?.userPreferred.orEmpty(),
                            imageUrl = item.coverImage?.large,
                            blurImage = blurAdult && item.isAdult == true,
                            modifier = Modifier
                                .wrapContentWidth()
                                .longPressDraggableHandle(),
                            onClick = { onMediaClick(item.id) }
                        )
                    }
                } else {
                    MediaItemVertical(
                        title = item.title?.userPreferred.orEmpty(),
                        imageUrl = item.coverImage?.large,
                        blurImage = blurAdult && item.isAdult == true,
                        modifier = Modifier.wrapContentWidth(),
                        onClick = { onMediaClick(item.id) }
                    )
                }
            }
            if (isLoading) {
                items(14) {
                    MediaItemVerticalPlaceholder()
                }
            }
        }

        FavoritesType.MANGA -> {
            items(
                items = manga,
                key = { it.id },
                contentType = { it }
            ) { item ->
                if (reorderableState != null) {
                    ReorderableItem(reorderableState, key = item.id) {
                        MediaItemVertical(
                            title = item.title?.userPreferred.orEmpty(),
                            imageUrl = item.coverImage?.large,
                            blurImage = blurAdult && item.isAdult == true,
                            modifier = Modifier
                                .wrapContentWidth()
                                .longPressDraggableHandle(),
                            onClick = { onMediaClick(item.id) }
                        )
                    }
                } else {
                    MediaItemVertical(
                        title = item.title?.userPreferred.orEmpty(),
                        imageUrl = item.coverImage?.large,
                        blurImage = blurAdult && item.isAdult == true,
                        modifier = Modifier.wrapContentWidth(),
                        onClick = { onMediaClick(item.id) }
                    )
                }
            }
            if (isLoading) {
                items(14) {
                    MediaItemVerticalPlaceholder()
                }
            }
        }

        FavoritesType.CHARACTERS -> {
            items(
                items = characters,
                key = { it.id },
                contentType = { it }
            ) { item ->
                if (reorderableState != null) {
                    ReorderableItem(reorderableState, key = item.id) {
                        PersonItemVertical(
                            title = item.name?.userPreferred.orEmpty(),
                            imageUrl = item.image?.large,
                            modifier = Modifier.longPressDraggableHandle(),
                            onClick = { onCharacterClick(item.id) }
                        )
                    }
                } else {
                    PersonItemVertical(
                        title = item.name?.userPreferred.orEmpty(),
                        imageUrl = item.image?.large,
                        onClick = { onCharacterClick(item.id) }
                    )
                }
            }
            if (isLoading) {
                items(14) {
                    PersonItemVerticalPlaceholder()
                }
            }
        }

        FavoritesType.STAFF -> {
            items(
                items = staff,
                key = { it.id },
                contentType = { it }
            ) { item ->
                if (reorderableState != null) {
                    ReorderableItem(reorderableState, key = item.id) {
                        PersonItemVertical(
                            title = item.name?.userPreferred.orEmpty(),
                            imageUrl = item.image?.large,
                            modifier = Modifier.longPressDraggableHandle(),
                            onClick = { onStaffClick(item.id) }
                        )
                    }
                } else {
                    PersonItemVertical(
                        title = item.name?.userPreferred.orEmpty(),
                        imageUrl = item.image?.large,
                        onClick = { onStaffClick(item.id) }
                    )
                }
            }
            if (isLoading) {
                items(14) {
                    PersonItemVerticalPlaceholder()
                }
            }
        }

        FavoritesType.STUDIOS -> {
            items(
                items = studios,
                key = { it.id },
                contentType = { it }
            ) { item ->
                if (reorderableState != null) {
                    ReorderableItem(reorderableState, key = item.id) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .shadow(
                                    elevation = 6.dp,
                                    shape = RoundedCornerShape(14.dp),
                                    ambientColor = Color.Black.copy(alpha = 0.4f),
                                    spotColor = Color.Black.copy(alpha = 0.4f)
                                )
                                .clip(RoundedCornerShape(14.dp))
                                .background(Color(0xFF1C1D2C))
                                .longPressDraggableHandle()
                                .clickable { onStudioClick(item.id) }
                        ) {
                            // Glass Sheen Overlay
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .background(
                                        Brush.verticalGradient(
                                            listOf(
                                                Color.White.copy(alpha = 0.05f),
                                                Color.White.copy(alpha = 0f)
                                            )
                                        )
                                    )
                            )
                            Text(
                                text = item.name,
                                modifier = Modifier.padding(
                                    horizontal = 16.dp,
                                    vertical = 8.dp
                                ),
                                fontSize = 16.sp,
                                lineHeight = 18.sp
                            )
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .shadow(
                                elevation = 6.dp,
                                shape = RoundedCornerShape(14.dp),
                                ambientColor = Color.Black.copy(alpha = 0.4f),
                                spotColor = Color.Black.copy(alpha = 0.4f)
                            )
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF1C1D2C))
                            .clickable { onStudioClick(item.id) }
                    ) {
                        // Glass Sheen Overlay
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            Color.White.copy(alpha = 0.05f),
                                            Color.White.copy(alpha = 0f)
                                        )
                                    )
                                )
                        )
                        Text(
                            text = item.name,
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            ),
                            fontSize = 16.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
            if (isLoading) {
                items(14) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .shadow(
                                elevation = 6.dp,
                                shape = RoundedCornerShape(14.dp),
                                ambientColor = Color.Black.copy(alpha = 0.4f),
                                spotColor = Color.Black.copy(alpha = 0.4f)
                            )
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF1C1D2C))
                            .defaultPlaceholder(visible = true),
                    ) {
                        // Glass Sheen Overlay
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(
                                            Color.White.copy(alpha = 0.05f),
                                            Color.White.copy(alpha = 0f)
                                        )
                                    )
                                )
                        )
                        Text(
                            text = stringResource(R.string.loading),
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            ),
                            fontSize = 16.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }
}
