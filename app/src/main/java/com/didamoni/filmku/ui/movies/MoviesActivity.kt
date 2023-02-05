package com.didamoni.filmku.ui.movies

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.didamoni.filmku.R
import com.didamoni.filmku.data.repository.Resource
import com.didamoni.filmku.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity()
{
	private val viewModel: MoviesViewModel by viewModels()

	@SuppressLint("NotifyDataSetChanged")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val binding = ActivityMoviesBinding.inflate(layoutInflater)
		binding.lifecycleOwner = this

		binding.viewModel = viewModel
		binding.adapter = MoviesAdapter()

		viewModel.result.observe(this) { it?.let {
			binding.progressbar.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
			binding.txtError.isVisible = it is Resource.Error && it.data.isNullOrEmpty()
			binding.txtError.text = it.error?.localizedMessage
		}}

		viewModel.sortedByYear.observe(this) { it?.let {

			binding.recyclerView.scrollToPosition(0)

			if (it) {
				binding.txtSortYear.setTextColor(
					ResourcesCompat.getColor(resources, R.color.font_movie_year, null)
				)
				binding.txtSortRate.setTextColor(
					ResourcesCompat.getColor(resources, R.color.black, null)
				)
			} else {
				binding.txtSortYear.setTextColor(
					ResourcesCompat.getColor(resources, R.color.black, null)
				)
				binding.txtSortRate.setTextColor(
					ResourcesCompat.getColor(resources, R.color.font_movie_year, null)
				)
			}
		}}

		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)
	}

}
