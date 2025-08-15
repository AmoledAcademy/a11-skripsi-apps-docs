package com.unidagontor.franctionapp.developer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unidagontor.franctionapp.datastore.DataStoreManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeveloperScreen() {
    val context = LocalContext.current
    val dataStore = remember { DataStoreManager(context) }
    val scope = rememberCoroutineScope()
    var userList by remember { mutableStateOf<List<DataStoreManager.UserDetail>>(emptyList()) }

    // Load all user data when screen starts
    LaunchedEffect(Unit) {
        scope.launch {
            userList = dataStore.getAllUserDetails()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Developer Panel") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF0D47A1), titleContentColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                "Daftar Semua Pengguna",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            if (userList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Belum ada data pengguna.")
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(userList) { user ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1))
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text("Nama: ${user.nama}", fontWeight = FontWeight.SemiBold)
                                Text("Kelas: ${user.kelas}")
                                Text("Email: ${user.email}")
                                Text("Sekolah: ${user.sekolah}")
                            }
                        }
                    }
                }
            }
        }
    }
}
