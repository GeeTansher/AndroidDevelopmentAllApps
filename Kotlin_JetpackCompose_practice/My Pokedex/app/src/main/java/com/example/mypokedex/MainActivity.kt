package com.example.mypokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.mypokedex.ui.theme.MyPokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPokedexTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screens.pokemonListScreen.route
                ){
                    composable(Screens.pokemonListScreen.route){

                    }
                    composable(Screens.pokemonDetailsScreen.route+"/{dominantColor}/{pokemon_name}",
                        arguments = listOf(
                            navArgument("dominant_color"){
                                type == NavType.IntType
                            },
                            navArgument("pokemon_name"){
                                type = NavType.StringType
                            }
                        )
                    ){
                        val dominantColor = remember {
                            val color = it.arguments?.getInt("dominant_color")
                            color?. let { Color(color) } ?: Color.White
                        }

                        val pokemonName = remember {
                            it.arguments?.getString("pokemon_name")
                        }
                    }
                }
            }
        }
    }
}
