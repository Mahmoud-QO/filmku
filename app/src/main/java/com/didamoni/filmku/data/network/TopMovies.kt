package com.didamoni.filmku.data.network

import com.didamoni.filmku.data.database.MovieEntity
import com.didamoni.filmku.data.domain.Movie

data class TopMovies(
	val items: List<Movie>,
	val errorMessage: String
)

fun TopMovies.asDatabaseModel(): Array<MovieEntity> = items.map {
	MovieEntity(
		id = it.id,
		rank = it.rank,
		title = it.title,
		year = it.year,
		image = it.image,
		imDbRating = it.imDbRating
	)
}.toTypedArray()

