package com.unidagontor.franctionapp.viewmodel

import androidx.compose.runtime.Composable


data class UjiQuestion(
    val question: String? = null,
    val options: List<String> = emptyList(),
    val correctAnswerIndex: Int,
    val imageResId: Int? = null,
    val optionContents: List<@Composable () -> Unit>? = null,
    val customQuestionContent: (@Composable (index: Int) -> Unit)? = null
)
