package com.zykrave.anirumy.feature.profile.stats.overview
import com.zykrave.anirumy.core.common.utils.NumberUtils.format

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zykrave.anirumy.core.model.genre.SelectableGenre.Companion.genreTagLocalized
import com.zykrave.anirumy.core.ui.composables.defaultPlaceholder
import com.zykrave.anirumy.core.model.point100PrimaryColor
import com.zykrave.anirumy.core.model.point10PrimaryColor
import com.zykrave.anirumy.core.model.point5PrimaryColor
import com.zykrave.anirumy.core.model.smileyPrimaryColor
import com.zykrave.anirumy.core.model.stats.StatDistributionType
import com.zykrave.anirumy.core.network.type.MediaType
import com.zykrave.anirumy.core.network.type.ScoreFormat
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.composables.InfoTitle
import com.zykrave.anirumy.core.ui.composables.TextSubtitleVertical
import com.zykrave.anirumy.core.ui.composables.common.FilterSelectionChip
import com.zykrave.anirumy.core.ui.composables.stats.HorizontalStatsBar
import com.zykrave.anirumy.core.ui.composables.stats.VerticalStatsBar
import com.zykrave.anirumy.core.ui.theme.AniHyouTheme
import com.zykrave.anirumy.feature.profile.stats.UserStatsEvent
import com.zykrave.anirumy.feature.profile.stats.UserStatsUiState
import com.zykrave.anirumy.feature.profile.stats.composables.DistributionTypeChips
import com.zykrave.anirumy.feature.profile.stats.composables.MediaTypeChips

@Composable
fun OverviewStatsView(
    uiState: UserStatsUiState,
    event: UserStatsEvent?,
    modifier: Modifier = Modifier,
) {
    val isAnime = uiState.mediaType == MediaType.ANIME
    val stats = if (isAnime) uiState.animeOverview else uiState.mangaOverview
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MediaTypeChips(
            value = uiState.mediaType,
            onValueChanged = { event?.setMediaType(it) }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1C1D2C))
                    .padding(vertical = 12.dp)
            ) {
                TextSubtitleVertical(
                    text = stats?.count?.format(),
                    subtitle = stringResource(R.string.total),
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = uiState.isLoading
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1C1D2C))
                    .padding(vertical = 12.dp)
            ) {
                TextSubtitleVertical(
                    text = stats?.episodeOrChapterCount?.format(),
                    subtitle = if (isAnime) stringResource(R.string.episodes_watched)
                    else stringResource(R.string.chapters_read),
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = uiState.isLoading
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1C1D2C))
                    .padding(vertical = 12.dp)
            ) {
                TextSubtitleVertical(
                    text = stats?.daysOrVolumes?.format(),
                    subtitle = if (isAnime) stringResource(R.string.days_watched)
                    else stringResource(R.string.volumes_read),
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = uiState.isLoading
                )
            }
        }//: Row

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1C1D2C))
                    .padding(vertical = 12.dp)
            ) {
                TextSubtitleVertical(
                    text = stats?.plannedCount?.format(),
                    subtitle = if (isAnime) stringResource(R.string.days_planned)
                    else stringResource(R.string.chapters_planned),
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = uiState.isLoading
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1C1D2C))
                    .padding(vertical = 12.dp)
            ) {
                TextSubtitleVertical(
                    text = stats?.meanScore?.format(),
                    subtitle = stringResource(R.string.mean_score),
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = uiState.isLoading
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF1C1D2C))
                    .padding(vertical = 12.dp)
            ) {
                TextSubtitleVertical(
                    text = stats?.standardDeviation?.format(),
                    subtitle = stringResource(R.string.standard_deviation),
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = uiState.isLoading
                )
            }
        }//: Row

        // Genre distribution donut chart
        val genreStats = if (isAnime) uiState.animeGenres else uiState.mangaGenres
        val sortedGenres = genreStats.orEmpty().sortedByDescending { it.count }
        val topGenres = sortedGenres.take(5)
        val otherCount = sortedGenres.drop(5).sumOf { it.count }

        val segmentColors = listOf(
            Color(0xFF8B5CF6), // purple
            Color(0xFF4FD1C5), // teal
            Color(0xFFF472B6), // pink
            Color(0xFF60A5FA), // blue
            Color(0xFFFBBF24), // amber
            Color(0xFF6B7280)  // gray
        )

        val donutSegments = topGenres.mapIndexed { index, stat ->
            DonutSegment(
                name = stat.genre?.genreTagLocalized() ?: stringResource(R.string.unknown),
                value = stat.count,
                color = segmentColors[index % (segmentColors.size - 1)]
            )
        }.toMutableList()

        if (otherCount > 0) {
            donutSegments.add(
                DonutSegment(
                    name = stringResource(R.string.other),
                    value = otherCount,
                    color = segmentColors.last()
                )
            )
        }

        InfoTitle(text = stringResource(R.string.genres))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF1C1D2C))
        ) {
            GenreDonutChart(
                donutSegments = donutSegments,
                isLoading = uiState.isLoading
            )
        }

        // Score stats
        InfoTitle(text = stringResource(R.string.score))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            arrayOf(
                StatDistributionType.TITLES,
                StatDistributionType.TIME
            ).forEach {
                FilterSelectionChip(
                    selected = uiState.scoreType == it,
                    text = it.localized(),
                    onClick = { event?.setScoreType(it) },
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
        VerticalStatsBar(
            stats = when (uiState.scoreType) {
                StatDistributionType.TITLES -> stats?.scoreCount.orEmpty()
                StatDistributionType.TIME -> stats?.scoreTime.orEmpty()
                else -> emptyList()
            },
            modifier = Modifier.padding(8.dp),
            mapColorTo = {
                when (stats?.scoreFormat) {
                    ScoreFormat.POINT_3 -> it.score.smileyPrimaryColor()
                    ScoreFormat.POINT_5 -> it.score.point5PrimaryColor()
                    ScoreFormat.POINT_10 -> it.score.point10PrimaryColor()
                    else -> it.score.point100PrimaryColor()
                }
            },
            isLoading = uiState.isLoading
        )

        // Episode/Chapter count
        InfoTitle(
            text = stringResource(if (isAnime) R.string.episode_count else R.string.chapter_count)
        )
        DistributionTypeChips(
            value = uiState.lengthType,
            onValueChanged = { event?.setLengthType(it) },
        )
        VerticalStatsBar(
            stats = when (uiState.lengthType) {
                StatDistributionType.TITLES -> stats?.lengthCount.orEmpty()
                StatDistributionType.TIME -> stats?.lengthTime.orEmpty()
                StatDistributionType.SCORE -> stats?.lengthScore.orEmpty()
            },
            modifier = Modifier.padding(8.dp),
            isLoading = uiState.isLoading
        )

        // Status distribution
        InfoTitle(text = stringResource(R.string.status_distribution))
        HorizontalStatsBar(
            stats = stats?.statusDistribution.orEmpty(),
            verticalPadding = 8.dp,
            showTotal = false,
            isLoading = uiState.isLoading
        )

        // Format distribution
        InfoTitle(text = stringResource(R.string.format_distribution))
        HorizontalStatsBar(
            stats = stats?.formatDistribution.orEmpty(),
            verticalPadding = 8.dp,
            showTotal = false,
            isLoading = uiState.isLoading
        )

        // Country distribution
        InfoTitle(text = stringResource(R.string.country_distribution))
        HorizontalStatsBar(
            stats = stats?.countryDistribution.orEmpty(),
            verticalPadding = 8.dp,
            showTotal = false,
            isLoading = uiState.isLoading
        )

        // Release year
        InfoTitle(text = stringResource(R.string.release_year))
        DistributionTypeChips(
            value = uiState.releaseYearType,
            onValueChanged = { event?.setReleaseYearType(it) },
        )
        VerticalStatsBar(
            stats = when (uiState.releaseYearType) {
                StatDistributionType.TITLES -> stats?.releaseYearCount.orEmpty()
                StatDistributionType.TIME -> stats?.releaseYearTime.orEmpty()
                StatDistributionType.SCORE -> stats?.releaseYearScore.orEmpty()
            },
            modifier = Modifier.padding(8.dp),
            isLoading = uiState.isLoading
        )

        // Watch/Read year
        InfoTitle(
            text = stringResource(if (isAnime) R.string.watch_year else R.string.read_year)
        )
        DistributionTypeChips(
            value = uiState.startYearType,
            onValueChanged = { event?.setStartYearType(it) },
        )
        VerticalStatsBar(
            stats = when (uiState.startYearType) {
                StatDistributionType.TITLES -> stats?.startYearCount.orEmpty()
                StatDistributionType.TIME -> stats?.startYearTime.orEmpty()
                StatDistributionType.SCORE -> stats?.startYearScore.orEmpty()
            },
            modifier = Modifier.padding(8.dp),
            isLoading = uiState.isLoading
        )
    }//: Column
}

