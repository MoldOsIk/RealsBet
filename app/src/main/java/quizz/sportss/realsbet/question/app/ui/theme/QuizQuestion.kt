package quizz.sportss.realsbet.question.app.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuizQuestion(
    question: Question,
    onAnswerSelected: (String) -> Unit
)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = question.text, fontSize = 18.sp, fontWeight = FontWeight.Bold)

        question.options.forEach { option ->
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { onAnswerSelected(option) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = option)
            }
        }
    }
}