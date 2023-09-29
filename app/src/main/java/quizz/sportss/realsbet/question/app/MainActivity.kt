package quizz.sportss.realsbet.question.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import quizz.sportss.realsbet.question.app.ui.theme.EndGame
import quizz.sportss.realsbet.question.app.ui.Privacy_screen
import quizz.sportss.realsbet.question.app.ui.theme.SportsRealsbeTheme
import quizz.sportss.realsbet.question.app.ui.theme.gamescreen
import quizz.sportss.realsbet.question.app.ui.theme.homescreen
import quizz.sportss.realsbet.question.app.ui.theme.pregamescreen

val HOMESCREEN = "homescreen"
val RULES = "rules"
val POLICY = "policy"
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
                NavHost(navController = navController, startDestination = HOMESCREEN) {

                    composable(HOMESCREEN) {
                        homescreen(
                            goPreGame = {navController.navigate(PREGAME){launchSingleTop=true} },
                            goRules = {navController.navigate(RULES){launchSingleTop=true}},
                            goPolicy = {navController.navigate(POLICY){launchSingleTop=true}},
                            navController)
                    }
                    composable(POLICY) {
                        Privacy_screen()
                    }
                    composable(PREGAME) {
                        pregamescreen(goGame = {
                            topic,bet ->
                            topice.value = topic
                            bete.value = bet
                            navController.navigate(GAME)},
                            balance = balance.value
                            )
                    }
                    composable(GAME) {
                        gamescreen(goPreGame = {navController.navigate(PREGAME){launchSingleTop=true}},
                            topic = topice.value,
                            EndGame = {
                                score->
                                scoree.value = score
                                navController.navigate(ENDGAME)
                                      },
                            )
                    }
                    composable(ENDGAME) {
                        EndGame(scoree.value, bete.value,
                            goPreGame = {
                                navController.popBackStack()
                                navController.popBackStack()
                                    //navController.navigate(PREGAME){launchSingleTop=true}
                                        },balance, topic = topice.value)}
                    }
                }
            }
        }
    }


