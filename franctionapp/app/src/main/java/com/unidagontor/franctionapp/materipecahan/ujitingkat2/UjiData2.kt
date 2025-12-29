package com.unidagontor.franctionapp.materipecahan.ujitingkat2

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.unidagontor.franctionapp.materipecahan.PecahanBiasa
import com.unidagontor.franctionapp.viewmodel.UjiQuestion


val SoalTingkat2 = listOf(

    //1
    UjiQuestion(
        correctAnswerIndex = 1,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(1, 2)
                Text(" + ", fontSize = 18.sp)
                PecahanBiasa(1, 4)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(2, 4) },
            { PecahanBiasa(3, 4) },//ini
            { PecahanBiasa(1, 6) },
            { PecahanBiasa(5, 4) }
        )
    ),

    //2
    UjiQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(2, 3)
                Text(" - ", fontSize = 18.sp)
                PecahanBiasa(1, 3)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(1 ,3) },//ini
            { PecahanBiasa(2, 2) },
            { PecahanBiasa(2, 6) },
            { PecahanBiasa(3, 6) }
        )
    ),

    //3
    UjiQuestion(
        correctAnswerIndex = 3,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(3, 5)
                Text(" + ", fontSize = 18.sp)
                PecahanBiasa(1, 5)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(5 ,5) },
            { PecahanBiasa(3, 10) },
            { PecahanBiasa(2, 5) },
            { PecahanBiasa(4, 5) }//ini
        )
    ),

    //4
    UjiQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(4, 9)
                Text(" + ", fontSize = 18.sp)
                PecahanBiasa(2, 3)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(10 ,9) },//ini
            { PecahanBiasa(8, 9) },
            { PecahanBiasa(7, 9) },
            { PecahanBiasa(14, 9) }
        )
    ),
    //5
    UjiQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(5, 8)
                Text(" + ", fontSize = 18.sp)
                PecahanBiasa(2, 8)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(7, 8) },//ini
            { PecahanBiasa(6, 8) },
            { PecahanBiasa(5, 16) },
            { PecahanBiasa(3, 4) }
        )
    ),

    //6
    UjiQuestion(
        correctAnswerIndex = 2,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(9, 10)
                Text(" - ", fontSize = 18.sp)
                PecahanBiasa(2, 5)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(7 ,10) },
            { PecahanBiasa(1, 2) },
            { PecahanBiasa(5, 10) },//ini
            { PecahanBiasa(1, 5) }
        )
    ),

    //7
    UjiQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(5, 6)
                Text(" - ", fontSize = 18.sp)
                PecahanBiasa(1, 3)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(3 ,6) },//ini
            { PecahanBiasa(4, 6) },
            { PecahanBiasa(4, 2) },
            { PecahanBiasa(2, 3) }
        )
    ),

    //8
    UjiQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(6, 10)
                Text(" + ", fontSize = 18.sp)
                PecahanBiasa(1, 10)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(7 ,10) },//ini
            { PecahanBiasa(6, 20) },
            { PecahanBiasa(1, 2) },
            { PecahanBiasa(8, 10) }
        )
    ),

    //9
    UjiQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(4, 5)
                Text(" - ", fontSize = 18.sp)
                PecahanBiasa(1, 5)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(3 ,5) },//inmi
            { PecahanBiasa(2, 5) },
            { PecahanBiasa(1, 4) },
            { PecahanBiasa(5, 5) }
        )
    ),

    //10
    UjiQuestion(
        correctAnswerIndex = 1,
        customQuestionContent = { index ->
            Row( verticalAlignment = Alignment.CenterVertically) {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Hasil dari ", fontSize = 18.sp)
                PecahanBiasa(3, 4)
                Text(" + ", fontSize = 18.sp)
                PecahanBiasa(2, 5)
                Text(" adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            { PecahanBiasa(19 ,20) },
            { PecahanBiasa(23, 20) },//ini
            { PecahanBiasa(31, 20) },
            { PecahanBiasa(27, 20) }
        )
    ),

)



