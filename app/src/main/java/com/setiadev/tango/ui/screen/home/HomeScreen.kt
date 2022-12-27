package com.setiadev.tango.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.setiadev.tango.di.Injection
import com.setiadev.tango.model.PlayerList
import com.setiadev.tango.ui.ViewModelFactory
import com.setiadev.tango.ui.component.PlayerItem
import com.setiadev.tango.ui.util.UiState

@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.primary,
                title = {
                    Text(
                        text = "Tango Team",
                        style = MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        modifier = modifier.padding(start = 16.dp)
                    )
                }
            )
        }
    ) {
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllPlayers()
                }
                is UiState.Success -> {
                    MainContent(
                        playerList = uiState.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                    )
                }
                is UiState.Error -> {}
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MainContent(
    playerList: List<PlayerList>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(26.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(playerList) { list ->
            PlayerItem(
                image = list.player.image,
                name = list.player.name,
                jerseyNumber = list.player.jerseyNumber,
                modifier = Modifier.clickable {
                    navigateToDetail(list.player.id)
                }
            )
        }
    }
}