package de.hshl.isd.tunesearchassignment2.ui.main

import androidx.lifecycle.ViewModel
import de.hshl.isd.tunesearchassignment2.ui.tracks.ItemViewModel

class MainViewModel : ViewModel() {
    var data: List<ItemViewModel> = listOf()
}