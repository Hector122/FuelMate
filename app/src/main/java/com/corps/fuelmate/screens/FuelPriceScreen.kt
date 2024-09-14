package com.corps.fuelmate.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun FullPriceScreen(viewModel: FullPriceScreenViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getFuelPrices()
    }

    when (state) {
        is Loading -> ShowLoadingIndicator()

        is Error -> Text(text = (state as Error).message)

        is Success -> {
            Column(modifier = Modifier
                .fillMaxSize()) {
                val fuels = (state as Success).fuelList
                LazyColumn {
                    items(items = fuels, key = { item -> item.id }) { item ->
                        Text(text = item.name)
                        Text(text = item.price)
                        Text(text = item.code)
                        Text(text = item.date)
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun ShowLoadingIndicator() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp), // Make it larger
            color = Color.Blue, // Change the color to blue
            strokeWidth = 8.dp // Make the line thicker
        )
    }
}

