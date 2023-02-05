package com.didamoni.filmku.di

import android.app.Application
import androidx.room.Room
import com.didamoni.filmku.data.database.AppDatabase
import com.didamoni.filmku.data.database.MovieDao
import com.didamoni.filmku.data.network.IMDbMoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{
	@Provides
	@Singleton
	fun provideIMDbRetrofit(): Retrofit =
		Retrofit.Builder()
			.baseUrl(IMDbMoviesApi.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()

	@Provides
	@Singleton
	fun provideIMDbMoviesApi(retrofit: Retrofit): IMDbMoviesApi =
		retrofit.create(IMDbMoviesApi::class.java)

	@Provides
	@Singleton
	fun provideDatabase(app: Application): AppDatabase =
		Room.databaseBuilder(
			app,
			AppDatabase::class.java,
			"database_app"
		).fallbackToDestructiveMigration().build()

	@Provides
	@Singleton
	fun provideMovieDao(database: AppDatabase): MovieDao =
		database.movieDao
}
