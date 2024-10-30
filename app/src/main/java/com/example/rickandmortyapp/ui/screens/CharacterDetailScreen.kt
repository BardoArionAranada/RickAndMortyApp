package com.example.rickandmortyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.rickandmortyapp.models.CharacterData
import com.example.rickandmortyapp.models.LocationData
import com.example.rickandmortyapp.models.OriginData

@Composable
fun CharacterDetailScreen(character: CharacterData, episodes: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen y nombre del personaje
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
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Sección de Estado y Especie
        DetailRow(label = "Status", value = character.status)
        DetailRow(label = "Species", value = character.species)
        DetailRow(label = "Gender", value = character.gender)

        Spacer(modifier = Modifier.height(12.dp))

        // Ubicación y Origen
        DetailRow(label = "Origin", value = character.origin.name)
        DetailRow(label = "Location", value = "${character.location.name} (Dimension: ${character.location.dimension})")

        Spacer(modifier = Modifier.height(12.dp))

        // Episodios
        Text(
            text = "Episodes:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(episodes) { episode ->
                Text(
                    text = episode,
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            textAlign = TextAlign.End
        )
    }
}
