package com.example.rickmortyapp.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MortyDataClient {
    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<CharacterResponse>
}
