package com.zykrave.anirumy.feature.explore.discover.content

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zykrave.anirumy.core.base.UNKNOWN_CHAR
import com.zykrave.anirumy.core.network.AiringAnimesQuery
import com.zykrave.anirumy.core.network.AiringOnMyListQuery
import com.zykrave.anirumy.core.network.fragment.BasicMediaDetails
import com.zykrave.anirumy.core.network.fragment.BasicMediaListEntry
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.common.LocalBlurAdult
import com.zykrave.anirumy.core.ui.composables.list.DiscoverLazyRow
import com.zykrave.anirumy.core.ui.composables.list.HorizontalListHeader
import com.zykrave.anirumy.core.ui.composables.media.AiringAnimeHorizontalItem
import com.zykrave.anirumy.core.ui.composables.media.AiringAnimeHorizontalItemPlaceholder
import com.zykrave.anirumy.core.ui.composables.media.AiringHeroCard
import com.zykrave.anirumy.core.ui.composables.media.MEDIA_POSTER_SMALL_HEIGHT
import com.zykrave.anirumy.core.ui.utils.ComposeDateUtils.secondsToLegibleText
import kotlinx.coroutines.delay

@Composable
fun AiringContent(
    airingOnMyList: Boolean?,
    airingAnime: List<AiringAnimesQuery.AiringSchedule>,
    airingAnimeOnMyList: List<AiringOnMyListQuery.Medium>,
    isLoading: Boolean,
    onLongClickItem: (BasicMediaDetails, BasicMediaListEntry?) -> Unit,
    navigateToCalendar: () -> Unit,
    navigateToMediaDetails: (mediaId: Int) -> Unit
) {
    val blurAdult = LocalBlurAdult.current
    HorizontalListHeader(
        text = stringResource(R.string.airing_soon),
        onClick = navigateToCalendar
    )
    when (airingOnMyList) {
        true -> {
            DiscoverLazyRow(
                minHeight = MEDIA_POSTER_SMALL_HEIGHT.dp
            ) {
                items(
                    items = airingAnimeOnMyList,
                    contentType = { it }
                ) { item ->
                    AiringAnimeHorizontalItem(
                        title = item.basicMediaDetails.title?.userPreferred.orEmpty(),
                        subtitle = stringResource(
                            R.string.airing_in,
                            item.nextAiringEpisode?.timeUntilAiring?.toLong()
                                ?.secondsToLegibleText() ?: UNKNOWN_CHAR
                        ),
                        blurImage = blurAdult && item.basicMediaDetails.isAdult == true,
                        imageUrl = item.coverImage?.large,
                        score = item.meanScore,
                        status = item.mediaListEntry?.basicMediaListEntry?.status,
                        onClick = {
                            navigateToMediaDetails(item.id)
                        },
                        onLongClick = {
                            onLongClickItem(
                                item.basicMediaDetails,
                                item.mediaListEntry?.basicMediaListEntry
                            )
                        }
                    )
                }
                if (isLoading) {
                    items(10) {
                        AiringAnimeHorizontalItemPlaceholder()
                    }
                }
            }//:LazyRow
        }

        false -> {
            if (airingAnime.isNotEmpty()) {
                val pagerState = rememberPagerState(pageCount = { airingAnime.size })

                LaunchedEffect(pagerState, airingAnime.size) {
                    while (true) {
                        delay(4500)
                        val nextPage = (pagerState.currentPage + 1) % airingAnime.size
                        pagerState.animateScrollToPage(nextPage)
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) { page ->
                    val item = airingAnime[page]
                    AiringHeroCard(
                        title = item.media?.basicMediaDetails?.title?.userPreferred.orEmpty(),
                        subtitle = stringResource(
                            R.string.airing_in,
                            item.timeUntilAiring.toLong().secondsToLegibleText()
                        ),
                        imageUrl = item.media?.coverImage?.large,
                        blurImage = false,
                        score = item.media?.meanScore,
                        onClick = {
                            item.media?.id?.let(navigateToMediaDetails)
                        },
                        onLongClick = {
                            item.media?.let { media ->
                                onLongClickItem(
                                    media.basicMediaDetails,
                                    media.mediaListEntry?.basicMediaListEntry
                                )
                            }
                        }
                    )
                }
            } else if (isLoading) {
                DiscoverLazyRow(
                    minHeight = MEDIA_POSTER_SMALL_HEIGHT.dp
                ) {
                    items(10) {
                        AiringAnimeHorizontalItemPlaceholder()
                    }
                }
            } else {
                Text(
                    text = stringResource(R.string.no_information),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        else -> {
            DiscoverLazyRow(
                minHeight = MEDIA_POSTER_SMALL_HEIGHT.dp
            ) {
                items(10) {
                    AiringAnimeHorizontalItemPlaceholder()
                }
            }
        }
    }
}