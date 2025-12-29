package com.unidagontor.franctionapp

import Routes
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.unidagontor.franctionapp.datastore.DataStoreManager
import com.unidagontor.franctionapp.fiturmulaibelajar.DaftarMateriScreen
import com.unidagontor.franctionapp.materipecahan.materi1.PengenalanPecahan1Screen
import com.unidagontor.franctionapp.materipecahan.materi1.PengenalanPecahan2Screen
import com.unidagontor.franctionapp.materipecahan.materi1.PengenalanPecahan3Screen
import com.unidagontor.franctionapp.materipecahan.materi2.BandingUrut1Screen
import com.unidagontor.franctionapp.materipecahan.materi2.BandingUrut2Screen
import com.unidagontor.franctionapp.materipecahan.materi2.BandingUrut3Screen
import com.unidagontor.franctionapp.materipecahan.materi2.BandingUrut4Screen
import com.unidagontor.franctionapp.materipecahan.materi3.Penjumlahan1Screen
import com.unidagontor.franctionapp.materipecahan.materi3.Penjumlahan2Screen
import com.unidagontor.franctionapp.materipecahan.materi4.Pengurangan1Screen
import com.unidagontor.franctionapp.materipecahan.materi4.Pengurangan2Screen
import com.unidagontor.franctionapp.materipecahan.ujitingkat1.UjiTingkat1ResultScreen
import com.unidagontor.franctionapp.materipecahan.ujitingkat1.UjiTingkat1Screen
import com.unidagontor.franctionapp.materipecahan.ujitingkat2.UjiTingkat2ResultScreen
import com.unidagontor.franctionapp.materipecahan.ujitingkat2.UjiTingkat2Screen
import com.unidagontor.franctionapp.quiz.HasilQuizScreen
import com.unidagontor.franctionapp.quiz.QuizScreen
import com.unidagontor.franctionapp.quiz.QuizViewModel
import com.unidagontor.franctionapp.rapotnilai.RapotScreen
import com.unidagontor.franctionapp.viewmodel.LevelkuViewModel
import com.unidagontor.franctionapp.viewmodel.LevelkuViewModelFactory
import com.unidagontor.franctionapp.viewmodel.MateriViewModel
import com.unidagontor.franctionapp.viewmodel.MateriViewModelFactory
import com.unidagontor.franctionapp.viewmodel.UjiTingkatViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AppNavHost(
    navController: NavHostController,
    materiViewModel: MateriViewModel
) {
    NavHost(navController = navController, startDestination = Routes.Splash) {


        composable(Routes.Splash) {
            SplashScreen(navController)
        }
        composable(Routes.Second) {
            SecondScreen(onNextClick = {
                navController.navigate(Routes.Register)
            })
        }
        composable(Routes.Register) {
            RegisScreen(navController, materiViewModel)
        }
        composable(Routes.DaftarMateri) {
            DaftarMateriScreen(navController, materiViewModel)
        }
        composable(Routes.home) {
            val context = LocalContext.current
            val application = context.applicationContext as Application
            val viewModel: LevelkuViewModel = viewModel(
                factory = LevelkuViewModelFactory(application)
            )
            HomeActivity(navController, viewModel = viewModel)
        }
        composable(Routes.tentang) {
            TentangScreen(navController)
        }
        composable("levelku") {
            val context = LocalContext.current
            val application = context.applicationContext as Application
            val viewModel: LevelkuViewModel = viewModel(
                factory = LevelkuViewModelFactory(application)
            )
            LaunchedEffect(Unit) { viewModel.loadProgress() }
            LevelkuScreen(navController, viewModel)
        }
        composable(Routes.profile) {
            ProfileScreen(navController)
        }
        composable(Routes.keluar) {
            KeluarActivity(navController)
        }

        //Materi 1
        composable("materi1satu") { PengenalanPecahan1Screen(navController) }
        composable("materi1dua") { PengenalanPecahan2Screen(navController) }
        composable("materi1tiga") { PengenalanPecahan3Screen(navController) }

        //Materi 2
        composable("materi2satu") { BandingUrut1Screen(navController) }
        composable("materi2dua") { BandingUrut2Screen(navController) }
        composable("materi2tiga") { BandingUrut3Screen(navController) }
        composable("materi2empat") { BandingUrut4Screen(navController) }

        //Materi 3
        composable("materi3satu") { Penjumlahan1Screen(navController) }
        composable("materi3dua") { Penjumlahan2Screen(navController) }

        //Materi 4
        composable("materi4satu") { Pengurangan1Screen(navController) }
        composable("materi4dua") { Pengurangan2Screen(navController) }

        //Quiz
        composable("materi1Quiz") {
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val quizViewModel: QuizViewModel = viewModel()
            QuizScreen(navController, quizViewModel, dataStore, materiKe = 1)
        }
        composable("materi2Quiz") {
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val quizViewModel: QuizViewModel = viewModel()
            QuizScreen(navController, quizViewModel, dataStore, materiKe = 2)
        }
        composable("materi3Quiz") {
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val quizViewModel: QuizViewModel = viewModel()
            QuizScreen(navController, quizViewModel, dataStore, materiKe = 4)
        }
        composable("materi4Quiz") {
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val quizViewModel: QuizViewModel = viewModel()
            QuizScreen(navController, quizViewModel, dataStore, materiKe = 5)
        }

        // Hasil Quiz

            composable("hasilQuiz/{skor}/{waktu}/{materiKe}") { backStack ->
                val skor = backStack.arguments?.getString("skor")?.toInt() ?: 0
                val waktu = backStack.arguments?.getString("waktu")?.toInt() ?: 0
                val materiKe = backStack.arguments?.getString("materiKe")?.toInt() ?: 1
                val coroutineScope = rememberCoroutineScope()
                val context = LocalContext.current
                val dataStore = remember { DataStoreManager(context) }
                val materiViewModel: MateriViewModel = viewModel(
                    factory = MateriViewModelFactory(context.applicationContext as Application)
                )

                HasilQuizScreen(
                    score = skor,
                    elapsedTime = waktu,
                    onNextClick = {
                        if (skor >= 80) {
                            coroutineScope.launch {
                                val user = dataStore.getLastUser()
                                user?.let { (nama, kelas) ->
                                    materiViewModel.selesaiQuiz(nama, kelas, materiKe)
                                    dataStore.upgradeLevel(nama, kelas, materiKe, "quiz")
                                }
                                val nextMateriScreen = when (materiKe + 1) {
                                    2 -> "materi2satu"
                                    3 -> "ujitingkat1"
                                    4 -> "materi3satu"
                                    5 -> "materi4satu"
                                    6 -> "ujitingkat2"
                                    else -> "daftarMateri"
                                }
                                navController.navigate(nextMateriScreen)
                            }
                        } else {
                            val screen = when (materiKe) {
                                1 -> "materi1satu"
                                2 -> "materi2satu"
                                4 -> "materi3satu"
                                5 -> "materi4satu"
                                else -> "daftarMateri"
                            }
                            navController.navigate(screen)
                        }
                    },
                    onUlangMateriClick = {
                        navController.navigate("materi${materiKe}Quiz")
                    },
                    onMainMenuClick = {
                        navController.navigate("daftarMateri") {
                            popUpTo("hasilQuiz") { inclusive = true }
                        }
                    }
                )
            }

        // UjiTingkat1
        composable("ujitingkat1") {
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val viewModel: UjiTingkatViewModel = viewModel()

            LaunchedEffect(Unit) {
                viewModel.setDataStore(dataStore)
                viewModel.loadUjiTingkat(UjiTingkatViewModel.UjiLevel.SATU)
            }

            UjiTingkat1Screen(
                navController = navController,
                viewModel = viewModel,
                dataStoreManager = dataStore,
                materiKe = 3,
                onQuizFinished = { skorFinal ->
                    val elapsed = 1800 - viewModel.timeLeft
                    navController.navigate("hasiluji1/$skorFinal/$elapsed/3") {
                        popUpTo("ujitingkat1") { inclusive = true }
                    }
                }
            )
        }

        composable("hasiluji1/{skor}/{waktu}/{materiKe}") { backStackEntry ->
            val skor = backStackEntry.arguments?.getString("skor")?.toIntOrNull() ?: 0
            val waktu = backStackEntry.arguments?.getString("waktu")?.toIntOrNull() ?: 0
            val materiKe = backStackEntry.arguments?.getString("materiKe")?.toIntOrNull() ?: 3

            val context = LocalContext.current
            val dataStoreManager = remember { DataStoreManager(context) }
            val viewModel: UjiTingkatViewModel = viewModel()
            val materiViewModel: MateriViewModel = viewModel(
                factory = MateriViewModelFactory(context.applicationContext as Application)
            )

            val isPassed = skor >= 70
            val sudahDiproses = remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                if (!sudahDiproses.value) {
                    val user = dataStoreManager.getLastUser()
                    user?.let { (nama, kelas) ->
                        if (isPassed) {
                            val currentLevel = dataStoreManager.getFinalLevel(nama, kelas)
                            if (currentLevel < 4) {
                                dataStoreManager.saveProgress(nama, kelas, 4)
                                dataStoreManager.upgradeLevel(nama, kelas, 3, "ujian")
                            }
                            materiViewModel.selesaiQuiz(nama, kelas, 3)
                        } else {
                            dataStoreManager.saveQuizHistory(nama, kelas, materiKe, skor, waktu)
                            dataStoreManager.saveScore(nama, kelas, materiKe, skor)
                        }
                    }
                    sudahDiproses.value = true
                }
            }

            UjiTingkat1ResultScreen(
                score = skor,
                elapsedTime = waktu,
                materiKe = materiKe,
                dataStoreManager = dataStoreManager,
                viewModel = viewModel,
                onBackToHome = {
                    navController.navigate("daftarmateri") {
                        popUpTo("daftarmateri") { inclusive = true }
                    }
                },
                onUlangUji = {
                    navController.navigate("ujitingkat1") {
                        popUpTo("ujitingkat1") { inclusive = true }
                    }
                },
                onNextBelajar = {
                    navController.navigate("materi3satu")
                }
            )
        }

        // UjiTingkat2
        composable("ujitingkat2") {
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val viewModel: UjiTingkatViewModel = viewModel()

            LaunchedEffect(Unit) {
                viewModel.setDataStore(dataStore)
                viewModel.loadUjiTingkat(UjiTingkatViewModel.UjiLevel.DUA)
            }

            UjiTingkat2Screen(
                navController = navController,
                viewModel = viewModel,
                dataStoreManager = dataStore,
                materiKe = 6,
                onQuizFinished = { skorFinal ->
                    val elapsed = 1800 - viewModel.timeLeft
                    navController.navigate("hasiluji2/$skorFinal/$elapsed/6") {
                        popUpTo("ujitingkat2") { inclusive = true }
                    }
                }
            )
        }

        composable("hasiluji2/{skor}/{waktu}/{materiKe}") { backStackEntry ->
            val skor = backStackEntry.arguments?.getString("skor")?.toIntOrNull() ?: 0
            val waktu = backStackEntry.arguments?.getString("waktu")?.toIntOrNull() ?: 0
            val materiKe = backStackEntry.arguments?.getString("materiKe")?.toIntOrNull() ?: 6

            val context = LocalContext.current
            val dataStoreManager = remember { DataStoreManager(context) }
            val viewModel: UjiTingkatViewModel = viewModel()
            val materiViewModel: MateriViewModel = viewModel(
                factory = MateriViewModelFactory(context.applicationContext as Application)
            )

            val isPassed = skor >= 70
            val sudahDiproses = remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                if (!sudahDiproses.value) {
                    val user = dataStoreManager.getLastUser()
                    user?.let { (nama, kelas) ->
                        if (isPassed) {
                            dataStoreManager.saveProgress(nama, kelas, 6)
                            dataStoreManager.upgradeLevel(nama, kelas, 7, "ujian")
                            materiViewModel.selesaiQuiz(nama, kelas, 6)
                        } else {
                            dataStoreManager.saveQuizHistory(nama, kelas, materiKe, skor, waktu)
                            dataStoreManager.saveScore(nama, kelas, materiKe, skor)
                        }
                    }
                    sudahDiproses.value = true
                }
            }

            UjiTingkat2ResultScreen(
                score = skor,
                elapsedTime = waktu,
                materiKe = materiKe,
                dataStoreManager = dataStoreManager,
                viewModel = viewModel,
                onBackToHome = {
                    navController.navigate("levelku") {
                        popUpTo("levelku") { inclusive = true }
                    }
                },
                onUlangUji = {
                    navController.navigate("ujitingkat2") {
                        popUpTo("ujitingkat2") { inclusive = true }
                    }
                }
            )
        }


        // Riwayat Nilai
        composable("rapot/{materiKe}") { backStackEntry ->
            val context = LocalContext.current
            val dataStore = remember { DataStoreManager(context) }
            val materiKe = backStackEntry.arguments?.getString("materiKe")?.toIntOrNull() ?: 1
            var filteredHistory by remember { mutableStateOf<List<Triple<Int, Int, String>>>(emptyList()) }

            val namaMateri = when (materiKe) {
                1 -> "Quiz Pengenalan Pecahan"
                2 -> "Quiz Membandingkan dan Mengurutkan Pecahan"
                3 -> "Ujian Tingkat 1"
                4 -> "Quiz Penjumlahan Pecahan"
                5 -> "Quiz Pengurangan Pecahan"
                6 -> "Ujian Tingkat 2"
                else -> "Materi Tidak Dikenal"
            }

            LaunchedEffect(Unit) {
                val user = dataStore.getLastUser()
                user?.let { (nama, kelas) ->
                    filteredHistory = dataStore.getQuizHistoryForMateri(nama, kelas, materiKe)
                }
            }

            RapotScreen(
                namaMateri = namaMateri,
                history = filteredHistory,
                materiKe = materiKe,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

//Internet
fun checkInternet(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        val networkInfo = connectivityManager.activeNetworkInfo ?: return false
        return networkInfo.isConnected
    }
}

@Composable
fun CekKoneksiWrapper(content: @Composable () -> Unit) {
    val context = LocalContext.current
    var isOnline by remember { mutableStateOf(checkInternet(context)) }
    val coroutineScope = rememberCoroutineScope()
    var isChecking by remember { mutableStateOf(false) }

    fun refreshConnection() {
        isChecking = true
        coroutineScope.launch {
            delay(500)
            isOnline = checkInternet(context)
            isChecking = false
        }
    }

    LaunchedEffect(Unit) {
        refreshConnection()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (isOnline) {
            content()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Tidak ada koneksi internet",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Aplikasi ini membutuhkan internet untuk berjalan.",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { refreshConnection() },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5D5FEF)),
                    shape = RoundedCornerShape(12.dp),
                    enabled = !isChecking
                ) {
                    Text("Refresh", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}
