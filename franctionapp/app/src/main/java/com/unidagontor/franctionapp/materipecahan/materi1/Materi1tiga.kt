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
import com.unidagontor.franctionapp.materipecahan.PecahanCampuran
import com.unidagontor.franctionapp.materipecahan.PecahanGabung

@Composable
fun PengenalanPecahan3Screen(
    navController: NavController,
    title: String = "Materi 1",
    subtitle: String = "Pengenalan Pecahan"
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
                    Spacer(modifier = Modifier.height(16.dp))



                            Text(
                                text = "❖ Pecahan Biasa",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1976D2)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Pecahan biasa dapat berupa pecahan murni atau pecahan tidak murni.",
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))


                            Text(
                                buildAnnotatedString {
                                    append("➔ Pecahan murni :\nJika nilai ")
                                    withStyle(style = SpanStyle(color = Color.Red)) {
                                        append("pembilang ")
                                    }
                                    append("lebih kecil daripada nilai ")
                                    withStyle(style = SpanStyle(color = Color(0xFF00B050))) {
                                        append("penyebut")
                                    }
                                    append(" (a < b), maka disebut pecahan murni. Contohnya:")
                                },
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                PecahanBiasa(1, 2)
                                Text(",", fontSize = 20.sp, modifier = Modifier.padding(horizontal = 4.dp))
                                PecahanBiasa(2, 4)
                                Text(",", fontSize = 20.sp, modifier = Modifier.padding(horizontal = 4.dp))
                                PecahanBiasa(3, 6)
                            }

                            Spacer(modifier = Modifier.height(24.dp))


                            Text(
                                buildAnnotatedString {
                                    append("➔ Pecahan tidak murni :\nJika nilai ")
                                    withStyle(style = SpanStyle(color = Color.Red)) {
                                        append("pembilang ")
                                    }
                                    append("lebih besar daripada nilai ")
                                    withStyle(style = SpanStyle(color = Color(0xFF00B050))) {
                                        append("penyebut")
                                    }
                                    append(" (a > b), maka disebut pecahan murni. Contohnya:")
                                },
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                PecahanBiasa(2, 1)
                                Text(",", fontSize = 20.sp, modifier = Modifier.padding(horizontal = 4.dp))
                                PecahanBiasa(10, 5)
                                Text(",", fontSize = 20.sp, modifier = Modifier.padding(horizontal = 4.dp))
                                PecahanBiasa(15, 3)
                            }

                            Spacer(modifier = Modifier.height(32.dp))


                            Text(
                                text = "❖ Pecahan Campuran",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1976D2)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Pecahan campuran adalah pecahan yang menggabungkan bilangan bulat dan pecahan murni. \nContohnya:",
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                PecahanCampuran(1, 1, 2)
                                Text(",", fontSize = 20.sp, modifier = Modifier.padding(horizontal = 4.dp))
                                PecahanCampuran(3, 1, 4)
                                Text(",", fontSize = 20.sp, modifier = Modifier.padding(horizontal = 4.dp))
                                PecahanCampuran(7, 5, 6)
                            }

                            Spacer(modifier = Modifier.height(32.dp))


                            Text(
                                text = "❖ Mengubah suatu pecahan",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1976D2)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Cara mengubah pecahan biasa ke pecahan campuran, pembilang dibentuk menjadi kelipatan penyebut dan ditambahkan sisanya",
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(text = "Contoh:", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                PecahanBiasa(13, 5)
                                Text(" = ", fontSize = 16.sp)
                                PecahanGabung(bilanganBulat = 10, pembilang = 3, penyebut = 5)
                                Text(" = ", fontSize = 16.sp)
                                Text("2 + ", fontSize = 16.sp)
                                PecahanBiasa(3, 5)
                                Text(" = ", fontSize = 16.sp)
                                PecahanCampuran(2, 3, 5)
                            }


                            Image(
                                painter = painterResource(id = R.drawable.akhir1),
                                contentDescription = "Penutup motivasi pecahan",
                                modifier = Modifier
                                    .size(400.dp)
                            )

                        }
                    }
            }


        //column

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
                        navController.navigate("materi1Quiz")
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
        //Nav Bottom
    }
}


