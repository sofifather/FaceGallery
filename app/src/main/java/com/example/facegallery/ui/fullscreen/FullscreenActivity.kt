package com.example.facegallery.ui.fullscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.facegallery.R
import com.example.facegallery.data.Photo
import com.example.facegallery.data.viewmodel.FullscreenActivityViewModel
import com.example.facegallery.data.viewmodel.FullscreenActivityViewModelFactory

class FullscreenActivity : AppCompatActivity() {

    private lateinit var pager: ViewPager
    private lateinit var model: FullscreenActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen)

        val position = intent?.extras?.getInt("position")

        pager = findViewById(R.id.pager)

        model = ViewModelProvider(this,
            FullscreenActivityViewModelFactory())[FullscreenActivityViewModel::class.java]
        model.photos.observe(this, Observer {
            pager.adapter = PhotoSlideAdapter(supportFragmentManager, it)
            position?.let {position ->
                pager.setCurrentItem(position, false)
            }
        })
    }

    private inner class PhotoSlideAdapter(fm: FragmentManager,val photos: List<Photo>)
        : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return FullscreenPageFragment(photos[position])
        }

        override fun getCount(): Int {
            return photos.size
        }
    }
}