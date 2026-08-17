package com.zykrave.anirumy.core.ui.composables.markdown

import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.UriHandler
import com.zykrave.anirumy.core.ui.utils.MarkdownUtils.onMarkdownLinkClicked

@Stable
class MarkdownUriHandler(
    private val onSpoilerClicked: (String) -> Unit = {},
    private val onLinkClicked: (String) -> Unit = {},
): UriHandler {
    override fun openUri(uri: String) {
        uri.onMarkdownLinkClicked(
            onSpoilerClicked = onSpoilerClicked,
            onLinkClicked = onLinkClicked,
        )
    }
}