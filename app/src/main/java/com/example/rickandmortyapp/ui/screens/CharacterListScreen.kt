package com.example.rickandmortyapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.rickandmortyapp.services.RickAndMortyApiService

@Composable
fun CharacterListScreen(onCharacterClick: (CharacterData) -> Unit) {
    val characters = remember { mutableStateListOf<CharacterData>() }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Llama a la API cuando se carga la pantalla
    LaunchedEffect(Unit) {
        isLoading = true
        errorMessage = null
        try {
            val response = RickAndMortyApiService.api.getCharacters(1)
            if (response.isSuccessful) {
                response.body()?.characters?.let { characters.addAll(it) }
                isLoading = false
            } else {
                errorMessage = "Error al cargar los personajes"
                isLoading = false
            }
        } catch (e: Exception) {
            errorMessage = "Error de conexión: ${e.message}"
            isLoading = false
        }
    }

    when {
        isLoading -> {
            // Indicador de carga
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        errorMessage != null -> {
            // Mensaje de error
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = errorMessage ?: "Unknown error", color = Color.Red)
            }
        }
        else -> {
            // Pantalla de lista de personajes
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(characters.size) { index ->
                    CharacterCard(character = characters[index], onClick = { onCharacterClick(characters[index]) })
                }
            }
        }
    }
}

@Composable
fun CharacterCard(character: CharacterData, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
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
    CharacterListScreen(onCharacterClick = {})
}
