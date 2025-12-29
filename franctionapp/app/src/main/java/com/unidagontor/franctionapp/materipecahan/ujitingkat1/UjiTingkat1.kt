package com.unidagontor.franctionapp.materipecahan.ujitingkat1

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unidagontor.franctionapp.datastore.DataStoreManager
import com.unidagontor.franctionapp.viewmodel.UjiTingkatViewModel
import kotlinx.coroutines.delay


@Composable
fun UjiTingkat1Screen(
    viewModel: UjiTingkatViewModel,
    navController: NavController,
    dataStoreManager: DataStoreManager,
    onQuizFinished: (Int) -> Unit,
    materiKe: Int
) {
    val context = LocalContext.current
    BackHandler(enabled = true) {
        Toast.makeText(context, "Selesaikan Ujian terlebih dahulu!", Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(Unit) {
        viewModel.loadUjiTingkat(UjiTingkatViewModel.UjiLevel.SATU)
        viewModel.setDataStore(dataStoreManager)
    }

    var isLoading by remember { mutableStateOf(false) }
    val questions by viewModel.questions.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex
    val selectedAnswers = viewModel.selectedAnswers
    val selectedOption = remember(currentIndex) { mutableStateOf(selectedAnswers[currentIndex] ?: -1) }
    var showStartPopup by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    if (showStartPopup) {
        CardPopupAwalUjian1(
            onStart = { showStartPopup = false }
        )
        return
    }

    if (questions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
            Text("Memuat soal...", fontSize = 16.sp)
        }
        return
    }

    val currentQuestion = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF5D4037))
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("💡", fontSize = 24.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Uji Tingkat 1",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFC107)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("💡", fontSize = 30.sp)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .border(1.dp, Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                TimerComponent(
                    totalSeconds = 1800,
                    navController = navController,
                    viewModel = viewModel,
                    materiKe = materiKe
                )
            }
        }

        // Konten Pertanyaan
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Pertanyaan
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    if (currentQuestion.customQuestionContent != null) {
                        currentQuestion.customQuestionContent.invoke(currentIndex)
                    } else {
                        Text(
                            text = "${currentIndex + 1}. ${currentQuestion.question}",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    }
                    currentQuestion.imageResId?.let { resId ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 200.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Pilihan Jawaban
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(20.dp))
                        .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                        .padding(16.dp)
                ) {
                    val optionsText = currentQuestion.options
                    val optionsComposable = currentQuestion.optionContents

                    if (!optionsText.isNullOrEmpty()) {
                        optionsText.forEachIndexed { index, option ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .selectable(
                                        selected = selectedOption.value == index,
                                        onClick = {
                                            selectedOption.value = index
                                            viewModel.submitAnswer(index)
                                        }
                                    )
                            ) {
                                RadioButton(selected = selectedOption.value == index, onClick = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(option, fontSize = 14.sp, color = Color.Black)
                            }
                        }
                    } else if (!optionsComposable.isNullOrEmpty()) {
                        optionsComposable.forEachIndexed { index, content ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .selectable(
                                        selected = selectedOption.value == index,
                                        onClick = {
                                            selectedOption.value = index
                                            viewModel.submitAnswer(index)
                                        }
                                    )
                            ) {
                                RadioButton(selected = selectedOption.value == index, onClick = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                content()
                            }
                        }
                    }
                }
            }
        }

        // Nav Bottom

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .windowInsetsPadding(WindowInsets.systemBars)
        ) {
            Button(
                onClick = { viewModel.prevQuestion() },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD32F2F).copy(alpha = if (currentIndex > 0) 1f else 0.5f)
                ),
                enabled = currentIndex > 0
            ) {
                Text("Kembali", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = {
                    if (viewModel.isLastQuestion()) {
                        isLoading = true
                        val durasi = 1800 - viewModel.timeLeft
                        val skorFinal = viewModel.hitungSkor()
                        viewModel.simpanSkorUjiTingkat(skor = skorFinal, waktu = durasi, materiKe = 3) {
                            isLoading = false
                            navController.navigate("hasiluji1/$skorFinal/$durasi/$materiKe") {
                                popUpTo("ujitingkat1") { inclusive = true }
                            }
                        }
                    } else {
                        viewModel.nextQuestion()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RectangleShape,
                enabled = selectedOption.value != -1,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFBC02D),
                    disabledContainerColor = Color(0xFF6F6F6E).copy(alpha = 0.8f)
                )
            ) {
                Text(
                    text = if (viewModel.isLastQuestion()) "Selesai" else "Lanjut",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    // Animasi Loading
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x88000000)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(
                    color = Color(0xFFFBC02D),
                    strokeWidth = 4.dp,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Menghitung Nilai...", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}



@Composable
fun TimerComponent(
    totalSeconds: Int = 1800,
    navController: NavController,
    viewModel: UjiTingkatViewModel,
    materiKe: Int
) {
    var timeLeft by remember { mutableStateOf(totalSeconds) }

    LaunchedEffect(Unit) {
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
            viewModel.timeLeft = timeLeft
        }
    }

    val hours = timeLeft / 3600
    val minutes = (timeLeft % 3600) / 60
    val seconds = timeLeft % 60
    val waktuFormat = remember(timeLeft) {
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }
    Text(
        text = waktuFormat,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}

@Composable
fun CardPopupAwalUjian1(
    onStart: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF5D4037))
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color.White, RoundedCornerShape(24.dp))
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🔔 Persiapan Ujian 🔔",
                fontSize = 25.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF0D47A1)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Kamu akan memulai:",
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "UJIAN TINGKAT 1",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1565C0)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "📋 Terdapat 10 soal yang harus kamu selesaikan\n🕒 Dalam waktu 30 menit",
                fontSize = 17.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE1F5FE), RoundedCornerShape(16.dp))
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = "📘 Materi yang diujikan:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0277BD)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "1. Pengenalan Pecahan",
                        fontSize = 17.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "2. Membandingkan dan\t\tMengurutkan Pecahan",
                        fontSize = 17.sp,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "🔥 Semangat ya!\nkamu bisa menyelesaikannya dengan baik!",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFFD84315),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = onStart,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "🚀 Mulai Ujian Sekarang",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}