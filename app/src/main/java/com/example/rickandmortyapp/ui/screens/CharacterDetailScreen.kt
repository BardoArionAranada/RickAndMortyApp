package com.example.rickandmortyapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmortyapp.models.CharacterData
import com.example.rickandmortyapp.models.LocationData
import com.example.rickandmortyapp.models.OriginData

@Composable
fun CharacterDetailScreen(character: CharacterData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(character.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = character.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Status: ${character.status}",
            fontSize = 18.sp,
            color = Color.DarkGray
        )
        Text(
            text = "Species: ${character.species}",
            fontSize = 18.sp,
            color = Color.DarkGray
        )
        Text(
            text = "Gender: ${character.gender}",
            fontSize = 18.sp,
            color = Color.DarkGray
        )
        Text(
            text = "Origin: ${character.origin.name}",
            fontSize = 18.sp,
            color = Color.DarkGray
        )
        Text(
            text = "Location: ${character.location.name}",
            fontSize = 18.sp,
            color = Color.DarkGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCharacterDetailScreen() {
    val sampleCharacter = CharacterData(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        origin = OriginData(name = "Earth", url = ""),
        location = LocationData(name = "Earth", url = "")
    )
    CharacterDetailScreen(character = sampleCharacter)
}
