package com.compose.navigationrail.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.compose.navigationrail.R
import com.compose.navigationrail.models.Image

private val BORDER_SIZE = 7.dp
private val GALLERY_SPACING = 2.dp
private const val NUM_COLUMNS = 2

@ExperimentalFoundationApi
@Composable
fun GalleryView(
    galleryList: List<Image>,
    currentImageId: Int?,
    onImageSelected: (Int) -> Unit,
    horizontalPadding: Dp
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(count = NUM_COLUMNS),
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(GALLERY_SPACING, Alignment.Top),
        horizontalArrangement = Arrangement.spacedBy(GALLERY_SPACING, Alignment.CenterHorizontally),
        contentPadding = PaddingValues(
            start = horizontalPadding,
            end = horizontalPadding,
            bottom = GALLERY_SPACING
        )
    ) {
        items(galleryList) { item ->
            GalleryItem(item, currentImageId, onImageSelected)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun GalleryItem(image: Image, currentImageId: Int?, onImageSelected: (Int) -> Unit) {
    Image(
        painterResource(id = image.image),
        contentDescription = stringResource(R.string.image_description, image.name, image.id),
        modifier = Modifier
            .selectable(
                onClick = { onImageSelected(image.id) },
                selected = image.id == currentImageId,
            )
            .then(
                if (image.id == currentImageId)
                    Modifier.border(BORDER_SIZE, MaterialTheme.colors.error)
                else
                    Modifier
            ),
        contentScale = ContentScale.FillWidth
    )
}
