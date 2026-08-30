package com.zykrave.anirumy.feature.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as lazyGridItems
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.composables.DefaultScaffoldWithSmallTopAppBar
import com.zykrave.anirumy.core.ui.composables.common.FilterSelectionChip
import com.zykrave.anirumy.feature.gallery.data.repository.GalleryImage
import com.zykrave.anirumy.feature.gallery.data.repository.GallerySource
import org.koin.compose.viewmodel.koinViewModel

private val gallerySources = GallerySource.entries
private val placeholderCategories = listOf("waifu", "neko", "kitsune", "husbando")

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun GalleryScreen() {
    val viewModel: GalleryViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )

    DefaultScaffoldWithSmallTopAppBar(
        title = stringResource(R.string.gallery),
        scrollBehavior = topAppBarScrollBehavior,
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Column(modifier = Modifier.fillMaxSize()) {
                val selectedSourceIndex = gallerySources.indexOf(uiState.source).coerceAtLeast(0)
                PrimaryTabRow(selectedTabIndex = selectedSourceIndex) {
                    gallerySources.forEach { source ->
                        Tab(
                            selected = uiState.source == source,
                            onClick = { viewModel.onSourceSelected(source) },
                            text = { Text(text = source.name) }
                        )
                    }
                }

                LazyRow(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(placeholderCategories) { category ->
                        FilterSelectionChip(
                            selected = uiState.category == category,
                            text = category,
                            onClick = { viewModel.onCategorySelected(category) }
                        )
                    }
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    when {
                        uiState.isLoading -> {
                            LoadingIndicator(
                                modifier = Modifier
                                    .size(48.dp)
                                    .align(Alignment.Center)
                            )
                        }
                        uiState.images.isEmpty() -> {
                            Text(
                                text = if (uiState.source == GallerySource.COMING_SOON) {
                                    "Coming soon... the developer is lazy \uD83D\uDE05"
                                } else {
                                    "No images yet"
                                },
                                modifier = Modifier.align(Alignment.Center),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        else -> {
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                lazyGridItems(uiState.images) { image ->
                                    GalleryImageCard(image)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GalleryImageCard(image: GalleryImage) {
    AsyncImage(
        model = image.url,
        contentDescription = image.attribution,
        contentScale = ContentScale.Crop,
        placeholder = ColorPainter(MaterialTheme.colorScheme.outline),
        error = ColorPainter(MaterialTheme.colorScheme.outline),
        modifier = Modifier
            .aspectRatio(0.75f)
            .clip(RoundedCornerShape(8.dp))
    )
}