@Composable
private fun GenreDonutChart(
    donutSegments: List<DonutSegment>,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    val totalCount = donutSegments.sumOf { it.value }.toFloat()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .defaultPlaceholder(visible = isLoading),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        if (donutSegments.isNotEmpty() || isLoading) {
            Canvas(modifier = Modifier.size(100.dp)) {
                var startAngle = -90f
                if (isLoading) {
                    drawArc(
                        color = Color.Gray.copy(alpha = 0.2f),
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Butt)
                    )
                } else {
                    donutSegments.forEach { segment ->
                        val sweepAngle = if (totalCount > 0) (segment.value / totalCount) * 360f else 0f
                        drawArc(
                            color = segment.color,
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = false,
                            style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Butt)
                        )
                        startAngle += sweepAngle
                    }
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (isLoading) {
                    repeat(5) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(Color.Gray.copy(alpha = 0.2f))
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 4.dp)
                                    .defaultPlaceholder(visible = true)
                            )
                        }
                    }
                } else {
                    donutSegments.forEach { segment ->
                        val percentage =
                            if (totalCount > 0) (segment.value / totalCount * 100).toInt() else 0
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(segment.color)
                            )
                            Text(
                                text = segment.name,
                                style = MaterialTheme.typography.labelMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = "$percentage%",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

private data class DonutSegment(
    val name: String,
    val value: Int,
    val color: Color
)

@Preview
@Composable
private fun OverviewUserStatsViewPreview() {
    AniHyouTheme {
        Surface {
            OverviewStatsView(
                uiState = UserStatsUiState(userId = 0),
                event = null,
            )
        }
    }
}
