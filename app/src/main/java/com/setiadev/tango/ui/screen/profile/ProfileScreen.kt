package com.setiadev.tango.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.setiadev.tango.R
import com.setiadev.tango.ui.theme.TangoTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.me),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(R.string.my_name),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = stringResource(R.string.email),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ProfileScreenPreview() {
    TangoTheme {
        ProfileScreen()
    }
}