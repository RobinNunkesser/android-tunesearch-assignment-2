package de.hshl.isd.tunesearchassignment2.ui.tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import de.hshl.isd.tunesearchassignment2.R
import de.hshl.isd.tunesearchassignment2.ui.main.MainViewModel

class TrackFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var list: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_track_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        list = requireActivity().findViewById(R.id.list)
        list.adapter = TrackListAdapter().apply {
            submitList(viewModel.data)
        }

    }


}
