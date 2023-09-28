package quizz.sportss.realsbet.question.app.ui.theme

import android.content.SharedPreferences
import android.util.Log
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

fun gamescreen(sharedPreferences: SharedPreferences) {

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var correctAnswers by remember { mutableStateOf(0) }

    val imagePainterList = listOf(
        painterResource(id = R.drawable.basketball),
        painterResource(id = R.drawable.footbal),
        painterResource(id = R.drawable.hockey)
    )
    val questionsbasket = listOf(
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
            listOf("a) 20x40 meters", "b) 28x15 meters", "c) 94x50 feet"), "c) 94x50 feet"),

        Question("What is the term for the area in basketball where players can earn more points than regular shots?",
            listOf("a) Three-point line", "b) Free-throw line", "c) Bonus zone"), "a) Three-point line"),

        Question("Which team has won the most NBA championships?\n",
            listOf("a) Los Angeles Lakers", "b) Boston Celtics", "c) Golden State Warriors"), "b) Boston Celtics"),
    )
    val questionsfootbal = listOf(
        Question("How many players are there on a standard football team?",
            listOf("a) 7", "b) 9", "c) 11"), "c) 11"),

        Question("Which country won the FIFA World Cup in 2018?",
            listOf("a) Brazil", "b) France", "c) Germany"), "b) France"),

        Question("What is the term for a penalty awarded to the opposing team when" +
                " a player commits a foul inside their own penalty area in football?",
            listOf("a) Free kick", "b) Goal kick", "c) Penalty kick"), "c) Penalty kick"),

        Question("Who is the all-time top scorer in the history of the FIFA World Cup?",
            listOf("a) Cristiano Ronaldo", "b) Lionel Messi", "c) Miroslav Klose"),
            "c) Miroslav Klose"),

        Question("How long does a standard football match last, excluding injury time?",
            listOf("a) 60 minutes", "b) 75 minutes", "c) 90 minutes"), "c) 90 minutes"),

        Question("Which player is often referred to as \"The Egyptian King\" in football?",
            listOf("a) Lionel Messi", "b) Neymar", "c) Mohamed Salah"), "c) Mohamed Salah"),

        Question("In football, what is the term for a" +
                " playmaker who controls the flow of the team's offensive play?",
            listOf("a) Striker", "b) Defender", "c) Midfielder"), "c) Midfielder"),

        Question("Which international football tournament is contested by teams from Europe?",
            listOf("a) Copa America", "b) African Cup of Nations", "c) UEFA European Championship"),
            "c) UEFA European Championship"),

        Question("What is the term for a situation in football where a" +
                " player scores three goals in a single match?",
            listOf("a) Hat-trick", "b) Penalty kick", "c) Offside"), "a) Hat-trick"),

        Question("Which football club is known as \"The Red Devils\"?",
            listOf("a) Manchester United", "b) FC Barcelona", "c) Bayern Munich"),
            "a) Manchester United"),


    )
    val questionshockey = listOf(
        Question("What instrument is used to strike the puck in hockey?",
            listOf("a) Stick", "b) Ball", "c) Racket"), "a) Stick"),

        Question("What is the position of the player responsible for guarding the goal in hockey?",
            listOf("a) Forward", "b) Defenseman", "c) Goalie"), "c) Goalie"),

        Question("Which hockey tournament is considered the most prestigious in the world?",
            listOf("a) Olympic Games", "b) Stanley Cup", "c) World Championship "),
            "b) Stanley Cup"),

        Question("What type of hockey is played on ice indoors rather than outdoors?",
            listOf("a) Ice hockey", "b) Ice curling", "c) Field hockey"),
            "a) Ice hockey"),

        Question("How many players are typically on the ice for one hockey team?",
            listOf("a) 4", "b) 5", "c) 6"), "c) 6"),

        Question("Which city is considered the birthplace of hockey?",
            listOf("a) Moscow", "b) Toronto", "c) New York"), "b) Toronto"),

        Question("What color is typically used for the hockey puck?",
            listOf("a) White", "b) Black", "c) Red\n"), " b) Black"),

        Question("What is the term for the action in which a hockey player" +
                " tries to score by shooting the puck into the opponent's goal?",
            listOf("a) Pass", "b) Block", "c) Shot"),
            "c) Shot"),

        Question("Which player in hockey wears the letter \"C\" on" +
                " their uniform and is considered the team captain?",
            listOf("a) Goalie", "b) Forward", "c) Defenseman"), "b) Forward"),

        Question("What is the term for the special round in hockey where teams " +
                "play with reduced player strength due to rule violations?",
            listOf("a) Supreme Court", "b) Shootout", "с) Penalty kill"),
            "с) Penalty kill"),
    )
    val answersState = remember{
        mutableStateOf(0)
    }
    val topic = sharedPreferences.getInt("topic", 0)

    Image(painter = painterResource(id = R.drawable.gamebg), contentDescription = "" ,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)

        if (currentQuestionIndex < questionsfootbal.size) {
            QuizQuestion(
                question = questionsfootbal[currentQuestionIndex].text,
                answers = questionsfootbal[currentQuestionIndex].options,
                rightAnswer = questionsfootbal[currentQuestionIndex].correctAnswer,
                answersState = answersState,
                goNext = {currentQuestionIndex++},
                goMenu = {},
                goLose = {}
            )
        } else {
            QuizResult(correctAnswers = correctAnswers, totalQuestions = questionsfootbal.size)
        }


}