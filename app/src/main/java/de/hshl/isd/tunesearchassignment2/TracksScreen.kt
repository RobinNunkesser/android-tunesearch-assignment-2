package de.hshl.isd.tunesearchassignment2

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun TracksScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Tracks") })
        })
    {

        LazyColumn {
            items(viewModel.collections) { collection ->
                SectionHeader(title = collection.name)
                collection.tracks.forEach { track ->
                    TrackRow(track)
                }
            }
        }
    }
}