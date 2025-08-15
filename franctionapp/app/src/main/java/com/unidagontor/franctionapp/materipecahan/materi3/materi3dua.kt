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

@Composable
fun Penjumlahan2Screen(
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

                    Image(
                        painter = painterResource(id = R.drawable.jumlah4),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "2. Penjumlahan pecahan berbeda penyebut",
                        color = Color.Red,
                        fontSize = 20.sp
                    )
                    Text("Kita mmempunyai pecahan dengan penyebut yang berbeda, " +
                            "akan tetapi kita harus menjumlahkan pecahan tersebut.\n", color = Color.Black, fontSize = 18.sp)

                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Contoh : ", color = Color.Black, fontSize = 18.sp)
                    Image(
                        painter = painterResource(id = R.drawable.jumlah5),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    Image(
                        painter = painterResource(id = R.drawable.jumlah6),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )


                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Black, fontSize = 18.sp)) {
                                append("Kita harus mencari ")
                            }
                            withStyle(style = SpanStyle(color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 18.sp)) {
                                append("KPK")
                            }
                            withStyle(style = SpanStyle(color = Color.Black, fontSize = 18.sp)) {
                                append(" dari kedua penyebutnya")
                            }
                        }
                    )


                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Black, fontSize = 18.sp)) {
                                append("2 = 2, 4, ")
                            }
                            withStyle(style = SpanStyle(color = Color.Red, fontSize = 18.sp)) {
                                append("6")
                            }
                            withStyle(style = SpanStyle(color = Color.Black, fontSize = 18.sp)) {
                                append(", 8, 10")
                            }
                        }
                    )


                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Black, fontSize = 18.sp)) {
                                append("3 = 3, ")
                            }
                            withStyle(style = SpanStyle(color = Color.Red, fontSize = 18.sp)) {
                                append("6")
                            }
                            withStyle(style = SpanStyle(color = Color.Black, fontSize = 18.sp)) {
                                append(", 9, 12")
                            }
                        }
                    )


                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Kita menemukan KPK dari kedua penyebutnya yaitu ", color = Color.Black,fontSize = 18.sp)
                    Text("6", color = Color.Red, fontSize = 18.sp)

                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "Kita buat gambar dari KPK yang sudah kita dapatkan tadi",
                        color = Color.Black,fontSize = 18.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.jumlah7),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )

                    Text(
                        text = "Sekarang kita menentukan Pembilangnya dengan cara seperti ini : ",
                        color = Color.Black,fontSize = 18.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.jumlah8),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    Image(
                        painter = painterResource(id = R.drawable.jumlah9),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        text = "Nah, Dari gambar diatas kita sudah bisa mendapatkan jawabannya : ",
                        color = Color.Black,fontSize = 18.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.jumlah10),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                    Image(
                        painter = painterResource(id = R.drawable.jumlah11),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
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
                        navController.navigate("materi3satu")
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
                        navController.navigate("materi3Quiz")
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

