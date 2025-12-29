@file:OptIn(ExperimentalMaterial3Api::class)

package com.unidagontor.franctionapp

import Routes
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
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unidagontor.franctionapp.datastore.DataStoreManager
import com.unidagontor.franctionapp.viewmodel.MateriViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisScreen(
    navController: NavController,
    viewModel: MateriViewModel
) {
    val nama = remember { mutableStateOf("") }
    var selectedAngka by remember { mutableStateOf("-") }
    var expandedAngka by remember { mutableStateOf(false) }
    val angkaList = listOf("-", "1", "2", "3", "4", "5", "6")

    var selectedHuruf by remember { mutableStateOf("-") }
    var expandedHuruf by remember { mutableStateOf(false) }
    val hurufList = listOf("-", "A", "B", "C", "D", "E")

    var showDialog by remember { mutableStateOf(false) }
    var showWarning by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = remember { DataStoreManager(context) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Konfirmasi") },
            text = { Text("Apakah data kamu sudah benar?") },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    val namaFix = nama.value.trim()
                    val kelasFix = "${selectedAngka}${selectedHuruf}"
                    isLoading = true
                    scope.launch {
                        dataStore.saveLastUser(namaFix, kelasFix)
                        isLoading = false
                        navController.navigate(Routes.home)
                    }
                }) {
                    Text("Iya")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Tidak")
                }
            },
            containerColor = Color.White
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(WindowInsets.systemBars),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text("Selamat Datang", fontWeight = FontWeight.Bold, fontSize = 28.sp, color = Color(0xFF5D5FEF))
            Text("Silakan isi datamu dulu ya...", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF5D5FEF))

            Image(
                painter = painterResource(id = R.drawable.formicon),
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )

            OutlinedTextField(
                value = nama.value,
                onValueChange = { nama.value = it },
                label = { Text("Nama") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF4CAF50),
                    unfocusedBorderColor = Color(0xFF5D5FEF),
                    focusedLabelColor = Color(0xFF4CAF50),
                    unfocusedLabelColor = Color(0xFF5D5FEF),
                    focusedTextColor = Color(0xFF4CAF50),
                    unfocusedTextColor = Color(0xFF5D5FEF),
                    cursorColor = Color(0xFF5D5FEF),
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expandedAngka,
                    onExpandedChange = { expandedAngka = !expandedAngka },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedAngka,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Kelas", color = Color.Black) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandedAngka) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF4CAF50),
                            unfocusedBorderColor = Color(0xFF5D5FEF),
                            focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            cursorColor = Color(0xFF5D5FEF),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = expandedAngka,
                        onDismissRequest = { expandedAngka = false }
                    ) {
                        angkaList.forEach { angka ->
                            DropdownMenuItem(
                                text = { Text(angka, color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    selectedAngka = angka
                                    expandedAngka = false
                                }
                            )
                        }
                    }
                }

                ExposedDropdownMenuBox(
                    expanded = expandedHuruf,
                    onExpandedChange = { expandedHuruf = !expandedHuruf },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedHuruf,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Subkelas", color = Color.Black) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expandedHuruf) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF4CAF50),
                            unfocusedBorderColor = Color(0xFF5D5FEF),
                            focusedLabelColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                            cursorColor = Color(0xFF5D5FEF),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = expandedHuruf,
                        onDismissRequest = { expandedHuruf = false }
                    ) {
                        hurufList.forEach { huruf ->
                            DropdownMenuItem(
                                text = { Text(huruf, color = MaterialTheme.colorScheme.onSurface) },
                                onClick = {
                                    selectedHuruf = huruf
                                    expandedHuruf = false
                                }
                            )
                        }
                    }
                }
            }

            Button(
                onClick = {
                    if (nama.value.isBlank() || selectedAngka == "-" || selectedHuruf == "-") {
                        showWarning = true
                    } else {
                        showWarning = false
                        showDialog = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5D5FEF)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Masuk", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 18.sp)
            }

            if (showWarning) {
                Text(
                    text = "Mohon isi semua data terlebih dahulu.",
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Ayo Belajar Matematika Pecahan!", fontSize = 18.sp, color = Color(0xFF5D5FEF), fontWeight = FontWeight.Medium)
        }
    }
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x88000000)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 4.dp,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text("Menyimpan data...", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}