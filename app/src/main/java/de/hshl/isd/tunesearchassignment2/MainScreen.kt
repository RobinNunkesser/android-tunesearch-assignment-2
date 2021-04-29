package de.hshl.isd.tunesearchassignment2

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import de.hshl.isd.explicitarchitecture.tunesearch.core.MockSearchTracksCommand
import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.CollectionEntity
import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.SearchTracksDTO

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {
    val service = MockSearchTracksCommand()
    var searchTerm by rememberSaveable { mutableStateOf("") }

    fun success(collections: List<CollectionEntity>) {
        viewModel.collections = collections.map { collection ->
            CollectionViewModel(name = collection.name,
                tracks = collection.tracks.map { track -> TrackViewModel(track.artistName, track.artworkUrl, "${track.trackNumber} - ${track.trackName}") }) }
        navController.navigate(Screen.Tracks.route)
    }

    fun failure(error: Throwable) {
        Log.e("MainScreen", error.localizedMessage!!)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tune Search") })
        })
    {
        Column(Modifier.fillMaxSize().padding(8.dp), verticalArrangement = Arrangement.Center) {
            TextField(value = searchTerm,
                modifier = Modifier.padding(8.dp),
                onValueChange = {
                    searchTerm = it
                })
            Button(onClick = {
                service.execute(
                    SearchTracksDTO(searchTerm),
                    ::success,
                    ::failure)
            }) {
                Text("Search")
            }
        }
        }
    }
