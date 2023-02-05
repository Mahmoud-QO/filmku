package com.didamoni.filmku.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.didamoni.filmku.data.domain.Movie
import com.didamoni.filmku.databinding.ItemMovieFlatBinding

class MoviesAdapter : ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(DiffUtilCallback())
{
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
		return MovieViewHolder(
			ItemMovieFlatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		)
	}

	override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	class MovieViewHolder(private val binding: ItemMovieFlatBinding) :
		RecyclerView.ViewHolder(binding.root)
	{
		fun bind(item: Movie) { binding.movie = item }
	}

	class DiffUtilCallback : DiffUtil.ItemCallback<Movie>()
	{
		override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
			return newItem.id == oldItem.id
		}
		override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
			return newItem == oldItem
		}
	}

}
