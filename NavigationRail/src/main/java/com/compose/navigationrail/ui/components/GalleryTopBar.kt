/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 */

package com.compose.navigationrail.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.compose.navigationrail.R

private val GALLERY_TITLE_TOP_PADDING = 20.dp
private val GALLERY_TITLE_BAR_HEIGHT = 120.dp
private val ELEVATION = 0.dp

@Composable
fun GalleryTopBar(
    title: String,
    horizontalPadding: Dp,
) {
    TopAppBar(
        modifier = Modifier
            .height(GALLERY_TITLE_BAR_HEIGHT),
        backgroundColor = Color.Transparent,
        elevation = ELEVATION,
        contentPadding = PaddingValues(
            start = horizontalPadding,
            end = horizontalPadding,
            top = GALLERY_TITLE_TOP_PADDING
        ),
    ) {
        Text(
            text = title,
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Start,
            modifier = Modifier.testTag(
                stringResource(id = R.string.nav_rail_top_bar_tag)
            )
        )
    }
}
