package com.setiadev.tango.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.setiadev.tango.R
import com.setiadev.tango.ui.theme.Shapes
import com.setiadev.tango.ui.theme.TangoTheme

@Composable
fun PlayerItem(
    image: Int,
    name: String,
    jerseyNumber: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = stringResource(R.string.jersey_number, jerseyNumber),
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PlayerItemPreview() {
    TangoTheme {
        PlayerItem(R.drawable.messi, "Lionel Messi", 10)
    }
}