package com.unidagontor.franctionapp.quiz

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unidagontor.franctionapp.datastore.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class QuizViewModel : ViewModel() {
    var timeLeft by mutableStateOf(600)

    private val _currentQuestionIndex = mutableStateOf(0)
    val currentQuestionIndex: State<Int> = _currentQuestionIndex

    private val _selectedAnswers = mutableStateMapOf<Int, Int>()
    val selectedAnswers: Map<Int, Int> = _selectedAnswers

    private val _score = mutableStateOf(0)
    val score: State<Int> = _score

    private val _questions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val questions: StateFlow<List<QuizQuestion>> = _questions

    val bankSoal = mapOf(
        1 to materi1Quiz,
        2 to materi2Quiz,
        4 to materi3Quiz,
        5 to materi4Quiz
    )


    fun loadQuiz(materiKe: Int) {
        println("Loading quiz for materi $materiKe")
        _questions.value = bankSoal[materiKe] ?: emptyList()
        _currentQuestionIndex.value = 0
        _selectedAnswers.clear()
        _score.value = 0
    }


    fun submitJawab(answerIndex: Int) {
        val currentIndex = _currentQuestionIndex.value
        val questionList = questions.value

        if (questionList.isEmpty() || currentIndex >= questionList.size) return

        val alreadyAnswered = _selectedAnswers.containsKey(currentIndex)
        val correctAnswerIndex = questionList[currentIndex].correctAnswerIndex
        if (!alreadyAnswered && answerIndex == correctAnswerIndex) {
            _score.value += 20
        }
        _selectedAnswers[currentIndex] = answerIndex
    }



    fun nextQuestion() {
        if (_currentQuestionIndex.value < questions.value.lastIndex) {
            _currentQuestionIndex.value++
        }
    }

    fun prevQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value--
        }
    }

    fun isLastQuestion(): Boolean = _currentQuestionIndex.value == questions.value.lastIndex

    fun simpanSkor(
        dataStore: DataStoreManager,
        materiKe: Int,
        waktu: Int,
        onSaved: (Int) -> Unit
    ) {
        viewModelScope.launch {
            val user = dataStore.getLastUser()
            user?.let { (nama, kelas) ->
                val skor = _score.value

                dataStore.saveScore(nama, kelas, materiKe, skor)
                dataStore.saveQuizHistory(nama, kelas, materiKe, skor, waktu)

                if (skor >= 80) {
                    dataStore.upgradeLevel(nama, kelas, materiKe, "quiz")

                    val currentProgress = dataStore.getProgress(nama, kelas)
                    val updatedProgress = maxOf(currentProgress, materiKe + 1)
                    dataStore.saveProgress(nama, kelas, updatedProgress)
                }

                onSaved(skor)
            }
        }
    }

}

