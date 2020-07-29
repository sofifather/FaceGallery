package com.example.facegallery.ui.main.album

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.facegallery.R
import com.example.facegallery.ui.AlbumView

class AlbumListAdapter : RecyclerView.Adapter<AlbumItemViewHolder>() {

    private val items: ArrayList<AlbumView> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItemViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album, parent, false)
            .let {
                AlbumItemViewHolder(
                    it
                )
            }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(items: Collection<AlbumView>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

class AlbumItemViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
    fun bind(view: AlbumView) {

        val wait = v.findViewById<FrameLayout>(R.id.wait)

        v.findViewById<TextView>(R.id.name).apply {
            text = view.name
        }
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
                        wait.visibility = View.GONE
                        return false
                    }

                })
                .into(this)
        }
        v.setOnClickListener {
            AlbumListFragmentDirections.actionGalleryListFragmentToPhotoListFragment(view.albumId).apply {
                Navigation.findNavController(it).navigate(this)
            }
        }
    }
}