package com.example.rickmortyapp.models

import com.google.gson.annotations.SerializedName

data class CharacterListResponse(
    @SerializedName("results") val characters: List<CharacterData>
)

data class CharacterData(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @SerializedName("image") val imageUrl: String,
    val origin: OriginData,
    val location: LocationData
)

data class OriginData(
    val name: String,
    val url: String
)

data class LocationData(
    val name: String,
    val url: String
)
