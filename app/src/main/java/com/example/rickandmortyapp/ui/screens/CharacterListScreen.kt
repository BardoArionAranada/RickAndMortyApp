package com.example.rickandmortyapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickandmortyapp.models.CharacterData

@Composable
fun CharacterListScreen(
    onCharacterClick: (CharacterData) -> Unit,
    characters: List<CharacterData>
) {
    var selectedStatus by remember { mutableStateOf("All") }
    val statuses = listOf("All", "Alive", "Dead", "unknown")

    Column {
        // Filtro de estado
        Text(
            text = "Filter by Status:",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(8.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            statuses.forEach { status ->
                FilterChip(
                    text = status,
                    selected = selectedStatus == status,
                    onClick = { selectedStatus = status }
                )
            }
        }

        // Lista de personajes
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(characters.filter { selectedStatus == "All" || it.status == selectedStatus }) { character ->
                CharacterCard(character = character, onClick = { onCharacterClick(character) })
            }
        }
    }
}

@Composable
fun FilterChip(text: String, selected: Boolean, onClick: () -> Unit) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = if (selected) MaterialTheme.colorScheme.primary else Color.Gray,
        modifier = Modifier
            .padding(4.dp)
            .clickable { onClick() }
            .background(
                color = if (selected) MaterialTheme.colorScheme.secondary else Color.LightGray,
                shape = MaterialTheme.shapes.small
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        textAlign = TextAlign.Center,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    )
}
