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
import androidx.compose.ui.layout.ContentScale
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
fun BandingUrut4Screen(
    navController: NavController,
    title: String = "Materi 2",
    subtitle: String = "Membandingkan dan Mengurutkan Pecahan",
    onNextClick: () -> Unit = {}
) {
    val merah = Color(0xFFEB5757)
    val hijau = Color(0xFF27AE60)
    val ungu = Color(0xFF9B51E0)
    val biru = Color(0xFF2D9CDB)
    val orange = Color(0xFFF2994A)
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
                Column(modifier = Modifier.weight(1f)) {
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
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Mengurutkan Pecahan berbeda penyebut",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF219653)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Mengurutkan pecahan yang memiliki penyebut berbeda kita harus mengubah penyebutnya menjadi sama dengan mencari KPK dari penyebut pecahan-pecahan tersebut.",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Selain itu kita bisa mengurutkan nilai pecahan melalui gambar loh, penasaran kan, berikut contohnya:",
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Image(
                        painter = painterResource(id = R.drawable.urut4),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Perhatikan gambar diatas, kita dapat mengurutkan pecahannya hanya dari luas yang telah kita warnai atau diarsir.",
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Sekarang kita ingin mengurutkan nilai pecahannya dari yang terkecil, dan kita bisa lihat dari ketiga gambar mana yang paling sedikit mendapatkan warnanya:",
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Image(
                        painter = painterResource(id = R.drawable.urut5),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        text = "Mengurutkan pecahan yang memiliki penyebut berbeda kita harus mengubah penyebutnya menjadi sama dengan mencari KPK dari penyebut pecahan-pecahan tersebut.",
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(2, 4)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(", ")
                        PecahanBiasa(1, 5)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(", ")
                        PecahanBiasa(7, 8)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("   KPK dari 4, 5 dan 8 adalah 40", fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(12.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("2×10", fontSize = 14.sp, color = Color(0xFF009688))
                            Box(
                                modifier = Modifier
                                    .height(2.dp)
                                    .width(32.dp)
                                    .background(Color.Black)
                            )
                            Text("4×10", fontSize = 16.sp, color = Color(0xFF009688))
                        }
                        Text(" = ")
                        PecahanBiasa(20, 40)
                        Text(" = ")
                        PecahanBiasa(2, 4)
                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("1×8", fontSize = 16.sp, color = Color(0xFF1976D2))
                            Box(
                                modifier = Modifier
                                    .height(2.dp)
                                    .width(32.dp)
                                    .background(Color.Black)
                            )
                            Text("5×8", fontSize = 14.sp, color = Color(0xFF1976D2))
                        }
                        Text(" = ")
                        PecahanBiasa(8, 40)
                        Text(" = ")
                        PecahanBiasa(1, 5)
                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("7×5", fontSize = 16.sp, color = Color(0xFFF57C00))
                            Box(
                                modifier = Modifier
                                    .height(2.dp)
                                    .width(32.dp)
                                    .background(Color.Black)
                            )
                            Text("8×5", fontSize = 14.sp, color = Color(0xFFF57C00))
                        }
                        Text(" = ")
                        PecahanBiasa(35, 40)
                        Text(" = ")
                        PecahanBiasa(7, 8)
                    }

                    Spacer(modifier = Modifier.height(12.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Urutan dari nilai terkecil ke terbesar adalah :", fontSize = 16.sp)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(1, 5)
                        Text(", ")
                        PecahanBiasa(2, 4)
                        Text(", ")
                        PecahanBiasa(7, 8)
                    }


                    Text(
                        text = buildAnnotatedString {
                            append("Sekarang kamu sudah belajar cara ")
                            withStyle(style = SpanStyle(color = merah)) {
                                append("membandingkan")
                            }
                            append(" dan ")
                            withStyle(style = SpanStyle(color = hijau)) {
                                append("mengurutkan pecahan")
                            }
                            append(".\nTernyata nggak sulit, kan?\n\n")

                            append("Dengan menyamakan penyebutnya,\n\n")

                            append("kamu bisa tahu mana pecahan yang lebih besar, lebih kecil, atau sama.\n")
                            append("Kamu juga bisa menyusun dari yang terkecil sampai terbesar atau sebaliknya.\n\n")

                            withStyle(style = SpanStyle(color = biru)) {
                                append("Gampang banget kalau sering latihan!\n")
                            }

                            withStyle(style = SpanStyle(color = orange)) {
                                append("Yuk, terus belajar supaya makin pintar!")
                            }
                        },
                        fontSize = 16.sp
                    )
                }
            }
        }

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
                        navController.navigate("materi2tiga")
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
                        navController.navigate("materi2Quiz")
                    },
                    enabled = isScrollAtBottom.value,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isScrollAtBottom.value) Color(0xFF00BCD4) else Color(0xFF03A9F4).copy(alpha = 0.5f)
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
