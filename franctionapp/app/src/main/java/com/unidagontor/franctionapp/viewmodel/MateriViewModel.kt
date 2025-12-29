package com.unidagontor.franctionapp.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.unidagontor.franctionapp.R
import com.unidagontor.franctionapp.datastore.DataStoreManager
import kotlinx.coroutines.launch

data class Materi(
    val id: Int,
    val title: String,
    val subtitle: String,
    val iconRes: Int,
    val isUnlocked: Boolean,
    val isCurrent: Boolean,
    val isFinished: Boolean,
    val isSedangDiproses: Boolean = false,
    val isSelesai: Boolean = false

)

class MateriViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val dataStore = DataStoreManager(context)

    fun loadUser(nama: String, kelas: String) {
        viewModelScope.launch {
            dataStore.saveLastUser(nama, kelas)
            namaUser = nama
            kelasUser = kelas
            currentProgress = dataStore.getProgress(nama, kelas)
            updateMateriList()
        }
    }
    var currentProgress by mutableStateOf(1)
        private set

    var namaUser by mutableStateOf("")
        private set

    var kelasUser by mutableStateOf("")
        private set


    var materiList = mutableStateListOf<Materi>()
        private set


    fun unlockNext(materiId: Int) {
        if (currentProgress == materiId) {
            currentProgress = materiId + 1
            viewModelScope.launch {
                dataStore.saveProgress(namaUser, kelasUser, currentProgress)
                updateMateriList()
            }
        }
    }

    private fun updateMateriList() {
        materiList.clear()
        val allMateri = listOf(
            "Materi 1"      to "Pengenalan Pecahan",           //id = 1
            "Materi 2"      to "Membandingkan dan Mengurutkan Pecahan",//id = 2
            "Uji Tingkat 1" to "Evaluasi Materi 1 dan 2",      //id = 3
            "Materi 3"      to "Penjumlahan Pecahan",          //id = 4
            "Materi 4"      to "Pengurangan Pecahan",          //id = 5
            "Uji Tingkat 2" to "Evaluasi Materi 3 dan 4"       //id =6
        )
        allMateri.forEachIndexed { index, (title, subtitle) ->
            val id = index + 1
            val isCurrent = currentProgress == id
            val isFinished = currentProgress > id
            val isUnlocked = isCurrent || isFinished
            val icon = when {
                title.contains("Uji", ignoreCase = true) -> R.drawable.ujiicon
                isCurrent -> R.drawable.prosicon
                isFinished -> R.drawable.doneicon
                else -> R.drawable.secicon


            }
            materiList.add(
                Materi(
                    id, title, subtitle, icon,
                    isUnlocked, isCurrent, isFinished,
                    isSedangDiproses = isCurrent,
                    isSelesai = isFinished
                )
            )
        }
    }

    fun selesaiQuiz(nama: String, kelas: String, materiKe: Int) {
        viewModelScope.launch {
            val currentProgress = dataStore.getProgress(nama, kelas)
            if (materiKe == currentProgress) {
                val nextProgress = currentProgress + 1
                dataStore.saveProgress(nama, kelas, nextProgress)
                this@MateriViewModel.currentProgress = nextProgress
                updateMateriList()
            }
        }
    }

}


class LevelkuViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStore = DataStoreManager(application.applicationContext)

    var namaUser = ""
    var kelasUser = ""
    var levelTerbuka by mutableStateOf(1)
        private set

    fun loadProgress() {
        viewModelScope.launch {
            dataStore.getLastUser()?.let { (nama, kelas) ->
                println(" Last user: $nama - $kelas")
                levelTerbuka = dataStore.getFinalLevel(nama, kelas)
                println(" Level Terbuka: $levelTerbuka")
            } ?: println(" Last user not found")
        }
    }

}

class LevelkuViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LevelkuViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LevelkuViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


