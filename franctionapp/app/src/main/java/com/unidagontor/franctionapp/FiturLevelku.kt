package com.unidagontor.franctionapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unidagontor.franctionapp.viewmodel.LevelkuViewModel

@Composable
fun LevelkuScreen(
    navController: NavController,
    viewModel: LevelkuViewModel
) {
    val scrollState = rememberScrollState()
    val currentLevel by remember { derivedStateOf { viewModel.levelTerbuka } }

    LaunchedEffect(Unit) {
        viewModel.loadProgress()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF8ED8F8))
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // HEADER
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.levicon),
                        contentDescription = "Logo Target",
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Levelku",
                        style = MaterialTheme.typography.displayLarge,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2266AA)
                    )
                }
            }

            // KONTEN UTAMA
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 80.dp)
                        .padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF8ED8F8), RoundedCornerShape(15.dp))
                            .padding(vertical = 30.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Level $currentLevel",
                            fontSize = 60.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Riwayat Nilai Anda",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Daftar riwayat nilai quiz
                    val items = listOf(
                        "Quiz Materi 1" to 1,
                        "Quiz Materi 2" to 2,
                        "Ujian Tingkat 1" to 3,
                        "Quiz Materi 3" to 4,
                        "Quiz Materi 4" to 5,
                        "Ujian Tingkat 2" to 6
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(scrollState)
                    ) {
                        items.forEach { (label, materiKe) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                                    .background(Color(0xFFE0F4FA), RoundedCornerShape(20.dp))
                                    .padding(horizontal = 12.dp, vertical = 20.dp)
                                    .clickable {
                                        navController.navigate("rapot/$materiKe")
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = label, fontSize = 18.sp, color = Color.Black)
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "Detail",
                                    tint = Color(0xFF33AADD)
                                )
                            }
                        }
                    }
                }
            }
        }

        // TOMBOL KEMBALI
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .shadow(10.dp)
                .background(Color.White)
                .padding(16.dp)
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            Button(
                onClick = {
                    navController.navigate("homeScreen")
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2266AA)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Kembali", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}