package quizz.sportss.realsbet.question.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("1", Context.MODE_PRIVATE)
        val wcontroller = WindowCompat.getInsetsController(window, window.decorView)
        wcontroller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        wcontroller.hide(WindowInsetsCompat.Type.statusBars())
        wcontroller.hide(WindowInsetsCompat.Type.navigationBars())
        wcontroller.hide(WindowInsetsCompat.Type.displayCutout())

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SportsRealsbeTheme {
                val imageResourceIds = listOf(
                    painterResource(id = R.drawable.footbal),
                    painterResource(id = R.drawable.footbal),
                    painterResource(id = R.drawable.footbal),

                )
                val navController = rememberNavController()
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
                        pregamescreen(goGame = {navController.navigate(GAME)},
                            sharedPreferences )
                    }
                    composable(GAME) {
                        gamescreen(sharedPreferences)
                    }
                }
            }
        }
    }
}

