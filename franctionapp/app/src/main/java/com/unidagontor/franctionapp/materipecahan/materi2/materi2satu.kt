package com.unidagontor.franctionapp.materipecahan.materi2

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
fun BandingUrut1Screen(
    navController: NavController,
    title: String = "Materi 2",
    subtitle: String = "Membandingkan dan Mengurutkan Pecahan",
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 20.dp, end = 24.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
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
                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    painter = painterResource(id = R.drawable.org2),
                    contentDescription = "Ilustrasi Materi",
                    modifier = Modifier.size(50.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(8.dp, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(24.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        Text(
                            text = "Membandingkan",
                            color = Color.Red,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "pecahan",
                            color = Color(0xFF4CAF50),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Gambar pecahan roti
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.banding1),
                            contentDescription = "Deskripsi gambar",
                            modifier = Modifier.size(300.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    RichText()
                }
            }
        }


        // BOTTOM NAVIGATION
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .shadow(8.dp)
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
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
                        navController.navigate("materi2dua")
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

@Composable
fun RichText() {
    val blue = Color(0xFF0080C0)
    val orange = Color(0xFFF39C12)
    val red = Color.Red
    val green = Color(0xFF4CAF50)
    val purple = Color(0xFF6A1B9A)

    Column() {
        // Teks biasa rata kiri
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = blue, fontWeight = FontWeight.Bold)) {
                    append("Membandingkan pecahan ")
                }
                append("artinya menentukan mana pecahan yang lebih besar, lebih kecil, atau sama.\n\n")

                append("Untuk bisa membandingkan dua pecahan, kita biasanya ")
                withStyle(style = SpanStyle(color = orange)) {
                    append("menyamakan penyebutnya ")
                }
                append("terlebih dahulu. Setelah penyebut sama, kita bisa membandingkan pembilangnya.\n\n")

                withStyle(style = SpanStyle(color = red)) {
                    append("Penyebut ")
                }
                append("adalah bilangan di bagian bawah pecahan, sedangkan ")
                withStyle(style = SpanStyle(color = green)) {
                    append("pembilang ")
                }
                append("adalah bilangan di bagian atas.\n\n")
            },
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))


        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = green, fontWeight = FontWeight.Bold)) {
                        append("Tanda Perbandingan Pecahan:\n")
                    }
                    append("  <  (Lebih Kecil dari)\n")
                    append("  >  (Lebih Besar dari)\n")
                    append("  =  (Sama Besar)\n\n")
                },
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = red, fontWeight = FontWeight.Bold)) {
                    append("Nah, ")
                }
                append("bagaimana untuk penjelasannya, apakah kamu sudah paham?! ")

                withStyle(style = SpanStyle(color = purple)) {
                    append("Mari kita gali lagi dengan contoh yang lain\n\n")
                }

                withStyle(style = SpanStyle(color = blue)) {
                    append("Tunggu apa lagi? Ayo ke laman selanjutnya!")
                }
            },
            fontSize = 16.sp
        )
    }
}



