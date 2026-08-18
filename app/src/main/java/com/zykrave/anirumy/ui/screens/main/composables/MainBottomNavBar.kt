package com.zykrave.anirumy.ui.screens.main.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zykrave.anirumy.core.ui.common.BottomDestination
import com.zykrave.anirumy.core.ui.common.BottomDestination.Companion.testTag
import com.zykrave.anirumy.core.ui.common.LocalNavActionManager
import com.zykrave.anirumy.core.ui.common.navigation.Route

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainBottomNavBar(
    currentTopRoute: Route,
    isVisible: Boolean,
    onItemSelected: (Int) -> Unit,
) {
    val navActionManager = LocalNavActionManager.current
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        NavigationBar(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(20.dp)
                ),
            containerColor = Color(0xFF1A1B2E).copy(alpha = 0.7f)
        ) {
            BottomDestination.values.forEachIndexed { index, dest ->
                val isSelected = dest.route == currentTopRoute
                NavigationBarItem(
                    icon = {
                        dest.Icon(selected = isSelected)
                    },
                    modifier = Modifier.semantics {
                        testTagsAsResourceId = true
                        testTag = dest.testTag
                    },
                    label = {
                        Text(
                            text = stringResource(dest.title),
                            textAlign = TextAlign.Center
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        if (isSelected) {
                            when (dest) {
                                BottomDestination.Explore -> {
                                    navActionManager.toSearch()
                                }

                                else -> {}
                            }
                        } else {
                            onItemSelected(index)
                            navActionManager.navigate(dest.route)
                        }
                    }
                )
            }
        }
    }
}
