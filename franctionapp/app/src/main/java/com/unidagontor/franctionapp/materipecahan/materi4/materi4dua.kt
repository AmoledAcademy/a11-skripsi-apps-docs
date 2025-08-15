package com.unidagontor.franctionapp.materipecahan.materi4

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
import androidx.compose.foundation.layout.heightIn
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
fun Pengurangan2Screen(
    navController: NavController,
    title: String = "Materi 4",
    subtitle: String = "Operasi Pengurangan Pecahan",
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

                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) {
                                append("Pengurangan Pecahan dengan ")
                            }
                            withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
                                append("penyebut yang berbeda")
                            }
                        },
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Penjelasan awal
                    Text(
                        text = "Untuk mengurangkan bilangan yang penyebutnya berbeda, Langkahnya sama persis dengan penjumlahan, yaitu dengan mencari ",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "KPK",
                        fontSize = 18.sp,
                        color = Color(0xFF00C853)

                    )
                    Text(
                        text = " antara dua atau lebih penyebut. Berikut ini contohnya :",
                        fontSize = 16.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.kurang4), // Gambar donat
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 280.dp, max = 360.dp) // bisa kamu sesuaikan tinggi minimumnya
                            .padding(vertical = 8.dp) // jarak atas bawah tidak terlalu besar
                    )


                    // Gambar segitiga dan bintang cukup diganti Image() jika ingin
                    Text(text="Seperti sebelumnya kita mencari pembilang dan penyebutnya terlebih dahulu dari kedua gambar tersebut yaitu", fontSize = 16.sp)
                    Image(
                        painter = painterResource(id = R.drawable.kurang5), // Gambar donat
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 150.dp, max = 200.dp) // bisa kamu sesuaikan tinggi minimumnya
                            .padding(vertical = 8.dp) // jarak atas bawah tidak terlalu besar
                    )

                    Text(
                        text = "Pada bentuk pecahan diatas kita menemukan nilai penyebut yang berbeda dari keduanya maka kita mencari ",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "KPK",
                        fontSize = 16.sp,
                        color = Color.Red
                    )
                    Text(text = " dari 2 dan 5", fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "KPK dari 2 = 2, 4, 6, 8, ",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "10",
                        fontSize = 16.sp,
                        color = Color(0xFF00C853)
                    )
                    Text(
                        text = "\nKPK dari 5 = 5, ",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "10",
                        fontSize = 16.sp,
                        color = Color(0xFF00C853)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(text="Kita mengambil nilai KPKnya 10 dengan gambar seperti dibawah ini :", fontSize = 16.sp)

                    Image(
                        painter = painterResource(id = R.drawable.kurang6),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 280.dp, max = 360.dp) // bisa kamu sesuaikan tinggi minimumnya
                            .padding(vertical = 8.dp) // jarak atas bawah tidak terlalu besar
                    )


                    Image(
                        painter = painterResource(id = R.drawable.kurang7),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 280.dp, max = 360.dp)
                            .padding(vertical = 2.dp)
                    )


                    Text(text="Untuk mencari pembilangnya kita harus menghitungnya melalui perkalian silang dari pembilang ke penyebut musuhnya", fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(8.dp))

                    Image(
                        painter = painterResource(id = R.drawable.kurang8),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 280.dp, max = 500.dp)
                            .padding(vertical = 8.dp)
                    )



                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Tak terasa, kita sudah belajar banyak hal tentang pengurangan pecahan ini.")

                    Spacer(modifier = Modifier.height(4.dp))

                    Text("Mulai dari pengurangan pecahan dengan penyebut yang sama, penyebut yang berbeda.")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Sekarang, kamu sudah tahu bahwa kunci dari pengurangan pecahan adalah menyamakan penyebut terlebih dahulu, baru deh kita bisa mengurangkan pembilangnya.")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Gampang, kan ?")

                    Spacer(modifier = Modifier.height(16.dp))

                    // Ajak latihan dan penutup
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color(0xFF00C853), fontWeight = FontWeight.Bold)) {
                                append("AYOK, ")
                            }
                            withStyle(style = SpanStyle(color = Color(0xFFFFAB00), fontWeight = FontWeight.Bold)) {
                                append("TERUS BERLATIH ")
                            }
                            withStyle(style = SpanStyle(color = Color.Black)) {
                                append("AGAR MAKIN MAHIR DAN\n")
                            }
                            withStyle(style = SpanStyle(color = Color(0xFF880E4F), fontWeight = FontWeight.Bold)) {
                                append("PERCAYA DIRI DALAM ")
                            }
                            append("MENGERJAKAN SOAL-SOAL PECAHAN. ")
                            append("KARENA MATEMATIKA ITU SERU KALAU KITA PAHAM CARANYA!")

                            withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold)) {
                                append("\nSAMPAI JUMPA YA!")
                            }
                        },
                        fontSize = 16.sp
                    )
                }
            }
        }


        //kolumn
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
                        navController.navigate("materi4satu")
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
                        navController.navigate("materi4Quiz")
                    },
                    enabled = isScrollAtBottom.value,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isScrollAtBottom.value) Color(0xFF00BCD4) else Color(
                            0xFF03A9F4
                        ).copy(alpha = 0.5f)
                    ),
                    modifier = Modifier
                        .width(140.dp)
                        .height(48.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Quiz", color = Color.White, fontSize = 16.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Quiz",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}


