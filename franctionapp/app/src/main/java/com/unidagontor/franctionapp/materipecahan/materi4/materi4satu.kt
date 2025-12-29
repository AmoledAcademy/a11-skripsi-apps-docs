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
import com.unidagontor.franctionapp.materipecahan.PecahanBiasa

@Composable
fun Pengurangan1Screen(
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
                        text = "Operasi Pengurangan Pecahan",
                        color = Color(0xFFFF6F00),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.kurang1), // Gambar donat
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                            .size(300.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Sobat,",
                        color = Color(0xFF2962FF),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "kalian pasti sudah belajar tentang penjumlahan pecahan, kan?",
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        buildAnnotatedString {
                            append("Nah, sekarang kita akan belajar ")
                            withStyle(style = SpanStyle(color = Color(0xFFFF6F00))) {
                                append("pengurangan pecahan.")
                            }
                            append(" Ternyata, caranya mirip sekali dengan penjumlahan, lho!")
                        },
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text( text = "Secara umum, pengurangan pecahan dibagi menjadi ", fontSize = 16.sp)
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color(0xFF2E7D32))) {
                                append("dua jenis")
                            }
                            append(", yaitu:")
                        },
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("1. Pengurangan pecahan dengan penyebut sama\n" +
                            "    kalo penyebutnya sama tinggal kita kurangkan saja pembilangnya")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("2. Pengurangan pecahan dengan penyebut yang berbeda\n" +
                            "    Kita harus samakan dulu penyebutnya, setelah itu, baru deh kita kurangkan pembilangnya")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        buildAnnotatedString {
                            append("Pengurangan pecahan dengan ")
                            withStyle(style = SpanStyle(color = Color(0xFF2962FF))) {
                                append("penyebut sama")
                            }
                        }
                        ,
                        fontSize = 16.sp
                    )
                    Text(text = "Pengurangan ini cukup mudah karena kamu hanya\nperlu mengurangkan pembilangnya saja. Berikut ini\ncontohnya:", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.kurang2),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                            .size(300.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text="Ayo kita hitung terlebih dahulu pembilang dan penyebut\ndari gambar bintang diatas,", fontSize = 17.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        buildAnnotatedString {
                            append("Nah, sekarang kita akan belajar ")
                            withStyle(style = SpanStyle(color = Color(0xFFFF6F00))) {
                                append("pengurangan pecahan.")
                            }
                            append(" Ternyata, caranya mirip sekali dengan penjumlahan, lho!")
                        },
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Text(
                        buildAnnotatedString {
                            append("penyebut")
                            withStyle(style = SpanStyle(color = Color.Red)) {
                                append(" = 6")
                            }
                        },
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text="Kemudian kita hitung berapa nilai pembilang dari kedua\ngambar diatas yang telah kita warnai ataupun diarsir." +
                            "gambar kiri menunjukan nilai 4 pada pembilangnya gambar kanan menunjukan nilai 3 pada pembilangnya" +
                            "kita dapat menyimpulkan bentuk pecahan dari kedua\ngambar diatas adalah :", fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(numerator = 4, denominator = 6)
                        Text("  -  ", fontSize = 20.sp)
                        PecahanBiasa(numerator = 3, denominator = 6)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text="Dikarenakan penyebutnya sama maka tinggal kita\nkurangkan saja pembilangnya yaitu", fontSize = 16.sp)
                    Text(text="4 - 3 = 1", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.kurang3),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 280.dp, max = 360.dp)
                            .padding(vertical = 8.dp)
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
                        navController.navigate("materi4dua")
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



