package de.hshl.isd.tunesearchassignment2.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import de.hshl.isd.explicitarchitecture.tunesearch.core.MockSearchTracksCommand
import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.CollectionEntity
import de.hshl.isd.explicitarchitecture.tunesearch.core.ports.SearchTracksDTO
import de.hshl.isd.tunesearchassignment2.R
import de.hshl.isd.tunesearchassignment2.ui.tracks.ItemViewModel
import de.hshl.isd.tunesearchassignment2.ui.tracks.TrackViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var searchButton : Button
    private lateinit var searchTermEditText : EditText
    val service = MockSearchTracksCommand()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(
                MainViewModel::class.java)

        searchTermEditText = requireActivity().findViewById(R.id.searchTermEditText)
        searchButton = requireActivity().findViewById(R.id.searchButton)
        searchButton.setOnClickListener {
            service.execute(
                SearchTracksDTO(searchTermEditText.text.toString()),
                ::success,
                ::failure
            )
        }
    }

    private fun success(collections: List<CollectionEntity>) {
        val trackList: MutableList<ItemViewModel> = mutableListOf()
        for (collection in collections) {
            trackList.add(ItemViewModel(collection.name))
            for (track in collection.tracks) {
                trackList.add(
                        TrackViewModel(
                                track.artistName,
                                track.artworkUrl,
                                "${track.trackNumber} - ${track.trackName}"
                        )
                )
            }
        }
        viewModel.data = trackList
        findNavController().navigate(R.id.action_mainFragment_to_trackFragment)
    }

    private fun failure(error: Throwable) {
        val builder = AlertDialog.Builder(requireActivity())
        val dialog = builder.setMessage(error.localizedMessage)
                .setTitle(android.R.string.dialog_alert_title)
                .setPositiveButton(android.R.string.ok, null).create()
        dialog.show()
    }
}