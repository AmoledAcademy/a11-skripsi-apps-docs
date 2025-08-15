package com.unidagontor.franctionapp.materipecahan.materi1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.absoluteOffset
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
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.unidagontor.franctionapp.R


@Composable
fun PengenalanPecahan2Screen(
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

            // Konten Materi (Background Putih)
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
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
                                append("Jenis-jenis ")
                            }
                            withStyle(style = SpanStyle(color = Color(0xFF00B050), fontWeight = FontWeight.Bold)) {
                                append("pecahan")
                            }
                            append(":")
                        },
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Daftar jenis pecahan
                    val jenisPecahan = listOf(
                        "Pecahan \u001BBiasa\u001B" to Color(0xFF0070C0),
                        "Pecahan \u001BMurni\u001B" to Color(0xFF7030A0),
                        "Pecahan \u001BSenama\u001B" to Color(0xFFFF0000),
                        "Pecahan \u001BSederhana\u001B" to Color(0xFFFF6600),
                        "Pecahan \u001BCampuran\u001B" to Color(0xFF993300),
                        "Pecahan \u001BEkuivalen\u001B" to Color(0xFF00B0F0),
                        "Pecahan \u001BDesimal\u001B" to Color(0xFF7030A0),
                        "\u001BPersen\u001B atau perSeratus" to Color(0xFF00B050),
                        "Permil atau Perseribu" to Color.Black
                    )

                    jenisPecahan.forEach { (text, color) ->
                        Text(
                            text = "\u25CB $text",
                            fontSize = 16.sp,
                            color = color,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Yuk, belajar pecahan dengan cara yang menyenangkan!",
                        color = Color.Red,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Pecahan bisa juga loh kita pelajari lewat gambar.",
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        buildAnnotatedString {
                            append("Sekarang, ")
                            withStyle(style = SpanStyle(color = Color(0xFF0070C0), fontWeight = FontWeight.Bold)) {
                                append("warnai")
                            }
                            append(" sebagian dari bagian tersebut. Misalnya, kalau kamu mewarnai 1 dari 2 bagian, itu berarti kamu sedang menunjukkan pecahan." +
                                    "Apabila kamu mewarnai sebagiannya, maka warna itu akan menjadi PEMBILANG!")
                        },
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Contoh Visual Gambar:",
                            color = Color.Blue,
                            fontSize = 20.sp

                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        val imageIds = listOf(
                            R.drawable.sadu,
                            R.drawable.sati,
                            R.drawable.duli,
                            R.drawable.patjuh,
                            R.drawable.juluh
                        )

                        imageIds.forEachIndexed { index, imageId ->
                            Image(
                                painter = painterResource(id = imageId),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(500.dp)
                                    .absoluteOffset(y = (index * 150).dp)
                                    .zIndex(index.toFloat())
                            )
                        }
                    }

                    // Spacer agar teks tidak tertimpa gambar
                    Spacer(modifier = Modifier.height(600.dp))

                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Red)) {
                                append("Dengan cara ini")
                            }
                            append(", kamu bisa melihat sendiri bagaimana pecahan itu bekerja. ")
                            withStyle(style = SpanStyle(color = Color(0xFF00B050))) {
                                append("Mudah, bukan?")
                            }
                            append(" Belajar pecahan jadi seru seperti bermain!")
                        },
                        fontSize = 18.sp,
                    )

                }
            }
        }


        //Nav Bottom
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
                        navController.navigate("materi1satu")
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
                        navController.navigate("materi1tiga")
                    },
                    enabled = isScrollAtBottom.value,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isScrollAtBottom.value) Color(0xFF4CAF50) else Color(
                            0xFF4CAF50
                        ).copy(alpha = 0.5f)
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


