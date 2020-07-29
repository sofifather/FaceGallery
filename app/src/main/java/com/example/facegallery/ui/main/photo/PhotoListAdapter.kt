package com.example.facegallery.ui.main.photo

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.facegallery.R
import com.example.facegallery.ui.PhotoView
import com.example.facegallery.ui.fullscreen.FullscreenActivity
import com.example.facegallery.util.toStringDate

class PhotoListAdapter : RecyclerView.Adapter<PhotoItemViewHolder>() {

    private val items: ArrayList<PhotoView> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo, parent, false)
            .let {
                PhotoItemViewHolder(
                    it
                )
            }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    fun setItems(items: Collection<PhotoView>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

class PhotoItemViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
    fun bind(view: PhotoView, position: Int) {
        val wait = v.findViewById<FrameLayout>(R.id.wait)
        v.findViewById<ImageView>(R.id.miniature).apply {
            Glide.with(this)
                .load(view.miniatureWebPath)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        // implement error  case
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        wait.visibility = GONE
                        return false
                    }

                })
                .into(this)
        }
        v.findViewById<TextView>(R.id.name).apply {
            text = view.name ?: "no name"
        }
        v.findViewById<TextView>(R.id.date).apply {
            text = view.date?.toStringDate()
        }
        v.setOnClickListener {
            Intent(it.context, FullscreenActivity::class.java).apply {
                putExtra("position", position)
                it.context.startActivity(this)
            }
        }
    }
}