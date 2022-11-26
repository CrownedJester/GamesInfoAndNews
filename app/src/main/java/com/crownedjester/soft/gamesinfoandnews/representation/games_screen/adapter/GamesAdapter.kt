package com.crownedjester.soft.gamesinfoandnews.representation.games_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.crownedjester.soft.gamesinfoandnews.data.model.dto.GameBaseData
import com.crownedjester.soft.gamesinfoandnews.databinding.ItemGameBinding

class GamesAdapter : RecyclerView.Adapter<GamesAdapter.GamesViewHolder>() {

    private val callback = object : ItemCallback<GameBaseData>() {

        override fun areItemsTheSame(oldItem: GameBaseData, newItem: GameBaseData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GameBaseData, newItem: GameBaseData): Boolean =
            oldItem == newItem

    }

    val differ = AsyncListDiffer(this@GamesAdapter, callback)


    class GamesViewHolder(private val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gameBaseData: GameBaseData) {
            gameBaseData.let {

                binding.apply {
                    tvGameTitle.text = it.title
                    tvGameDescription.text = it.description
                    tvGameDeveloper.text = it.developer
                    tvGameGenre.text = it.genre
                    ivThumbLogo.load(it.thumb) {
                        scale(Scale.FIT)
                        transformations(RoundedCornersTransformation(12f, 12f, 2f, 2f))
                    }

                }

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GamesViewHolder(binding)
    }

    override fun getItemCount(): Int =
        differ.currentList.size

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val gameData = differ.currentList[position]

        holder.bind(gameData)
    }

}