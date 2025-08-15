package com.unidagontor.franctionapp.materipecahan


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PecahanBiasa(numerator: Int, denominator: Int, fontSize: TextUnit = 20.sp)
{
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = numerator.toString(),
            fontSize = 16.sp,
        )
        HorizontalDivider(
            modifier = Modifier.width(20.dp),
            thickness = 2.dp,
            color = Color.Black
        )
        Text(
            text = denominator.toString(),
            fontSize = 16.sp,
        )
    }
}

@Composable
fun PecahanCampuran(whole: Int, numerator: Int, denominator: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = whole.toString(),
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.width(4.dp))
        PecahanBiasa(numerator, denominator)
    }
}

@Composable
fun PecahanGabung(
    bilanganBulat: Int,
    pembilang: Int,
    penyebut: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Bagian atas: 10 + 3
        Text(
            text = "$bilanganBulat + $pembilang",
            fontSize = 16.sp,
            color = Color.Black
        )

        // Garis pecahan
        Box(modifier = Modifier
            .padding(vertical = 2.dp)
            .width(80.dp)
            .height(2.dp)
            .background(Color.Black)
        )

        // Penyebut di bawah garis
        Text(
            text = "$penyebut",
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}
@Composable
fun KonversiPecahanCampuran(
    bilanganBulat: Int,
    pembilang: Int,
    penyebut: Int
) {
    val hasilKali = bilanganBulat * penyebut
    val hasilJumlah = hasilKali + pembilang

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        // Bentuk campuran
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("$bilanganBulat ", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            PecahanVertikal(pembilang, penyebut)
        }

        Text(" = ", fontSize = 20.sp)

        PecahanVertikal("(${bilanganBulat}×${penyebut})+${pembilang}", penyebut)

        Text(" = ", fontSize = 20.sp)

        PecahanVertikal("${hasilKali}+${pembilang}", penyebut)

        Text(" = ", fontSize = 20.sp)

        PecahanVertikal("$hasilJumlah", penyebut)
    }
}
@Composable
fun PecahanVertikal(pembilang: Any, penyebut: Any) {
    val pembilangStr = pembilang.toString()
    val penyebutStr = penyebut.toString()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 6.dp)
    ) {
        Text(
            text = pembilangStr,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Box(
            modifier = Modifier
                .height(2.dp)
                .width((pembilangStr.length * 9).dp)
                .background(Color.Black)
        )
        Text(
            text = penyebutStr,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}




