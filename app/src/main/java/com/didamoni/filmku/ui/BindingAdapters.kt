package com.didamoni.filmku.ui

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.didamoni.filmku.R
import com.didamoni.filmku.data.domain.Movie
import com.didamoni.filmku.ui.movies.MoviesAdapter

@BindingAdapter("binding_list")
fun RecyclerView.setList(value: List<Movie>?) { value?.let {
	val adapter = adapter as MoviesAdapter
	adapter.submitList(it)
}}

@BindingAdapter("binding_imageResource_withGlide")
fun ImageView.setImageResourceWithGlide(value: String?) { value?.let {
	val imgUri = it.toUri().buildUpon().scheme("https").build()
	Glide.with(context)
		.load(imgUri)
		.apply(
			RequestOptions()
			.placeholder(R.drawable.loading_animation)
			.error(R.drawable.ic_broken_image))
		.into(this)
}}
