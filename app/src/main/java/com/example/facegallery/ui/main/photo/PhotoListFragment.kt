package com.example.facegallery.ui.main.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.facegallery.R
import com.example.facegallery.data.viewmodel.PhotoListFragmentViewModel
import com.example.facegallery.data.viewmodel.PhotoListFragmentViewModelFactory
import com.example.facegallery.ui.PhotoView
import java.util.*

class PhotoListFragment : Fragment() {

    private val listAdapter = PhotoListAdapter()
    private val args: PhotoListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.photoList).apply {
            adapter = listAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ViewModelProvider(this,
            PhotoListFragmentViewModelFactory())[PhotoListFragmentViewModel::class.java].apply {
            photos.observe(this@PhotoListFragment.viewLifecycleOwner, Observer {
                it.map {photo ->
                    PhotoView(photo.images.last().source, photo.name, photo.created_time)
                }.also { photoViewList ->
                    listAdapter.setItems(photoViewList)
                }
            })
            fetchPhotos(args.albumId)
        }
    }
}