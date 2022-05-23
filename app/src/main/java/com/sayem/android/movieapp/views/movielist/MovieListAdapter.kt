package com.sayem.android.movieapp.views.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sayem.android.movieapp.base.ItemClickListener
import com.sayem.android.movieapp.data.Movie
import com.sayem.android.movieapp.databinding.ViewItemMovieBinding

/**
 * Created by Sayem on 5/23/2022
 */
class MovieListAdapter constructor(
    private val itemClickListener: ItemClickListener<Movie>
) : RecyclerView.Adapter<MovieViewHolder>() {

    private val mDiffer = AsyncListDiffer<Movie>(this, MovieListDiffer())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ViewItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view, itemClickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindData(mDiffer.currentList[position])
    }

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    fun setData(data: List<Movie>) {
        mDiffer.submitList(data)
    }
}

class MovieViewHolder(
    private val binding: ViewItemMovieBinding,
    private val itemClickListener: ItemClickListener<Movie>
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(movie: Movie) {
        binding.titleTextView.text = movie.title
        Glide.with(binding.thumbnailView).load(movie.thumbnailUrl)
            //.placeholder(R.drawable.photo_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade()).into(binding.thumbnailView)

        binding.root.setOnClickListener {
            itemClickListener.onItemClick(movie)
        }
    }
}

class MovieListDiffer : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}