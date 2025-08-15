package com.unidagontor.franctionapp.quiz

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unidagontor.franctionapp.R
import com.unidagontor.franctionapp.datastore.DataStoreManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel,
    dataStore: DataStoreManager,
    materiKe: Int
) {
    val context = LocalContext.current
    BackHandler(enabled = true) {
        Toast.makeText(context, "Selesaikan kuis terlebih dahulu!", Toast.LENGTH_SHORT).show()
    }
    val namaMateri = when (materiKe) {
        1 -> "Pengenalan Pecahan"
        2 -> "Membandingkan dan Mengurutkan Pecahan"
        3 -> "Ujitingkat1"
        4 -> "Operasi Penjumlahan Pecahan"
        5 -> "Operasi Pengurangan Pecahan"
        6 -> "Ujitingkat2"
        else -> "Materi Tidak Diketahui"
    }

    var showPopUp by remember { mutableStateOf(true) }

    if (showPopUp) {
        QuizPopUpCard(
            namaMateri = namaMateri,
            onClose = {
                navController.popBackStack()
            },
            onStartQuiz = {
                showPopUp = false
                viewModel.loadQuiz(materiKe)
            }
        )
        return
    }

    val questionIndex by viewModel.currentQuestionIndex
    val questions by viewModel.questions.collectAsState()

    if (questions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Memuat soal...", fontSize = 16.sp)
        }
        return
    }

    val question = questions[questionIndex]

    val selectedOptionFromViewModel = viewModel.selectedAnswers[questionIndex] ?: -1
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFB6D9F3))
                .padding(horizontal = 16.dp, vertical = 5.dp)
                .windowInsetsPadding(WindowInsets.systemBars)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.soalquiz),
                    contentDescription = "Icon Quiz",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(start = 4.dp)
                )

                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            "QUIZ",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        MarqueeText(
                            text = namaMateri,
                            fontSize = 16,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .border(1.dp, Color.Red, shape = RoundedCornerShape(12.dp))
                        .width(70.dp)
                        .padding(horizontal = 4.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    TimerComponent(
                        totalSeconds = 600,
                        navController = navController,
                        viewModel = viewModel,
                        dataStore = dataStore,
                        materiKe = materiKe
                    )
                }
            }
        }

        // Soal & Pilihan
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            if (question.customQuestionContent != null) {
                question.customQuestionContent.invoke(questionIndex)
            } else {
                Text(
                    text = "${questionIndex + 1}. ${question.question ?: ""}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            question.imageResId?.let { resId ->
                Spacer(modifier = Modifier.height(12.dp))
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                question.options?.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .selectable(
                                selected = selectedOptionFromViewModel == index,
                                onClick = {
                                    viewModel.submitJawab(index)
                                }
                            )
                    ) {
                        RadioButton(
                            selected = selectedOptionFromViewModel == index,
                            onClick = {
                                viewModel.submitJawab(index)
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(option, fontSize = 14.sp, color = Color.Black)
                    }
                }

                question.optionContents?.forEachIndexed { index, optionComposable ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .selectable(
                                selected = selectedOptionFromViewModel == index,
                                onClick = {
                                    viewModel.submitJawab(index)
                                }
                            )
                    ) {
                        RadioButton(
                            selected = selectedOptionFromViewModel == index,
                            onClick = {
                                viewModel.submitJawab(index)
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        optionComposable()
                    }
                }
            }
        }

        // Navigasi bawah
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFB6D9F3))
                .windowInsetsPadding(WindowInsets.systemBars)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (questionIndex > 0) {
                Button(
                    onClick = { viewModel.prevQuestion() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF0B2B69)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Kembali", color = Color(0xFF0B2B69), fontSize = 16.sp)
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            Button(
                onClick = {
                    if (viewModel.isLastQuestion()) {
                        val elapsed = 600 - viewModel.timeLeft
                        isLoading = true
                        viewModel.simpanSkor(dataStore, materiKe, elapsed) { skorFinal ->
                            isLoading = false
                            navController.navigate("hasilQuiz/$skorFinal/$elapsed/$materiKe") {
                                popUpTo("materi${materiKe}Quiz") { inclusive = true }
                            }
                        }
                    } else {
                        viewModel.nextQuestion()
                    }
                },
                enabled = selectedOptionFromViewModel != -1,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Text(
                    if (viewModel.isLastQuestion()) "Selesai" else "Lanjut",
                    color = Color(0xFF0B2B69),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Next",
                    tint = Color(0xFF0B2B69)
                )
            }
        }
    }
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x88000000)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(
                    color = Color(0xFF0B2B69),
                    strokeWidth = 4.dp,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Menghitung Nilai...",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun TimerComponent(
    totalSeconds: Int = 600,
    navController: NavController,
    viewModel: QuizViewModel,
    dataStore: DataStoreManager,
    materiKe: Int
) {
    var timeLeft by remember { mutableStateOf(totalSeconds) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            while (timeLeft > 0) {
                delay(1000)
                timeLeft--
                viewModel.timeLeft = timeLeft
            }

            // Aksi saat waktu habis
            val elapsed = totalSeconds
            viewModel.simpanSkor(dataStore, materiKe, elapsed) { skorFinal ->
                navController.navigate("hasilQuiz/$skorFinal/$elapsed/$materiKe") {
                    popUpTo("materi${materiKe}Quiz") { inclusive = true }
                }
            }
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    Text(
        text = String.format("%02d:%02d", minutes, seconds),
        color = Color.Red,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        softWrap = false,
        maxLines = 1
    )
}

@Composable
fun QuizPopUpCard(
    namaMateri: String,
    onClose: () -> Unit,
    onStartQuiz: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB6D9F3))
            .clickable(enabled = false) {}
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.9f) // Mengisi 90% lebar layar
                .padding(20.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "🎉 Perhatian! 🎉",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Color(0xFFDAA520)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Kamu sudah selesai belajar tentang",
                    color = Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    namaMateri,
                    color = Color.Red,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Sekarang waktunya menguji kemampuan kamu! 💪",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "📋 Akan ada 5 soal pilihan ganda\n⏰ Dengan waktu 10 menit",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    "Soalnya seru dan mudah kok,\nanggap seperti main game seru 🎮",
                    fontSize = 16.sp,
                    color = Color(0xFF00796B),
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    "🔥 Semangat ya, kami yakin kamu bisa!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF1976D2),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(30.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Button(
                        onClick = onStartQuiz,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text(text = "Mulai Kuis", color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun MarqueeText(
    text: String,
    fontSize: Int = 16,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val textWidth = remember { mutableStateOf(0) }
    val boxWidth = remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(textWidth.value, boxWidth.value) {
        if (textWidth.value > boxWidth.value) {
            while (true) {
                scrollState.animateScrollTo(scrollState.maxValue)
                delay(1500)
                scrollState.animateScrollTo(0)
                delay(1500)
            }
        } else {
            scrollState.scrollTo(0)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(24.dp)
            .onGloballyPositioned {
                boxWidth.value = it.size.width
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState, enabled = textWidth.value > boxWidth.value)
        ) {
            Text(
                text = text,
                fontSize = fontSize.sp,
                color = textColor,
                softWrap = false,
                maxLines = 1,
                modifier = Modifier.onGloballyPositioned {
                    textWidth.value = it.size.width
                }
            )
        }
    }
}