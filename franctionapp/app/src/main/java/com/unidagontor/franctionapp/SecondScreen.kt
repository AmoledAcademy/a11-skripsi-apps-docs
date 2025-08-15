@file:OptIn(ExperimentalMaterial3Api::class)

package com.unidagontor.franctionapp

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SecondScreen(
    onNextClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 100), label = "scale"
    )

    LaunchedEffect(pressed) {
        if (pressed) {
            delay(100)
            pressed = false
        }
    }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState) 
        ) {
            Image(
                painter = painterResource(id = R.drawable.org3),
                contentDescription = "Thinking Icon",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 20.dp)
            )

            Text(
                text = "Apa sih pecahan itu ?",
                color = Color(0xFF0D2B6B),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 20.dp),
                style = TextStyle(shadow = Shadow(Color.Gray, Offset(2f, 2f), 4f))
            )

            Text(
                text = "Pecahan adalah bilangan yang\nmenyatakan sebagian dari suatu\nkeseluruhan.",
                color = Color(0xFFE91E63),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Text(
                text = "Biasanya ditulis dalam bentuk:",
                color = Color(0xFFE91E63),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "a",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                HorizontalDivider(
                    modifier = Modifier
                        .width(40.dp)
                        .height(2.dp)
                        .background(Color.Black)
                )
                Text(
                    text = "b",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Penasaran kan ....",
                fontSize = 20.sp,
                color = Color(0xFFE91E63),
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Ayo Jelajahi Petualang Pecahan\nSekarang!!!",
                fontSize = 18.sp,
                color = Color(0xFF030303),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = TextStyle(shadow = Shadow(Color.Gray, Offset(2f, 2f), 4f))
            )

            Spacer(modifier = Modifier.height(50.dp))

            Box(
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale
                    )
            ) {
                Button(
                    onClick = {
                        pressed = true
                        onNextClick()
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFAA0C1E)
                    ),
                    elevation = ButtonDefaults.buttonElevation(10.dp)
                ) {
                    Text(
                        text = "Ayo Mulai Sekarang !",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
