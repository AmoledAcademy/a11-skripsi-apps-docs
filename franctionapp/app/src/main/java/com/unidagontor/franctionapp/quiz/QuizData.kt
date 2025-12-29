package com.unidagontor.franctionapp.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.unidagontor.franctionapp.R
import com.unidagontor.franctionapp.materipecahan.PecahanBiasa

data class QuizQuestion(
    val question: String? = null,
    val options: List<String> = emptyList(),
    val correctAnswerIndex: Int,
    val imageResId: Int? = null,
    val optionContents: List<@Composable () -> Unit>? = null,
    val customQuestionContent: (@Composable (index: Int) -> Unit)? = null
)



val materi1Quiz = listOf(
    QuizQuestion(
        question = "Bentuk pecahan dari gambar yang diarsir berikut adalah….",
        correctAnswerIndex = 3,
        imageResId = R.drawable.soalkesatu,
        optionContents = listOf(
            { PecahanBiasa(2, 3) },
            { PecahanBiasa(2, 4) },
            { PecahanBiasa(2, 5) },
            { PecahanBiasa(2, 6) }//ini
        )
    ),
    QuizQuestion(
        correctAnswerIndex = 2,
        customQuestionContent = { index ->
            Row {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Pecahan ", fontSize = 18.sp)
                PecahanBiasa(3, 5)
                Text(" artinya...", fontSize = 18.sp)
            }
        },
        options = listOf(
            "3 bagian dari 3",
            "5 bagian dari 3",
            "3 bagian dari 5",//ini
            "5 bagian dari 3"
        )
    ),
    QuizQuestion(
        question = "Bilangan berikut yang termasuk pecahan adalah...",
        correctAnswerIndex = 1,
        optionContents = listOf(
            { Text("5") },
            { PecahanBiasa(3, 7) },//ini
            { Text("8 x 2") },
            { Text("20") }
        )
    ),
    QuizQuestion(
        question = "Bentuk pecahan dari gambar yang diarsir berikut adalah….",
        correctAnswerIndex = 3,
        imageResId = R.drawable.soalketiga,
        optionContents = listOf(
            { PecahanBiasa(1, 3) },
            { PecahanBiasa(2, 3) },
            { PecahanBiasa(1, 4) },
            { PecahanBiasa(3, 4) }//ini
        )
    ),
    QuizQuestion(
        question = "Jika sebuah pizza dipotong menjadi 8 bagian dan dimakan 3 bagian, maka bagian yang dimakan adalah...",
        correctAnswerIndex = 0,
        optionContents = listOf(
            { PecahanBiasa(3, 8) },//ini
            { PecahanBiasa(5, 8) },
            { PecahanBiasa(8, 3) },
            { PecahanBiasa(2, 3) }
        )
    )
)



