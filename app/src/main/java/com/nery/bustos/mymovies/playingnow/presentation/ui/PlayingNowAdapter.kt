package com.nery.bustos.mymovies.playingnow.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nery.bustos.moviesbasemodule.network.Api
import com.nery.bustos.mymovies.App
import com.nery.bustos.mymovies.R
import com.nery.bustos.mymovies.databinding.ItemMovieBinding
import com.nery.bustos.mymovies.playingnow.data.PlayingNowItemView

class PlayingNowAdapter
constructor(
    private val ls: List<PlayingNowItemView>,
    private val onItemClicked: (item: PlayingNowItemView) -> Unit) :
    RecyclerView.Adapter<PlayingNowAdapter.PlayingNowViewHolder>() {

    class PlayingNowViewHolder(
        private val binding: ItemMovieBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemView: PlayingNowItemView,onItemClicked: (item: PlayingNowItemView) -> Unit) {
            binding.apply {
                root.setOnClickListener {
                    onItemClicked(itemView)
                }
                rate.text = App
                    .applicationContext()
                    .getString(R.string.rate, itemView.voteAverage.toString())
                year.text = itemView.releaseDate
                title.text = itemView.title
                Glide.with(binding.root).load("${Api.IMAGE_URL}${itemView.posterPath}")
                    .into(poster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayingNowViewHolder {
        val mView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val binding: ItemMovieBinding = ItemMovieBinding.bind(mView)
        return PlayingNowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayingNowViewHolder, position: Int) {
        holder.bind(ls[position], onItemClicked)
    }

    override fun getItemCount(): Int = ls.size
}