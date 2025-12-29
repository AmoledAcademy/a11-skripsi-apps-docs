package com.unidagontor.franctionapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unidagontor.franctionapp.datastore.DataStoreManager
import com.unidagontor.franctionapp.materipecahan.ujitingkat1.SoalTingkat1
import com.unidagontor.franctionapp.materipecahan.ujitingkat2.SoalTingkat2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class UjiTingkatViewModel : ViewModel() {

    // TimerF
    var timeLeft by mutableStateOf(3600)

    // State soal dan skor
    private val _currentQuestionIndex = mutableStateOf(0)
    val currentQuestionIndex: State<Int> = _currentQuestionIndex

    private val _selectedAnswers = mutableStateMapOf<Int, Int>()
    val selectedAnswers: Map<Int, Int> = _selectedAnswers

    private val _score = mutableStateOf(0)
    val score: State<Int> = _score

    private val _questions = MutableStateFlow<List<UjiQuestion>>(emptyList())
    val questions: StateFlow<List<UjiQuestion>> = _questions

    private var poinPerSoal = 0

    // DataStoreManager
    private var dataStoreManager: DataStoreManager? = null
    fun setDataStore(dataStore: DataStoreManager) {
        this.dataStoreManager = dataStore
    }

    enum class UjiLevel { SATU, DUA }

    fun loadUjiTingkat(level: UjiLevel) {
        val soal = when (level) {
            UjiLevel.SATU -> SoalTingkat1
            UjiLevel.DUA -> SoalTingkat2
        }
        loadUjiTingkatSoal(soal)
    }

    fun loadUjiTingkatSoal(soal: List<UjiQuestion>) {
        _questions.value = soal
        _currentQuestionIndex.value = 0
        _selectedAnswers.clear()
        _score.value = 0
        poinPerSoal = if (soal.isNotEmpty()) 100 / soal.size else 0
    }

    // Simpan jawaban
    fun submitAnswer(answerIndex: Int) {
        val currentIndex = _currentQuestionIndex.value
        val questionList = questions.value

        if (questionList.isEmpty() || currentIndex >= questionList.size) return

        val correctAnswer = questionList[currentIndex].correctAnswerIndex
        val previousAnswer = _selectedAnswers[currentIndex]

        if (previousAnswer == null && answerIndex == correctAnswer) {
            _score.value += poinPerSoal
        }
        if (previousAnswer == correctAnswer && answerIndex != correctAnswer) {
            _score.value -= poinPerSoal
        }
        if (previousAnswer != null && previousAnswer != correctAnswer && answerIndex == correctAnswer) {
            _score.value += poinPerSoal
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

    fun hitungSkor(): Int {
        val soal = questions.value
        var skor = 0

        val poinPerSoalFinal = if (soal.isNotEmpty()) 100 / soal.size else 0

        for ((index, question) in soal.withIndex()) {
            val selected = selectedAnswers[index]
            if (selected == question.correctAnswerIndex) {

                skor += poinPerSoalFinal
            }
        }
        return skor
    }


    fun simpanSkorUjiTingkat(skor: Int, waktu: Int, materiKe: Int, onSaved: (Int) -> Unit) {
        viewModelScope.launch {
            dataStoreManager?.let { dataStore ->
                val user = dataStore.getLastUser()
                user?.let { (nama, kelas) ->

                    dataStore.saveScore(nama, kelas, materiKe, skor)
                    dataStore.saveQuizHistory(nama, kelas, materiKe, skor, waktu)

                    if (skor >= 60) {
                        dataStore.upgradeLevel(nama, kelas, materiKe, "ujian")
                        val currentProgress = dataStore.getProgress(nama, kelas)
                        val updatedProgress = maxOf(currentProgress, materiKe + 1)
                        dataStore.saveProgress(nama, kelas, updatedProgress)
                    }

                    onSaved(skor)
                }
            }
        }
    }

}


