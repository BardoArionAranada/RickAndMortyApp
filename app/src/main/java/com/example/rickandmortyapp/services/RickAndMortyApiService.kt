package com.example.rickandmortyapp.services

import com.example.rickandmortyapp.models.CharacterListResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Definimos la interfaz para los métodos de la API
interface RickAndMortyApi {
    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<CharacterListResponse>
}

// Singleton para Retrofit
object RickAndMortyApiService {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    val api: RickAndMortyApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }
}
