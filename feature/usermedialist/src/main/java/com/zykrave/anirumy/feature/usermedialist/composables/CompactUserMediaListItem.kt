package com.zykrave.anirumy.feature.usermedialist.composables
import com.zykrave.anirumy.core.common.utils.NumberUtils.format

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zykrave.anirumy.core.common.utils.NumberUtils.isGreaterThanZero
import com.zykrave.anirumy.core.model.media.duration
import com.zykrave.anirumy.core.model.media.exampleBasicMediaListEntry
import com.zykrave.anirumy.core.model.media.exampleCommonMediaListEntry
import com.zykrave.anirumy.core.model.media.isUsingVolumeProgress
import com.zykrave.anirumy.core.model.media.progressOrVolumes
import com.zykrave.anirumy.core.network.fragment.CommonMediaListEntry
import com.zykrave.anirumy.core.network.type.MediaListStatus
import com.zykrave.anirumy.core.network.type.ScoreFormat
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.common.LocalBlurAdult
import com.zykrave.anirumy.core.ui.composables.IncrementOneButton
import com.zykrave.anirumy.core.ui.composables.media.AiringScheduleText
import com.zykrave.anirumy.core.ui.composables.media.AllPriorityColors
import com.zykrave.anirumy.core.ui.composables.media.ListStatusBadgeIndicator
import com.zykrave.anirumy.core.ui.composables.media.MEDIA_POSTER_COMPACT_WIDTH
import com.zykrave.anirumy.core.ui.composables.media.MediaPoster
import com.zykrave.anirumy.core.ui.composables.media.PriorityIndicator
import com.zykrave.anirumy.core.ui.composables.scores.BadgeScoreIndicator
import com.zykrave.anirumy.core.ui.theme.AniHyouTheme

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun CompactUserMediaListItem(
    item: CommonMediaListEntry,
    listStatus: MediaListStatus?,
    scoreFormat: ScoreFormat,
    isMyList: Boolean,
    isPlusEnabled: Boolean,
    showLowPriority: Boolean,
    allPriorityColors: AllPriorityColors,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onClickPlus: (Int) -> Unit,
    blockPlus: () -> Unit,
    onClickNotes: () -> Unit,
) {
    val blurAdult = LocalBlurAdult.current
    val status = listStatus ?: item.basicMediaListEntry.status
    val priority = item.basicMediaListEntry.priority
    ListItem(
        onClick = onClick,
        onLongClick = onLongClick,
        leadingContent = {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                MediaPoster(
                    url = item.media?.coverImage?.large,
                    enableBlur = blurAdult && item.media?.basicMediaDetails?.isAdult == true,
                    showShadow = false,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.size(
                        width = MEDIA_POSTER_COMPACT_WIDTH.dp,
                        height = (MEDIA_POSTER_COMPACT_WIDTH + 8).dp
                    )
                )

                if (listStatus == null && status != null) {
                    ListStatusBadgeIndicator(
                        alignment = Alignment.TopStart,
                        status = status
                    )
                }

                if (item.basicMediaListEntry.score?.isGreaterThanZero() == true) {
                    BadgeScoreIndicator(
                        modifier = Modifier.align(Alignment.BottomStart),
                        score = item.basicMediaListEntry.score,
                        scoreFormat = scoreFormat
                    )
                }

                if (priority != null && (priority > 0 || showLowPriority)) {
                    PriorityIndicator(
                        modifier = Modifier.align(Alignment.TopEnd),
                        priority = priority,
                        allPriorityColors = allPriorityColors,
                    )
                }
            }//: Box
        }
    ) {
        Column(
            modifier = Modifier.height((MEDIA_POSTER_COMPACT_WIDTH + 8).dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.media?.basicMediaDetails?.title?.userPreferred.orEmpty(),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                lineHeight = 19.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (item.media?.nextAiringEpisode != null) 1 else 2
            )

            AiringScheduleText(
                item = item,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                val progress = item.basicMediaListEntry.progressOrVolumes()?.format() ?: 0
                val duration = item.duration()?.format()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = if (duration != null) "$progress/$duration" else "$progress",
                        fontSize = 15.sp,
                    )
                    if (item.basicMediaListEntry.isUsingVolumeProgress()) {
                        Icon(
                            painter = painterResource(R.drawable.bookmark_20),
                            contentDescription = stringResource(R.string.volumes),
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    if (item.basicMediaListEntry.repeat.isGreaterThanZero()) {
                        RepeatIndicator(
                            count = item.basicMediaListEntry.repeat ?: 0,
                        )
                    }
                    if (!item.basicMediaListEntry.notes.isNullOrBlank()) {
                        NotesIndicator(
                            modifier = Modifier.padding(bottom = 2.dp),
                            onClick = onClickNotes
                        )
                    }
                    if (isMyList && (status == MediaListStatus.CURRENT
                                || status == MediaListStatus.REPEATING)
                    ) {
                        IncrementOneButton(
                            onClickPlus = onClickPlus,
                            blockPlus = blockPlus,
                            enabled = isPlusEnabled,
                        )
                    }
                }
            }//:Row
        }//:Column
    }
}

@Preview
@Composable
private fun CompactUserMediaListItemPreview() {
    AniHyouTheme {
        Surface {
            Column {
                CompactUserMediaListItem(
                    item = exampleCommonMediaListEntry,
                    listStatus = MediaListStatus.CURRENT,
                    scoreFormat = ScoreFormat.POINT_100,
                    isMyList = true,
                    isPlusEnabled = true,
                    showLowPriority = true,
                    allPriorityColors = AllPriorityColors.Default,
                    onClick = {},
                    onLongClick = {},
                    onClickPlus = {},
                    blockPlus = {},
                    onClickNotes = {}
                )
                CompactUserMediaListItem(
                    item = exampleCommonMediaListEntry.copy(
                        basicMediaListEntry = exampleBasicMediaListEntry.copy(
                            score = 3.0,
                            status = MediaListStatus.PLANNING
                        )
                    ),
                    listStatus = null,
                    scoreFormat = ScoreFormat.POINT_3,
                    isMyList = true,
                    isPlusEnabled = true,
                    showLowPriority = false,
                    allPriorityColors = AllPriorityColors.Default,
                    onClick = {},
                    onLongClick = {},
                    onClickPlus = {},
                    blockPlus = {},
                    onClickNotes = {}
                )
            }
        }
    }
}
