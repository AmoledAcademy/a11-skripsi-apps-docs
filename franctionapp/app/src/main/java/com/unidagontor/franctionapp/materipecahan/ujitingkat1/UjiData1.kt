package com.unidagontor.franctionapp.materipecahan.ujitingkat1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import com.unidagontor.franctionapp.R
import com.unidagontor.franctionapp.materipecahan.PecahanBiasa
import com.unidagontor.franctionapp.viewmodel.UjiQuestion


val SoalTingkat1 = listOf(
    UjiQuestion(
        question = "Bentuk pecahan dari 5 bagian yang diarsir dari 10 bagian sama besar adalah....",
        correctAnswerIndex = 0,
        optionContents = listOf(
            { PecahanBiasa(5, 10) },//ini
            { PecahanBiasa(10, 5) },
            { PecahanBiasa(2, 10) },
            { PecahanBiasa(1, 5) }
        )
    ),
    UjiQuestion(
        question = "Bentuk pecahan dari gambar yang diarsir berikut adalah….",
        correctAnswerIndex = 0,
        imageResId = R.drawable.soaluji1,
        optionContents = listOf(
            { PecahanBiasa(7, 10) },//ini
            { PecahanBiasa(6, 8) },
            { PecahanBiasa(6, 12) },
            { PecahanBiasa(8, 10) }
        )
    ),
    UjiQuestion(
        correctAnswerIndex = 0,
        customQuestionContent = { index ->
            Column {
                Row {
                    Text("${index + 1}. ", fontSize = 18.sp)
                    Text("Yang merupakan pecahan senilai dengan ", fontSize = 18.sp)

                }
                Row {
                    PecahanBiasa(3, 6)
                    Text("adalah", fontSize = 18.sp)
                }
            }

        },
        optionContents = listOf(
            { PecahanBiasa(1, 2) },//ini
            { PecahanBiasa(1, 3) },
            { PecahanBiasa(2, 4) },
            { PecahanBiasa(1, 6) }
        )
    ),
    UjiQuestion(
        correctAnswerIndex = 1,
        customQuestionContent = { index ->
            Row {
                Text("${index + 1}. ", fontSize = 18.sp)
                Text("Pada pecahan ", fontSize = 18.sp)
                PecahanBiasa(3, 5)
                Text(", angka 5 disebut ", fontSize = 18.sp)
            }
            Row {
                Text("dengan … ", fontSize = 18.sp)
            }
        },
        options = listOf(
            "Bilangan",
            "Penyebut",//ini
            "Pembilang",
            "Pecahan"
        )
    ),
    UjiQuestion(
        correctAnswerIndex = 1,
        customQuestionContent = { index ->

                Row {
                    Text("${index + 1}. ", fontSize = 18.sp)
                    Text("Urutan dari yang terbesar adalah...", fontSize = 18.sp)
            }
        },
        optionContents = listOf(
            {
                Row {
                    PecahanBiasa(1, 6)
                    Text(", ")
                    PecahanBiasa(1, 3)
                    Text(", ")
                    PecahanBiasa(1, 2)
                }
            },
            {
                Row {
                    PecahanBiasa(1, 2) //ini
                    Text(", ")
                    PecahanBiasa(1, 3)
                    Text(", ")
                    PecahanBiasa(1, 6)
                }
            },
            {
                Row {
                    PecahanBiasa(1, 3)
                    Text(", ")
                    PecahanBiasa(1, 6)
                    Text(", ")
                    PecahanBiasa(1, 2)
                }
            },
            {
                Row {
                    PecahanBiasa(1, 3)
                    Text(", ")
                    PecahanBiasa(1, 2)
                    Text(", ")
                    PecahanBiasa(1, 6)
                }
            }
        )
    ),
    UjiQuestion(
        correctAnswerIndex =0,
        customQuestionContent = { index ->
            Column {
                Row {
                    Text("${index + 1}. ", fontSize = 18.sp)
                    Text("Manakah pasangan pecahan yang sama besar ? ", fontSize = 18.sp)
                }
            }
        },
        optionContents = listOf(
            {
                Row {
                    PecahanBiasa(1, 3) //ini
                    Text("dan ")
                    PecahanBiasa(2, 6)
                }
            },
            {
                Row {
                    PecahanBiasa(2, 5)
                    Text("dan ")
                    PecahanBiasa(2, 4)

                }
            },
            {
                Row {
                    PecahanBiasa(3, 4)
                    Text("dan ")
                    PecahanBiasa(2, 3)
                }
            },
            {
                Row {
                    PecahanBiasa(1, 2)
                    Text("dan ")
                    PecahanBiasa(3, 5)

                }
            }
        )
    ),
    UjiQuestion(
        question = "Jika dari 8 bagian kue dimakan 6 bagian, maka SISA kue adalah...",
        correctAnswerIndex =0,
        optionContents = listOf(
            { PecahanBiasa(2, 8) },//ini
            { PecahanBiasa(3, 8) },
            { PecahanBiasa(1, 8) },
            { PecahanBiasa(6, 8) }
        )
    ),
    UjiQuestion(
        question = "Yang bukan pecahan adalah...",
        correctAnswerIndex = 3,
        optionContents = listOf(
            { PecahanBiasa(4, 5) },
            { Text("0,75") },
            { PecahanBiasa(7, 7) },
            { Text("5") }//ini
        )
    ),
    UjiQuestion(
        question = "Lambang pecahan satu per empat dituliskan dengan…",
        correctAnswerIndex = 1,
        optionContents = listOf(
            { PecahanBiasa(2, 4) },
            { PecahanBiasa(1, 4) }, //ini
            { PecahanBiasa(1, 2) },
            { PecahanBiasa(4, 4) }
        )
    ),
    UjiQuestion(
        question = "Bagian pizza yang hilang, jika dituliskan dalam bentuk pecahan menjadi …",
        correctAnswerIndex = 0,
        imageResId = R.drawable.soaluji2,
        optionContents = listOf(
            { PecahanBiasa(1, 4) }, //ini
            { PecahanBiasa(2, 4) },
            { PecahanBiasa(3, 4) },
            { PecahanBiasa(4, 4) }
        )
    ),
)



