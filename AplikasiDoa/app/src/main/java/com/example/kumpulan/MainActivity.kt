package com.example.kumpulan

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private val REQUEST_CODE_PERMISSIONS = 123
    private val REQUIRED_PERMISSIONS = arrayOf(
        android.Manifest.permission.WAKE_LOCK,
        android.Manifest.permission.FOREGROUND_SERVICE,
        android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
    )

    private lateinit var recyclerView: RecyclerView
    internal var doaList: ArrayList<Doa> = arrayListOf()
    private lateinit var doaAdapter: DoaAdapter
    private lateinit var db: FirebaseFirestore
    private lateinit var searchView: SearchView
    private lateinit var tvHome: TextView
    private lateinit var tvJadwal: TextView
    private lateinit var tvFavorite: TextView

    private var isDarkMode = false
    private var isToastShown = false

    private var homeData = arrayListOf<Doa>()
    private var favoriteData = arrayListOf<Doa>()

    companion object {
        private const val REQUEST_CODE_SETTING_JADWAL = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.rvItem)
        recyclerView.layoutManager = LinearLayoutManager(this)

        doaAdapter = DoaAdapter(doaList)
        recyclerView.adapter = doaAdapter

        db = FirebaseFirestore.getInstance()
        db.collection("KumpulaDoa")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val doa = document.toObject(Doa::class.java)
                    doaList.add(doa)
                }
                doaList.sortBy { it.no ?: Int.MAX_VALUE }
                doaAdapter.notifyDataSetChanged()
                homeData = doaList
                showHomeData()
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Data Doa Eror", exception)
            }

        searchView = findViewById(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterDoaList(newText)
                return true
            }
        })

        tvHome = findViewById(R.id.tv_home)
        tvJadwal = findViewById(R.id.tv_jadwal)
        tvFavorite = findViewById(R.id.tv_favorite)

        tvHome.setOnClickListener { showHomeData() }
        tvJadwal.setOnClickListener { showJadwalData() }
        tvFavorite.setOnClickListener { showFavoriteData() }

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

    }

    override fun onResume() {
        super.onResume()
        showJadwalData()
        showFavoriteData()
        showHomeData()

        val currentIsDarkMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
        if (currentIsDarkMode != isDarkMode) {
            isDarkMode = currentIsDarkMode
            doaAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_jadwal -> {
                val intent = Intent(this, JadwalActivity::class.java)
                intent.putParcelableArrayListExtra("doaList", doaList)
                startActivityForResult(intent, REQUEST_CODE_SETTING_JADWAL)
                true
            }

            R.id.action_settings -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.action_favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun filterDoaList(text: String?) {
        val filteredList = arrayListOf<Doa>()

        val dataToFilter = when {
            tvHome.isSelected -> homeData
            tvJadwal.isSelected -> {

                val jadwalList = getJadwalDoaList()
                jadwalList
            }
            tvFavorite.isSelected -> favoriteData
            else -> homeData
        }

        if (!text.isNullOrEmpty()) {
            for (doa in dataToFilter) {
                if (doa.judulDoa?.lowercase()?.contains(text.lowercase()) == true) {
                    filteredList.add(doa)
                }
            }

            doaAdapter.updateDoaList(filteredList)
            doaAdapter.notifyDataSetChanged()

            if (filteredList.isEmpty() && !isToastShown) {
                Toast.makeText(this, "Doa Tidak Ditemukan", Toast.LENGTH_SHORT).show()
                isToastShown = true
            }
        } else {

            when {
                tvHome.isSelected -> showHomeData()
                tvJadwal.isSelected -> showJadwalData()
                tvFavorite.isSelected -> showFavoriteData()
                else -> showHomeData()
            }
            isToastShown = false
        }
    }


    private fun showHomeData() {
        recyclerView.adapter = doaAdapter
        doaAdapter.updateDoaList(homeData)
        tvHome.isSelected = true
        tvJadwal.isSelected = false
        tvFavorite.isSelected = false

        tvHome.setTextColor(Color.BLACK)
        tvJadwal.setTextColor(Color.WHITE)
        tvFavorite.setTextColor(Color.WHITE)
    }
    private fun showJadwalData() {
        tvHome.isSelected = false
        tvJadwal.isSelected = true
        tvFavorite.isSelected = false

        tvHome.setTextColor(Color.WHITE)
        tvJadwal.setTextColor(Color.BLACK)
        tvFavorite.setTextColor(Color.WHITE)

        val sharedPrefs = getSharedPreferences("jadwal_doa", Context.MODE_PRIVATE)
        val jadwalList = ArrayList<Doa>()

        for (doa in doaList) {
            val doaKey = "doa_${doa.no}"
            val judulDoa = sharedPrefs.getString("$doaKey-judul", null)
            val tanggal = sharedPrefs.getString("$doaKey-tanggal", null)
            val waktu = sharedPrefs.getString("$doaKey-waktu", null)

            if (judulDoa != null && tanggal != null && waktu != null) {
                val doaJadwal = Doa(doa.no, judulDoa, doa.bArab, doa.bIndo, tanggal, waktu)
                jadwalList.add(doaJadwal)
            }
        }

        doaAdapter.updateDoaList(jadwalList)

    }


    private fun showFavoriteData() {
        recyclerView.adapter = doaAdapter
        favoriteData = getFavoriteData()
        doaAdapter.updateDoaList(favoriteData)
        tvHome.isSelected = false
        tvJadwal.isSelected = false
        tvFavorite.isSelected = true

        tvHome.setTextColor(Color.WHITE)
        tvJadwal.setTextColor(Color.WHITE)
        tvFavorite.setTextColor(Color.BLACK)
    }


    private fun getFavoriteData(): ArrayList<Doa> {
        val sharedPrefs = getSharedPreferences("favorite_doas", Context.MODE_PRIVATE)
        val favoriteDoaIds = sharedPrefs.getStringSet("doa_ids", setOf()) ?: setOf()
        val favoriteList = arrayListOf<Doa>()
        for (doaId in favoriteDoaIds) {
            val doa = doaList.find { it.no.toString() == doaId }
            if (doa != null) {
                favoriteList.add(doa)
            }
        }
        return favoriteList
    }

    fun getDoaList(): ArrayList<Doa> {
        return doaList
    }

    fun isJadwalSelected(): Boolean {
        return tvJadwal.isSelected

    }

    private fun getJadwalDoaList(): ArrayList<Doa> {
        val sharedPrefs = getSharedPreferences("jadwal_doa", Context.MODE_PRIVATE)
        val jadwalList = ArrayList<Doa>()

        for (doa in doaList) {
            val doaKey = "doa_${doa.no}"
            val judulDoa = sharedPrefs.getString("$doaKey-judul", null)

            if (judulDoa != null) {
                jadwalList.add(doa)
            }
        }
        return jadwalList
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri: Uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

}