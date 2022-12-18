package com.crownedjester.soft.gamesinfoandnews.representation.games_detail_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.Screenshot
import com.crownedjester.soft.gamesinfoandnews.databinding.ItemViewPagerBinding

class ScreenshotsAdapter(private val screenshots: List<Screenshot>) :
    RecyclerView.Adapter<ScreenshotsAdapter.ScreenshotsViewHolder>() {

    class ScreenshotsViewHolder(private val binding: ItemViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(screenshot: Screenshot) {
            binding.imageViewScreenshot.load(screenshot.image) {
                scale(Scale.FIT)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenshotsViewHolder {
        val binding =
            ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ScreenshotsViewHolder(binding)
    }

    override fun getItemCount(): Int =
        screenshots.size


    override fun onBindViewHolder(holder: ScreenshotsViewHolder, position: Int) {
        holder.bind(screenshots[position])
    }


}