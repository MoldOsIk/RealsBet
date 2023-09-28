package quizz.sportss.realsbet.question.app.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import quizz.sportss.realsbet.question.app.R

@Composable

fun gamescreen() {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var correctAnswers by remember { mutableStateOf(0) }


    val questions = listOf(
        Question("What is the diameter of a basketball hoop?",
            listOf("a) 40 cm", "b) 45 cm", "c) 50 cm"), "b) 45 cm"),
        Question("What material is used to make a basketball?",
            listOf("a) Leather", "b) Rubber", "c) Plastic"), "a) Leather"),
        Question("How many players are on the court for each team in basketball?",
            listOf("a) 3", "b) 5", "c) 7"), "b) 5"),
        Question("What is the term for passing the ball between players while moving across the court in basketball?",
            listOf("a) Pass", "b) Shot", "c) Dribble"), "a) Pass"),

        Question("How long does one quarter last in professional basketball?",
            listOf("a) 5 minutes", "b) 12 minutes", "c) 10 minutes"), "b) 12 minutes"),

        Question("Which of these players is considered a basketball legend?\n",
            listOf("a) LeBron James", "b) Lionel Messi", "c) Usain Bolt"), "a) LeBron James"),

        Question("In which country do most basketball competitions take place?",
            listOf("a) USA", "b) Russia", "c) China"), "a) USA"),

        Question("What is the official size of a basketball court?",
            listOf("a) 20x40 meters", "b) 28x15 meters", "d) 94x50 feet"), "d) 94x50 feet"),

        Question("What is the term for the area in basketball where players can earn more points than regular shots?",
            listOf("a) Three-point line", "b) Free-throw line", "c) Bonus zone"), "a) Three-point line"),

        Question("Which team has won the most NBA championships?\n",
            listOf("a) Los Angeles Lakers", "b) Boston Celtics", "d) Golden State Warriors"), "b) Boston Celtics"),
    )

    Image(painter = painterResource(id = R.drawable.gamebg), contentDescription = "" ,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)
    if (currentQuestionIndex < questions.size) {
        QuizQuestion(
            question = questions[currentQuestionIndex],
            onAnswerSelected = { selectedAnswer ->
                if (selectedAnswer == questions[currentQuestionIndex].correctAnswer) {
                    correctAnswers++
                }
                currentQuestionIndex++
            }
        )
    } else {
        QuizResult(correctAnswers = correctAnswers, totalQuestions = questions.size)
    }
}