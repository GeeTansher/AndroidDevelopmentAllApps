package com.example.mypokedex

sealed class Screens(val route: String){
    object pokemonListScreen: Screens("pokemon_list_screen")
    object pokemonDetailsScreen: Screens("pokemon_details_screen")
}
