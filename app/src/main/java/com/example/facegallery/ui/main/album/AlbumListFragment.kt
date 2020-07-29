package com.example.facegallery.ui.main.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.facegallery.R
import com.example.facegallery.data.viewmodel.AlbumListFragmentViewModel
import com.example.facegallery.data.viewmodel.AlbumListFragmentViewModelFactory
import com.example.facegallery.ui.AlbumView

class AlbumListFragment : Fragment() {

    private val listAdapter = AlbumListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.albumList).apply {
            adapter = listAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ViewModelProvider(this,
            AlbumListFragmentViewModelFactory())[AlbumListFragmentViewModel::class.java].also {
            it.albumData.observe(this.viewLifecycleOwner, Observer {albumList ->
                listAdapter.setItems(albumList.map {album ->
                    AlbumView(album.id, album.picture.data.url, album.name)
                })
            })
        }
    }

}