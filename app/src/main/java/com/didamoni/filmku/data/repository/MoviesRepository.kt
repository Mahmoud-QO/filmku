package com.didamoni.filmku.data.repository

import androidx.room.withTransaction
import com.didamoni.filmku.data.database.AppDatabase
import com.didamoni.filmku.data.database.asDomainModel
import com.didamoni.filmku.data.network.IMDbMoviesApi
import com.didamoni.filmku.data.network.asDatabaseModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
	private val moviesApi: IMDbMoviesApi,
	private val database: AppDatabase
) {
	fun getMovies() = networkBoundResource(
		query = { database.movieDao.getAll().map { it.asDomainModel() } },
		fetch = { moviesApi.getTop250Movies() },
		saveFetchResult = { database.withTransaction {
			database.movieDao.deleteAll()
			database.movieDao.insert(*it.asDatabaseModel())
		}}
	)

}
