package com.unidagontor.franctionapp.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {
    private val firestore = FirebaseFirestore.getInstance()
    private val PROGRESS_KEY = intPreferencesKey("progress")
    private val LAST_USER_KEY = stringPreferencesKey("last_user")
    private val ALL_USERS_KEY = stringPreferencesKey("all_users")

    // Back up function
    private fun scoreKey(n: String, k: String, materi: Int) =
        intPreferencesKey("score_${n}_${k}_materi$materi")
    private fun timeKey(n: String, k: String, materi: Int) =
        intPreferencesKey("time_${n}_${k}_materi$materi")
    private fun historyKey(n: String, k: String) =
        stringSetPreferencesKey("quiz_history_${n}_${k}")
    private fun historyPerMateriKey(n: String, k: String, m: Int) =
        stringSetPreferencesKey("quiz_history_${n}_${k}_materi$m")
    private fun emailKey(n: String, k: String) =
        stringPreferencesKey("email_${n}_${k}")
    private fun sekolahKey(n: String, k: String) =
        stringPreferencesKey("sekolah_${n}_${k}")
    private fun levelKey(n: String, k: String) =
        intPreferencesKey("level_${n}_${k}")
    private fun unlockKey(nama: String, kelas: String, materi: Int) =
        intPreferencesKey("unlocked_${nama}_${kelas}_materi$materi")
    private fun timestampKey(n: String, k: String, materi: Int) =
        stringPreferencesKey("timestamp_${n}_${k}_materi$materi")

    private suspend fun ensureUserDocumentExists(nama: String, kelas: String) {
        val userRef = firestore.collection("users").document("$nama|$kelas")
        userRef.get().await().let {
            if (!it.exists()) {
                userRef.set(mapOf("nama" to nama, "kelas" to kelas)).await()
            }
        }
    }

    //Menyimpan Progres
    suspend fun saveProgress(nama: String, kelas: String, progress: Int) {
        ensureUserDocumentExists(nama, kelas)
        val userRef = firestore.collection("users").document("$nama|$kelas")
        val data = mapOf("progress" to progress)
        userRef.set(data, SetOptions.merge()).await()
        println("Progress disimpan ke Firebase: $progress untuk $nama - $kelas")
    }

    suspend fun getProgress(nama: String, kelas: String): Int {
        val userRef = firestore.collection("users").document("$nama|$kelas")
        return try {
            val snapshot = userRef.get().await()
            snapshot.getLong("progress")?.toInt() ?: 1
        } catch (e: Exception) {
            println("Error getting progress from Firebase: ${e.message}")
            1
        }
    }

    // menyimpan user
    suspend fun saveLastUser(nama: String, kelas: String) {
        val newUser = "$nama|$kelas"
        context.dataStore.edit { prefs ->
            prefs[LAST_USER_KEY] = newUser
        }
        ensureUserDocumentExists(nama, kelas)

        val allUsersRef = firestore.collection("app_metadata").document("users")
        val userSet = firestore.runTransaction { transaction ->
            val snapshot = transaction.get(allUsersRef)
            val existing = snapshot.get("users") as? MutableList<String> ?: mutableListOf()
            if (!existing.contains(newUser)) {
                existing.add(newUser)
            }
            transaction.set(allUsersRef, mapOf("users" to existing))
            existing
        }.await()
        println("Last user and all users updated in Firebase.")
    }

    suspend fun getLastUser(): Pair<String, String>? {
        val prefs = context.dataStore.data.first()
        return prefs[LAST_USER_KEY]?.split("|")?.takeIf { it.size == 2 }?.let {
            it[0] to it[1]
        }
    }

    // Skor

    suspend fun saveScore(nama: String, kelas: String, materiKe: Int, score: Int) {
        ensureUserDocumentExists(nama, kelas)

        val userRef = firestore.collection("users").document("$nama|$kelas")
        userRef.collection("materi").document("materi_$materiKe")
            .set(mapOf("score" to score), SetOptions.merge())
            .await()
    }

    suspend fun setLevel(nama: String, kelas: String, level: Int) {
        ensureUserDocumentExists(nama, kelas)
        val userRef = firestore.collection("users").document("$nama|$kelas")
        userRef.update("level", level).await()
    }

    suspend fun getFinalLevel(nama: String, kelas: String): Int {
        val userRef = firestore.collection("users").document("$nama|$kelas")
        val snapshot = userRef.get().await()
        return snapshot.getLong("level")?.toInt()?.coerceAtMost(7) ?: 1
    }

    suspend fun unlockMateri(nama: String, kelas: String, materi: Int) {
        ensureUserDocumentExists(nama, kelas)

        val userRef = firestore.collection("users").document("$nama|$kelas")
        userRef.collection("materi").document("materi_$materi")
            .set(mapOf("unlocked" to 1), SetOptions.merge())
            .await()
    }

    suspend fun upgradeLevel(nama: String, kelas: String, materiKe: Int, tipe: String) {
        val targetLevel = when {
            tipe == "quiz" && materiKe == 1 -> 2
            tipe == "quiz" && materiKe == 2 -> 3
            tipe == "ujian" && materiKe == 3 -> 4
            tipe == "quiz" && materiKe == 4 -> 5
            tipe == "quiz" && materiKe == 5 -> 6
            tipe == "ujian" && materiKe == 6 -> 7
            else -> null
        }

        targetLevel?.let {
            val currentLevel = getFinalLevel(nama, kelas)

            if (it > currentLevel) {
                setLevel(nama, kelas, it)
                println("Naik level ke $it (tipe: $tipe, materi: $materiKe)")
            } else {
                println("Level tidak naik karena sudah mencapai level $it atau lebih tinggi.")
            }
        }
    }

    // Riwayat Quiz
    suspend fun saveQuizHistory(nama: String, kelas: String, materiKe: Int, skor: Int, waktu: Int) {
        ensureUserDocumentExists(nama, kelas)

        val currentTimestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val historyData = mapOf(
            "skor" to skor,
            "waktu" to waktu,
            "timestamp" to currentTimestamp
        )

        val userRef = firestore.collection("users").document("$nama|$kelas")
        userRef.collection("materi").document("materi_$materiKe")
            .collection("quiz_history").add(historyData)
            .await()
    }

    suspend fun getQuizHistoryForMateri(nama: String, kelas: String, materiKe: Int): List<Triple<Int, Int, String>> {
        val userRef = firestore.collection("users").document("$nama|$kelas")
        return try {
            val querySnapshot = userRef.collection("materi").document("materi_$materiKe")
                .collection("quiz_history")
                .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .get().await()

            querySnapshot.documents.mapNotNull {
                val skor = it.getLong("skor")?.toInt()
                val waktu = it.getLong("waktu")?.toInt()
                val timestamp = it.getString("timestamp")
                if (skor != null && waktu != null && timestamp != null) {
                    Triple(skor, waktu, timestamp)
                } else null
            }
        } catch (e: Exception) {
            println("Error getting quiz history from Firebase: ${e.message}")
            emptyList()
        }
    }

    //isi data email dan sekolah user
    suspend fun saveUserInfo(nama: String, kelas: String, email: String, sekolah: String) {
        ensureUserDocumentExists(nama, kelas)

        val userRef = firestore.collection("users").document("$nama|$kelas")
        val data = mapOf(
            "email" to email,
            "sekolah" to sekolah
        )
        userRef.set(data, SetOptions.merge()).await()
    }

    suspend fun getUserInfo(nama: String, kelas: String): Pair<String, String> {
        val userRef = firestore.collection("users").document("$nama|$kelas")
        return try {
            val snapshot = userRef.get().await()
            val email = snapshot.getString("email") ?: ""
            val sekolah = snapshot.getString("sekolah") ?: ""
            email to sekolah
        } catch (e: Exception) {
            println("Error getting user info from Firebase: ${e.message}")
            "" to ""
        }
    }
    // data semua pengguna
    suspend fun getAllUsers(): List<Pair<String, String>> {
        val allUsersRef = firestore.collection("app_metadata").document("users")
        val snapshot = allUsersRef.get().await()
        val users = snapshot.get("users") as? List<String> ?: emptyList()
        return users.mapNotNull {
            val parts = it.split("|")
            if (parts.size == 2) parts[0] to parts[1] else null
        }
    }

    suspend fun getAllUserDetails(): List<UserDetail> {
        return try {
            val allUsersRef = firestore.collection("app_metadata").document("users")
            val snapshot = allUsersRef.get().await()
            val userIds = snapshot.get("users") as? List<String> ?: emptyList()
            val userDetails = userIds.mapNotNull { userId ->
                val userRef = firestore.collection("users").document(userId)
                val userSnapshot = userRef.get().await()

                if (userSnapshot.exists()) {
                    val (nama, kelas) = userId.split("|").takeIf { it.size == 2 } ?: return@mapNotNull null
                    val email = userSnapshot.getString("email") ?: ""
                    val sekolah = userSnapshot.getString("sekolah") ?: ""
                    UserDetail(nama, kelas, email, sekolah)
                } else {
                    null
                }
            }
            return userDetails
        } catch (e: Exception) {
            println("Error getting all user details from Firebase: ${e.message}")
            emptyList()
        }
    }

    fun unlockNextMateri(materiKe: Int) {
    }
    companion object {
        val LAST_USER_NAMA = stringPreferencesKey("last_user_nama")
        val LAST_USER_KELAS = stringPreferencesKey("last_user_kelas")
    }
    suspend fun clearLastUser() {
        context.dataStore.edit { preferences ->
            preferences.remove(LAST_USER_NAMA)
            preferences.remove(LAST_USER_KELAS)
        }
    }


    data class UserDetail(
        val nama: String,
        val kelas: String,
        val email: String,
        val sekolah: String
    )
}