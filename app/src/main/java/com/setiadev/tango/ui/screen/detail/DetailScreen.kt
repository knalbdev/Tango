package com.setiadev.tango.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.setiadev.tango.R
import com.setiadev.tango.di.Injection
import com.setiadev.tango.ui.ViewModelFactory
import com.setiadev.tango.ui.theme.TangoTheme
import com.setiadev.tango.ui.util.UiState

@Composable
fun DetailScreen(
    playerId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getPlayerById(playerId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.player.image,
                    data.player.name,
                    data.player.jerseyNumber,
                    data.player.description,
                    data.player.born,
                    data.player.team,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    name: String,
    jerseyNumber: Int,
    description: String,
    born: String,
    team: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
           Box {
              Image(
                  painter = painterResource(image),
                  contentDescription = null,
                  contentScale = ContentScale.Crop,
                  modifier = modifier
                      .height(400.dp)
                      .fillMaxWidth()
              )
               Icon(
                   imageVector = Icons.Default.ArrowBack,
                   contentDescription = stringResource(R.string.back),
                   modifier = Modifier
                       .padding(16.dp)
                       .clickable { onBackClick() },
                   colorResource(R.color.white)
               )
           }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = stringResource(R.string.jersey_number, jerseyNumber),
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colors.secondary
                )
                Spacer(
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 12.dp)
                        .fillMaxWidth()
                        .height(4.dp)
                        .background(Color.LightGray)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = modifier
                ) {
                    Text(
                        text = stringResource(R.string.player_born),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Justify
                    )
                    Text(
                        text = born,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = modifier
                ) {
                    Text(
                        text = stringResource(R.string.player_team),
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Justify,
                    )
                    Text(
                        text = team,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DetailContentPreview() {
    TangoTheme {
        DetailContent(
            R.drawable.messi,
            "Lionel Messi",
            10,
            "Player Details.",
            "Player Born Date.",
            "Player Current Teams.",
            onBackClick = {}
        )
    }
}