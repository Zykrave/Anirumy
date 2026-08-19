package com.zykrave.anirumy.core.ui.composables.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.DropdownMenuPopup
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zykrave.anirumy.core.common.utils.DateUtils.timestampIntervalSinceNow
import com.zykrave.anirumy.core.network.type.ActivityType
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.composables.common.CommentIconButton
import com.zykrave.anirumy.core.ui.composables.common.FavoriteIconButton
import com.zykrave.anirumy.core.ui.composables.defaultPlaceholder
import com.zykrave.anirumy.core.ui.composables.markdown.DefaultMarkdownText
import com.zykrave.anirumy.core.ui.composables.media.MediaPoster
import com.zykrave.anirumy.core.ui.composables.person.PersonItemSmall
import com.zykrave.anirumy.core.ui.theme.AniHyouTheme
import com.zykrave.anirumy.core.ui.utils.ComposeDateUtils.secondsToLegibleText
import java.time.temporal.ChronoUnit

const val ACTIVITY_IMAGE_SIZE = 48

@Composable
fun ActivityItem(
    type: ActivityType,
    text: String,
    createdAt: Int,
    replyCount: Int,
    likeCount: Int,
    isLiked: Boolean?,
    modifier: Modifier = Modifier,
    blurImage: Boolean = false,
    imageUrl: String? = null,
    username: String? = null,
    isPrivate: Boolean? = null,
    isLocked: Boolean? = null,
    onClick: () -> Unit,
    onClickImage: () -> Unit = {},
    onClickLike: () -> Unit,
    onClickDelete: () -> Unit,
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Color.Black.copy(alpha = 0.4f),
                spotColor = Color.Black.copy(alpha = 0.4f)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1C1D2C))
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.05f),
                            Color.White.copy(alpha = 0f)
                        )
                    )
                )
        )
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            if (type == ActivityType.MEDIA_LIST) {
                MediaPoster(
                    url = imageUrl,
                    enableBlur = blurImage,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(ACTIVITY_IMAGE_SIZE.dp)
                        .clickable(onClick = onClickImage),
                    showShadow = false
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    if (type == ActivityType.TEXT || type == ActivityType.MESSAGE) {
                        PersonItemSmall(
                            avatarUrl = imageUrl,
                            username = username,
                            modifier = Modifier.padding(bottom = 8.dp),
                            isPrivate = isPrivate,
                            isLocked = isLocked,
                            onClick = onClickImage
                        )
                        DefaultMarkdownText(
                            markdown = text,
                            modifier = Modifier.weight(1f),
                            textStyle = MaterialTheme.typography.bodyMedium,
                        )
                    } else {
                        Text(
                            text = text,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .weight(1f),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    ActivityMenu(
                        modifier = Modifier.align(Alignment.Top),
                        onClickDelete = onClickDelete
                    )
                }

                Row(
                    modifier = Modifier.align(Alignment.End),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = createdAt.toLong().timestampIntervalSinceNow()
                            .secondsToLegibleText(
                                maxUnit = ChronoUnit.WEEKS,
                                isFutureDate = false
                            ),
                        modifier = Modifier.weight(1f),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelMedium
                    )
                    CommentIconButton(
                        modifier = Modifier.width(78.dp),
                        commentCount = replyCount,
                        onClick = onClick,
                        fontSize = 14.sp,
                        iconSize = 20.dp,
                        tint = Color(0xFF8B5CF6)
                    )
                    FavoriteIconButton(
                        modifier = Modifier.width(78.dp),
                        isFavorite = isLiked == true,
                        favoritesCount = likeCount,
                        onClick = onClickLike,
                        fontSize = 14.sp,
                        iconSize = 20.dp,
                        tint = Color(0xFF8B5CF6)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ActivityMenu(
    modifier: Modifier = Modifier,
    onClickDelete: () -> Unit,
) {
    Box(modifier = modifier.wrapContentSize(Alignment.TopEnd)) {
        var moreExpanded by remember { mutableStateOf(false) }
        IconButton(
            onClick = { moreExpanded = !moreExpanded },
            shapes = IconButtonDefaults.shapes()
        ) {
            Icon(
                painter = painterResource(R.drawable.more_vert_24),
                contentDescription = stringResource(R.string.show_more),
                tint = Color(0xFF8B5CF6)
            )
        }
        DropdownMenuPopup(
            expanded = moreExpanded,
            onDismissRequest = { moreExpanded = false }
        ) {
            DropdownMenuItem(
                checked = false,
                onCheckedChange = {
                    moreExpanded = false
                    onClickDelete()
                },
                text = { Text(text = stringResource(R.string.delete)) },
                shapes = MenuDefaults.itemShapes(),
            )
        }
    }
}

@Composable
fun ActivityItemPlaceholder(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = Color.Black.copy(alpha = 0.4f),
                spotColor = Color.Black.copy(alpha = 0.4f)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1C1D2C))
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.05f),
                            Color.White.copy(alpha = 0f)
                        )
                    )
                )
        )
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(ACTIVITY_IMAGE_SIZE.dp)
                    .defaultPlaceholder(visible = true)
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "This is a  loading placeholder",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .defaultPlaceholder(visible = true)
                )

                Text(
                    text = "Placeholder",
                    modifier = Modifier.defaultPlaceholder(visible = true),
                    fontSize = 14.sp,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ActivityItemPreview() {
    AniHyouTheme {
        Surface {
            Column {
                ActivityItem(
                    type = ActivityType.MEDIA_LIST,
                    text = "Plans to watch Alice to Therese no Maboroshi Koujou",
                    createdAt = 1927389,
                    replyCount = 999,
                    likeCount = 999,
                    isLiked = false,
                    imageUrl = "",
                    modifier = Modifier.padding(8.dp),
                    onClick = {},
                    onClickLike = {},
                    onClickDelete = {},
                )
                ActivityItemPlaceholder(
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}