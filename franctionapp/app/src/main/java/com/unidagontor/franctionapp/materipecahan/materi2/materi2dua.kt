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
import com.unidagontor.franctionapp.materipecahan.PecahanBiasa

@Composable
fun BandingUrut2Screen(
    navController: NavController,
    title: String = "Materi 2",
    subtitle: String = "Membandingkan dan Mengurutkan Pecahan",
    onNextClick: () -> Unit = {}
) {
    val green = Color(0xFF4CAF50)
    val black = Color.Black
    val red = Color.Red
    val blue = Color(0xFF1565C0)
    val yellow = Color(0xFFFBC02D)
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
                    Text(
                        buildAnnotatedString {
                            withStyle(SpanStyle(color = green, fontWeight = FontWeight.Bold)) {
                                append("Ayo ")
                            }
                            withStyle(SpanStyle(color = black, fontWeight = FontWeight.Bold)) {
                                append("kita membandingkan bersama....")
                            }
                        },
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.banding2),
                            contentDescription = "Deskripsi gambar",
                            modifier = Modifier.size(300.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    Text("Pada gambar diatas menunjukkan sarang lebah.\n\n" +
                            "Pertama, kita cari pembilangnya yaitu yang telah diberi warna kuning,\n\n" +
                            "Gambar di samping kiri menunjukkan 4 buah warna kuning, kanan menunjukkan 5 buah.\n\n" +
                            "Pembilang = 4 dan 5\n\n" +
                            "Kedua, kita mencari penyebut yang dimana semua lubang kita hitung dari masing-masing gambar mempunyai 7 lubang\n\n" +
                            "Penyebut = 7",
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))


                    Column(horizontalAlignment = Alignment.Start) {
                        Text("Bentuk Pecahan:", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            PecahanBiasa(numerator = 4, denominator = 7, fontSize = 20.sp)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("dan", fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(16.dp))
                            PecahanBiasa(numerator = 5, denominator = 7, fontSize = 20.sp)
                        }
                    }


                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Nah, pada bentuk pecahan di atas kita dapat melihat 4 itu lebih kecil dari 5 atau 5 lebih besar dari 4\n\nHasilnya:",
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.banding3),
                            contentDescription = "Deskripsi gambar",
                            modifier = Modifier.size(300.dp)
                        )

                    }
                    Text(
                        text = "Membandingkan Pecahan",
                        color = Color.Red,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    Text(
                        text = "Setelah mengubah penyebut dan pembilang, dilanjutkan dengan membandingkan kedua pecahan seperti cara membandingkan pecahan dengan penyebut sama.",
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))


                    Text(
                        text = "Contoh soal:",
                        color = Color.Red,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(3, 5)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("... ", fontSize = 18.sp)
                        PecahanBiasa(6, 8)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("mari kita bandingkan", fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(3, 5)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(" = ", fontSize = 18.sp)
                        PecahanBiasa(24, 40)
                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(6, 8)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(" = ", fontSize = 18.sp)
                        PecahanBiasa(30, 40)
                    }

                    Spacer(modifier = Modifier.height(12.dp))


                    Text(
                        text = "(keterangan: KPK dari 5 dan 8 adalah 40)",
                        color = Color.Red,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Karena 24 < 30 maka ", fontSize = 16.sp)
                        PecahanBiasa(3, 5)
                        Text(" < ")
                        PecahanBiasa(6, 8)
                    }

                    Text(
                        "Ternyata, membandingkan pecahan itu gampang, asalkan kita tahu caranya!\n\n" +
                                "Yuk, terus belajar mengurutkan pecahan supaya makin jago!",
                        fontSize = 16.sp
                    )
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
                        navController.navigate("materi2satu")
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
                        navController.navigate("materi2tiga")
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