val materi2Quiz = listOf(
    QuizQuestion(
        correctAnswerIndex = 2,
        customQuestionContent = { index ->
            Column {
                Row {
                    Text("${index + 1}. ", fontSize = 18.sp)
                    Text("Dari pecahan berikut, manakah yang lebih besar dari ", fontSize = 18.sp)
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PecahanBiasa(1, 3)
                }
            }
        },
        optionContents = listOf(
            { PecahanBiasa(1, 4) },
            { PecahanBiasa(1, 5) },
            { PecahanBiasa(2, 3) },//ini
            { PecahanBiasa(1, 6) }
        )
    ),

    QuizQuestion(
        question = "Pecahan manakah yang paling kecil?",
        correctAnswerIndex = 1,
        optionContents = listOf(
            { PecahanBiasa(1, 3) },
            { PecahanBiasa(1, 5) },//ini
            { PecahanBiasa(1, 4) },
            { PecahanBiasa(1, 2) }
        )
    ),
    QuizQuestion(
        correctAnswerIndex = 2, // jawaban benar: 1/5, 2/5, 3/5, 4/5
        customQuestionContent = { index ->
            Column {
                Row {
                    Text("${index + 1}. ", fontSize = 18.sp)
                    Text("Urutkan pecahan dari yang terkecil: ", fontSize = 18.sp)
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PecahanBiasa(2, 5)
                    Text(", ", fontSize = 18.sp)
                    PecahanBiasa(4, 5)
                    Text(", ", fontSize = 18.sp)
                    PecahanBiasa(3, 5)
                    Text(", ", fontSize = 18.sp)
                    PecahanBiasa(1, 5)
                }
            }

        },
        optionContents = listOf(
            { // a. 2/5, 4/5, 3/5, 1/5
                Row {
                    PecahanBiasa(2, 5)
                    Text(", ")
                    PecahanBiasa(4, 5)
                    Text(", ")
                    PecahanBiasa(3, 5)
                    Text(", ")
                    PecahanBiasa(1, 5)
                }
            },
            { // b. 1/5, 3/5, 2/5, 4/5
                Row {
                    PecahanBiasa(1, 5)
                    Text(", ")
                    PecahanBiasa(3, 5)
                    Text(", ")
                    PecahanBiasa(2, 5)
                    Text(", ")
                    PecahanBiasa(4, 5)
                }
            },
            { // c. 1/5, 2/5, 3/5, 4/5 <- JAWABAN BENAR
                Row {
                    PecahanBiasa(1, 5)
                    Text(", ")
                    PecahanBiasa(2, 5)
                    Text(", ")
                    PecahanBiasa(3, 5)
                    Text(", ")
                    PecahanBiasa(4, 5)
                }
            },
            { // d. 1/5, 4/5, 3/5, 2/5
                Row {
                    PecahanBiasa(1, 5)
                    Text(", ")
                    PecahanBiasa(4, 5)
                    Text(", ")
                    PecahanBiasa(3, 5)
                    Text(", ")
                    PecahanBiasa(2, 5)
                }
            }
        )
    ),
    QuizQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Column {
                Row {
                    Text("${index + 1}. ", fontSize = 18.sp)
                    Text("Manakah yang Sama Besar (=) dengan ", fontSize = 18.sp)
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PecahanBiasa(3, 6)
                }
            }
        },
        optionContents = listOf(
            { PecahanBiasa(1, 2) },//inni
            { PecahanBiasa(3, 4) },
            { PecahanBiasa(1, 4) },
            { PecahanBiasa(2, 1) }
        )
    ),
    QuizQuestion(
        correctAnswerIndex = 1,
        customQuestionContent = { index ->
            Column {
                Row {
                    Text("${index + 1}. ", fontSize = 18.sp)
                    Text("Dari pecahan berikut manakah yang paling Besar ? ", fontSize = 18.sp)
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PecahanBiasa(1, 8)
                    Text(", ", fontSize = 18.sp)
                    PecahanBiasa(1, 3)
                    Text(", ", fontSize = 18.sp)
                    PecahanBiasa(1, 6)
                    Text(", ", fontSize = 18.sp)
                    PecahanBiasa(1, 5)
                }
            }
        },
        optionContents = listOf(
            { PecahanBiasa(1, 6) },
            { PecahanBiasa(1, 3) },//ini
            { PecahanBiasa(1, 8) },
            { PecahanBiasa(1, 5) }
        )
    )
)
val materi3Quiz = listOf(
    QuizQuestion(
        correctAnswerIndex = 1, // 2/4
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(1, 4)
                Text(" + ", fontSize = 18.sp)
                PecahanBiasa(1, 4)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(1, 8) },
            { PecahanBiasa(2, 4) }, // benar
            { PecahanBiasa(1, 2) },
            { PecahanBiasa(3, 4) }
        )
    ),
    QuizQuestion(
        correctAnswerIndex = 3,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(1, 2)
                Text(" + ", fontSize = 18.sp)
                PecahanBiasa(2, 3)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(2, 5) },
            { PecahanBiasa(3, 5) },
            { PecahanBiasa(1, 6) },
            { PecahanBiasa(7, 6) }//ini
        )
    ),
    QuizQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(3, 8)
                Text(" + ", fontSize = 18.sp)
                PecahanBiasa(2, 8)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(5, 8) },//ini
            { PecahanBiasa(1, 2) },
            { PecahanBiasa(6, 8) },
            { PecahanBiasa(3, 4) }
        )
    ),
    QuizQuestion(
        correctAnswerIndex = 2,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(1, 5)
                Text(" + ", fontSize = 18.sp)
                PecahanBiasa(2, 5)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(2, 10) },
            { PecahanBiasa(3, 10) },
            { PecahanBiasa(3, 5) },//ini
            { PecahanBiasa(1, 2) }
        )
    ),     QuizQuestion(
        correctAnswerIndex = 2,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(2, 3)
                Text(" + ", fontSize = 18.sp)
                PecahanBiasa(1, 6)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(1, 2) },
            { PecahanBiasa(3, 9) },
            { PecahanBiasa(5, 6) },//bnr
            { PecahanBiasa(4, 6) }
        )
    ),)
val materi4Quiz = listOf(
    QuizQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(3, 4)
                Text(" - ", fontSize = 18.sp)
                PecahanBiasa(1, 4)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(2, 4) },//ini
            { PecahanBiasa(1, 2) },
            { PecahanBiasa(3, 8) },
            { PecahanBiasa(1, 4) }
        )
    ),
    QuizQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                Text(" 1 ", fontSize = 18.sp)
                Text(" - ", fontSize = 18.sp)
                PecahanBiasa(1, 2)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(1, 2) },//ini
            { PecahanBiasa(2, 2) },
            { PecahanBiasa(3, 3) },
            { PecahanBiasa(1, 4) }
        )
    ),
    QuizQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(5, 6)
                Text(" - ", fontSize = 18.sp)
                PecahanBiasa(1, 6)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(4, 6) },//ini
            { PecahanBiasa(3, 6) },
            { PecahanBiasa(2, 6) },
            { PecahanBiasa(5, 5) }
        )
    ),
    QuizQuestion(
        correctAnswerIndex = 2,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(3, 5)
                Text(" - ", fontSize = 18.sp)
                PecahanBiasa(2, 5)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(1, 10) },
            { PecahanBiasa(2, 10) },
            { PecahanBiasa(1, 5) },//ini
            { PecahanBiasa(3, 10) }
        )
    ),
    QuizQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(7, 8)
                Text(" - ", fontSize = 18.sp)
                PecahanBiasa(3, 8)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(4, 8) },//ini
            { PecahanBiasa(3, 4) },
            { PecahanBiasa(5, 8) },
            { PecahanBiasa(6, 8) }
        )
    )
 )
