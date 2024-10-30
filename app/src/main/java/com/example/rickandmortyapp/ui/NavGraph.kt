package com.example.rickandmortyapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortyapp.models.CharacterData
import com.example.rickandmortyapp.models.LocationData
import com.example.rickandmortyapp.models.OriginData
import com.example.rickandmortyapp.ui.screens.CharacterDetailScreen
import com.example.rickandmortyapp.ui.screens.CharacterListScreen

sealed class Screen(val route: String) {
    object CharacterList : Screen("character_list")
    object CharacterDetail : Screen("character_detail/{characterId}") {
        fun createRoute(characterId: Int) = "character_detail/$characterId"
    }
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.CharacterList.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.CharacterList.route) {
            CharacterListScreen(onCharacterClick = { character ->
                navController.navigate(Screen.CharacterDetail.createRoute(character.id))
            })
        }
        composable(Screen.CharacterDetail.route) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
            val sampleCharacter = CharacterData(
                id = characterId ?: 1,
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
    }
}
