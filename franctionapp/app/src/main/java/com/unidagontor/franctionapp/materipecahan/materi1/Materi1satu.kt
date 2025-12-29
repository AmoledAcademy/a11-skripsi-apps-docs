package com.unidagontor.franctionapp.materipecahan.materi1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unidagontor.franctionapp.R

@Composable
fun PengenalanPecahan1Screen(
    navController: NavController,
    title: String = "Materi 1",
    subtitle: String = "Pengenalan Pecahan",
    onNextClick: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    val isScrollAtBottom = remember {
        derivedStateOf {
            scrollState.value >= scrollState.maxValue
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4CAF50))
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)

        ) {
            // HEADER
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 20.dp, end = 24.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = subtitle,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.org2),
                    contentDescription = "Ilustrasi Materi",
                    modifier = Modifier.size(50.dp)
                )
            }

            // Konten Materi
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(24.dp)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Red)) {
                                append("Apa ")
                            }
                            withStyle(style = SpanStyle(color = Color.Blue)) {
                                append("itu ")
                            }
                            withStyle(style = SpanStyle(color = Color(0xFF00B050))) {
                                append("pecahan ")
                            }
                            withStyle(style = SpanStyle(color = Color.Red)) {
                                append("?")
                            }
                        },
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Pecahan adalah bagian dari sesuatu yang utuh (seluruhnya).",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                            Image(
                                painter = painterResource(id = R.drawable.cokelat1),
                                contentDescription = "gambar cokelat",
                                modifier = Modifier
                                    .size(350.dp)
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = "Asal kata pecahan",
                                color = Color(0xFF0070C0),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Berasal dari bahasa latin fractio yang berarti memecah menjadi bagian-bagian yang lebih kecil. Sebuah pecahan mempunyai 2 bagian yaitu pembilang dan penyebut.",
                                fontSize = 16.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Pecahan biasanya ditulis seperti ini:",
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "a",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Red
                            )
                            Divider(
                                modifier = Modifier
                                    .width(30.dp)
                                    .padding(vertical = 2.dp),
                                color = Color.Black,
                                thickness = 2.dp
                            )
                            Text(
                                text = "b",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                        }
                    }


                    Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Nah, pecahan punya dua bagian penting:",
                                fontSize = 16.sp
                            )

                            Text(
                                text = "\u2022 Pembilang (a): angka di atas, yang menunjukkan berapa bagian yang kita ambil.",
                                color = Color.Red,
                                fontSize = 16.sp
                            )

                            Text(
                                text = "\u2022 Penyebut (b): angka di bawah, yang menunjukkan semua bagian sama besar dari satu benda utuh.",
                                color = Color.Blue,
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Gambar pizza
                            Text(
                                text = "Contoh:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Image(
                                painter = painterResource(id = R.drawable.pizza),
                                contentDescription = "gambar pizza",
                                modifier = Modifier
                                    .size(350.dp)
                            )


                            Text(
                                text = "Perhatikan Gambar Diatas",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFED7D31)
                            )

                            Text(
                                text = "kamu memiliki satu loyang pizza yang akan dibagi kepada empat orang teman. Agar adil, pizza tersebut harus dipotong menjadi 4 bagian yang sama besar. Masing-masing orang akan menerima 1 potong dari 4 potong keseluruhan pizza. Nah, setiap potongan itu mewakili pecahan 1/4 dari keseluruhan pizza.",
                                fontSize = 18.sp
                            )
                        }
                    }
                }

        // Nav Bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .shadow(8.dp)
                .background(Color.White)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.navigate("daftarMateri") {
                            popUpTo("hasilQuiz") { inclusive = true }
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                    modifier = Modifier
                        .width(140.dp)
                        .height(48.dp)
                ) {
                    Text("Kembali", color = Color.White, fontSize = 16.sp)
                }

                Button(
                    onClick = {
                        navController.navigate("materi1dua")
                    },
                    enabled = isScrollAtBottom.value,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isScrollAtBottom.value) Color(0xFF4CAF50) else Color(0xFF4CAF50).copy(alpha = 0.5f)
                    ),
                    modifier = Modifier
                        .width(140.dp)
                        .height(48.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Lanjut", color = Color.White, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Lanjut",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

