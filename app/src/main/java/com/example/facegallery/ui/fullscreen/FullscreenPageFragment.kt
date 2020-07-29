package com.example.facegallery.ui.fullscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.facegallery.R
import com.example.facegallery.data.Photo
import com.example.facegallery.util.toStringDate

class FullscreenPageFragment(val photo: Photo) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fullscreen_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.photo).apply {
            Glide.with(this).load(photo.images.first().source).into(this)
        }
        view.findViewById<TextView>(R.id.tvInfo).apply {
            val infoText = "${photo.name ?: "no name"} " +
                    "\n${photo.created_time?.toStringDate()} " +
                    "\n${photo.images.first().width}x${photo.images.first().height}px"
            text = infoText
        }
    }
}