package com.didamoni.filmku.ui.movies

import androidx.lifecycle.*
import com.didamoni.filmku.data.domain.Movie
import com.didamoni.filmku.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(repository: MoviesRepository) : ViewModel()
{
	val result = repository.getMovies().asLiveData()

	private val _movies = MediatorLiveData<List<Movie>>()
	val movies: LiveData<List<Movie>> =_movies

	private val _sortedByYear = MutableLiveData(true)
	val sortedByYear: LiveData<Boolean> get() = _sortedByYear

	init {
		viewModelScope.launch {
			_movies.addSource(result) { resource ->
				_movies.value = resource.data?.sortedByDescending { it.year }
			}
		}
	}

	fun onSortByYear() = viewModelScope.launch {
		_movies.value = _movies.value?.sortedByDescending { it.year }
		delay(500)
		_sortedByYear.value = true
	}

	fun onSortByRate() = viewModelScope.launch {
		_movies.value = _movies.value?.sortedByDescending { it.imDbRating }
		delay(500)
		_sortedByYear.value = false
	}

}