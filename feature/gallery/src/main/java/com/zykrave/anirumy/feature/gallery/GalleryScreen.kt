package com.zykrave.anirumy.feature.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.composables.DefaultScaffoldWithSmallTopAppBar
import com.zykrave.anirumy.core.ui.composables.common.FilterSelectionChip

private enum class GallerySource(val label: String) {
    NEKOS_BEST("NekosBest"),
    WAIFU_IM("Waifu.im"),
    WAIFU_PICS("Waifu.pics"),
}

// Placeholder categories per source — static only, will differ per
// source once wired to real APIs in a later step.
private val placeholderCategories = listOf("All", "Neko", "Waifu", "Hug", "Smile")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen() {
    val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )
    var selectedSourceIndex by remember { mutableIntStateOf(0) }
    var selectedCategory by remember { mutableStateOf(placeholderCategories.first()) }

    DefaultScaffoldWithSmallTopAppBar(
        title = stringResource(R.string.gallery),
        scrollBehavior = topAppBarScrollBehavior,
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                PrimaryTabRow(selectedTabIndex = selectedSourceIndex) {
                    GallerySource.entries.forEachIndexed { index, source ->
                        Tab(
                            selected = selectedSourceIndex == index,
                            onClick = { selectedSourceIndex = index },
                            text = { Text(text = source.label) }
                        )
                    }
                }

                LazyRow(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(placeholderCategories) { category ->
                        FilterSelectionChip(
                            selected = selectedCategory == category,
                            text = category,
                            onClick = { selectedCategory = category }
                        )
                    }
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Empty for now — no data wiring yet.
                        // Real image cards get added in a later step.
                    }
                    Text(
                        text = "No images yet",
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
