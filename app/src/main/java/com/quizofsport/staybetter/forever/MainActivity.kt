package com.quizofsport.staybetter.forever

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quizofsport.staybetter.forever.screens.EndGame
import com.quizofsport.staybetter.forever.screens.SplashScreen
import com.quizofsport.staybetter.forever.screens.gamescreen
import com.quizofsport.staybetter.forever.screens.homescreen
import com.quizofsport.staybetter.forever.screens.pregamescreen
import com.quizofsport.staybetter.forever.ui.theme.SportsRealsbeTheme

val SPLASH = "SPLASH"
val HOMESCREEN = "homescreen"
val RULES = "rules"
val GAME = "game"
val PREGAME = "pregame"
val ENDGAME = "endgame"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val wcontroller = WindowCompat.getInsetsController(window, window.decorView)
        wcontroller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        wcontroller.hide(WindowInsetsCompat.Type.statusBars())
        wcontroller.hide(WindowInsetsCompat.Type.navigationBars())
        wcontroller.hide(WindowInsetsCompat.Type.displayCutout())
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SportsRealsbeTheme {
                val navController = rememberNavController()
                val topice = remember{
                    mutableStateOf(0)
                }
                val balance = remember{
                    mutableStateOf(1000)
                }
                val scoree = remember {
                    mutableStateOf(0)
                }
                val bete = remember{
                    mutableStateOf(0)
                }
                NavHost(navController = navController, startDestination = SPLASH) {

                    composable(SPLASH){
                        SplashScreen {
                            navController.navigate(HOMESCREEN){launchSingleTop=true}
                        }
                    }
                    composable(HOMESCREEN) {
                        homescreen(
                            goPreGame = {navController.navigate(PREGAME){launchSingleTop=true} },
                            navController =navController)
                    }
                    composable(PREGAME) {
                        pregamescreen(goGame = {
                            topic,bet ->
                            topice.value = topic
                            bete.value = bet
                            navController.navigate(GAME){launchSingleTop=true}},
                            balance = balance.value
                            )
                    }
                    composable(GAME) {
                        gamescreen(goPreGame = {navController.navigate(PREGAME){launchSingleTop=true}},
                            topic = topice.value,
                            endGame = {
                                score->
                                scoree.value = score
                                navController.navigate(ENDGAME){launchSingleTop}
                                      },
                            )
                    }
                    composable(ENDGAME) {
                        EndGame(scoree.value, bete.value,
                            goPreGame = {
                                navController.popBackStack(PREGAME, inclusive = false)
                                        },balance, topic = topice.value)
                    }
                    }
                }
            }
        }
    }


