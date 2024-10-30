package com.example.rickandmortyapp.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.rickandmortyapp.models.CharacterListResponse

interface MortyDataClient {
    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): Response<CharacterListResponse>
}
