package com.didamoni.filmku.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.didamoni.filmku.data.domain.Movie

@Entity(tableName = "table_movie")
data class MovieEntity(
	@PrimaryKey
	var id: String,

	@ColumnInfo(name = "rank")
	val rank: String,

	@ColumnInfo(name = "title")
	val title: String,

	@ColumnInfo(name = "year")
	val year: String,

	@ColumnInfo(name = "image")
	val image: String,

	@ColumnInfo(name = "rating")
	val imDbRating: String
)

fun List<MovieEntity>.asDomainModel(): List<Movie> = map {
	Movie(
		id = it.id,
		rank = it.rank,
		title = it.title,
		year = it.year,
		image = it.image,
		imDbRating = it.imDbRating
	)
}
