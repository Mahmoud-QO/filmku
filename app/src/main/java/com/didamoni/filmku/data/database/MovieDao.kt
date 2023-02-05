package com.didamoni.filmku.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao
{
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(vararg videos: MovieEntity)

	@Query("SELECT * FROM table_movie")
	fun getAll(): Flow<List<MovieEntity>>

	@Query("DELETE FROM table_movie")
	suspend fun deleteAll()

}