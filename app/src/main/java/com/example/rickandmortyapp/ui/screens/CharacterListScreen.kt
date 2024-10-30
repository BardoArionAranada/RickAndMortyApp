package com.example.rickandmortyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.rickandmortyapp.models.CharacterData
import com.example.rickandmortyapp.models.LocationData
import com.example.rickandmortyapp.models.OriginData

@Composable
fun CharacterListScreen() {
    val characters = remember { mutableStateListOf<CharacterData>() }

    // Aquí se omite la llamada a la API para la previsualización

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(characters.size) { index ->
            CharacterCard(character = characters[index])
        }
    }
}

@Composable
fun CharacterCard(character: CharacterData) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = character.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = character.name,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = character.species,
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterListScreen() {
    val sampleCharacters = listOf(
        CharacterData(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            origin = OriginData(name = "Earth", url = ""),
            location = LocationData(name = "Earth", url = "")
        ),
        CharacterData(
            id = 2,
            name = "Morty Smith",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            origin = OriginData(name = "Earth", url = ""),
            location = LocationData(name = "Earth", url = "")
        )
    )
    Column(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(sampleCharacters.size) { index ->
                CharacterCard(character = sampleCharacters[index])
            }
        }
    }
}
