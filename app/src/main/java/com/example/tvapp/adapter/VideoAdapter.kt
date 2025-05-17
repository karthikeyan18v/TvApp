package com.example.tvapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvapp.databinding.ItemVideoBinding
import com.example.tvapp.model.Video

class VideoAdapter(
    private val videos: List<Video>,
    private val onClick: (Video) -> Unit
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        with(holder.binding) {
            Glide.with(root.context)
                .load(video.thumbnailUrl)
                .into(ivThumbnail)
            tvTitle.text = video.title

            root.setOnClickListener { onClick(video) }
            root.setOnFocusChangeListener { _, hasFocus ->
                root.animate().scaleX(if (hasFocus) 1.1f else 1f).setDuration(200).start()
                root.animate().scaleY(if (hasFocus) 1.1f else 1f).setDuration(200).start()
            }
        }
    }

    override fun getItemCount() = videos.size
}