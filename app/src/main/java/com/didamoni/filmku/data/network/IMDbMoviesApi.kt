package com.didamoni.filmku.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface IMDbMoviesApi
{
	companion object {
		const val BASE_URL = "https://imdb-api.com/"

		// TODO use your api key here
		const val API_KEY = ""
	}

	@GET("API/Top250Movies")
	suspend fun getTop250Movies(
		@Query("apiKey") apiKey: String = API_KEY
	): TopMovies
}
