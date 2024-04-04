package com.omerfpekgoz.movieapp.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.omerfpekgoz.movieapp.R
import com.omerfpekgoz.movieapp.data.data_source.remote.MovieApi
import com.omerfpekgoz.movieapp.databinding.ItemMovieBinding
import com.omerfpekgoz.movieapp.domain.model.MovieEntity

/**
 * Created by omerfarukpekgoz on 2.04.2024.
 */
class MovieListAdapter(
    var onMovieClicked: ((movieName: String) -> Unit)? = null,
) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    private var movieList = arrayListOf<MovieEntity>()

    fun submitList(list: List<MovieEntity>) {
        this.movieList.addAll(list)
        notifyDataSetChanged()
    }

    fun cleaner() {
        this.movieList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = movieList[position]
        with(holder.itemMovieBinding) {
            with(movieItem) {
                txtMovieTitle.text = original_title
                txtReleaseDate.text = release_date!!.split("-")[0]
                txtVoteAverage.text = "${String.format("%.1f", (vote_average) ?: 0.0)}/10"
                if (vote_average!! < 7.0) {
                    txtVoteAverage.setTextColor(Color.RED)
                    imgRate.setColorFilter(Color.RED)
                } else {
                    txtVoteAverage.setTextColor(Color.GREEN)
                    imgRate.setColorFilter(Color.GREEN)
                }

                Glide.with(holder.itemView.context)
                    .load(MovieApi.IMAGE_BASE_URL + poster_path)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.ic_block)
                    .error(R.drawable.ic_block)
                    .override(300, 400)
                    .centerCrop()
                    .into(imgMovieImage)

                imgGoToDetail.setOnClickListener {
                    onMovieClicked!!.invoke(original_title!!)
                }
            }
        }
    }

    override fun getItemCount(): Int = movieList.size
    inner class MovieViewHolder(val itemMovieBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemMovieBinding.root)

}