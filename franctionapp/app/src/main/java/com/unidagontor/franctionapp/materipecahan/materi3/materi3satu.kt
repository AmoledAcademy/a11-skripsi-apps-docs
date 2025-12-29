package com.unidagontor.franctionapp.materipecahan.materi3

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unidagontor.franctionapp.R
import com.unidagontor.franctionapp.materipecahan.PecahanBiasa

@Composable
fun Penjumlahan1Screen(
    navController: NavController,
    title: String = "Materi 3",
    subtitle: String = "Operasi Penjumlahan Pecahan",
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
                        text = "Pada suatu hari, di pelajaran matematika ahmad bertanya....",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))


                    Image(
                        painter = painterResource(id = R.drawable.jumlah1),
                        contentDescription = "Ilustrasi garis pecahan",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    Text(
                        text = "Penjumlahan pecahan",
                        color = Color(0xFF1565C0),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "itu artinya menjumlahkan dua pecahan atau lebih.",
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Tapi, sebelum dijumlahkan, kamu harus cek dulu bagian bawahnya (penyebutnya).",
                        color = Color.Black
                    )
                    Text(
                        text = "Jenis Penjumlahan pecahan : ",
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "1. penyebutnya sama",
                        color = Color(0xFF2E7D32)
                    )
                    Text(
                        text = "\ttinggal jumlahkan bagian atasnya (pembilang).",
                        color = Color.Black
                    )
                    Text(
                        text = "2. penyebutnya beda",
                        color = Color.Red
                    )
                    Text(
                        text = "\tkita harus samakan dulu penyebutnya.",
                        color = Color.Black
                    )

                    Text("Caranya? Cari KPK(Kelipatan Persekutuan Terkecil) dari Penyebut kee penyebut lainnya, supaya sama" +
                            "Setelah penyebutnya sama, kamu bisa langsung jumlahkan bagian atasnya, dan bagian bawahnya tetap.", color = Color.Black)

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "1. Penjumlahan pecahan penyebutnya sama",
                        color = Color(0xFF2E7D32),
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.jumlah2),
                        contentDescription = "Ilustrasi garis pecahan",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
                    )

                    Text(
                        text = "Terlihat jelas pada gambar diatas kita ingin menjumlahkan dari kedua gambar tersebut, tetapi bagaimana kita mengambil nilai pecahan dari kedua gambar tersebut,",
                        color = Color.Black
                    )
                    Text(
                        text = "Ingat Pembilang merupakan gambar yang diwarnai atau di arsis sedangkan Penyebut itu keseluruhan dari setiap potongannya.",
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("gambarkan kanan menunjukan nilai pecahan berupa : ", color = Color.Black)
                        Spacer(modifier = Modifier.width(8.dp))
                        PecahanBiasa(1, 8)
                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("gambarkan kiri menunjukan nilai pecahan berupa : ", color = Color.Black)
                        Spacer(modifier = Modifier.width(8.dp))
                        PecahanBiasa(7, 8)
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PecahanBiasa(1, 8)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("+", fontSize = 20.sp, color = Color.Black)
                        Spacer(modifier = Modifier.width(4.dp))
                        PecahanBiasa(7, 8)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("=", fontSize = 20.sp, color = Color.Black)
                        Spacer(modifier = Modifier.width(4.dp))
                        PecahanBiasa(8, 8)
                    }

                    Image(
                        painter = painterResource(id = R.drawable.jumlah3),
                        contentDescription = "Ilustrasi garis pecahan",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = androidx.compose.ui.layout.ContentScale.FillWidth
                    )

                    Text(
                        text = "Jelas bukan, apabila pecahan kita mempunyai sama penyebutnya kita bisa langsung menambahkan pembilangnya saja...",
                        color = Color.Black
                    )
                }
            }

        }//kolumn


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
                        navController.navigate("materi3dua")
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

