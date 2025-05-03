package com.example.kumpulan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class DetailActivity : AppCompatActivity() {

    private var isFavorite = false
    private lateinit var doa: Doa
    private var fromJadwal = false
    private var doaId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvJudulDoa: TextView = findViewById(R.id.tvJudulDoa)
        val tvArabic: TextView = findViewById(R.id.tvArabic)
        val tvTranslation: TextView = findViewById(R.id.tvTranslation)

        doa = intent.getParcelableExtra("doa")!!
        fromJadwal = intent.getBooleanExtra("fromJadwal", false)
        doaId = doa.no

        tvJudulDoa.text = doa.judulDoa
        tvArabic.text = doa.bArab
        tvTranslation.text = doa.bIndo

        isFavorite = isDoaFavorite(doa)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        val favoriteItem = menu?.findItem(R.id.action_favorite)
        setFavoriteIcon(favoriteItem)


        if (doa.no == null) {
            menu?.findItem(R.id.action_jadwal)?.isVisible = false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (fromJadwal) {
                    val intent = Intent(this, DetailJadwalActivity::class.java)
                    intent.putExtra("doa", doa)
                    startActivity(intent)
                } else {
                    onBackPressed()
                }
                return true
            }


            R.id.action_favorite -> {
                isFavorite = !isFavorite
                if (isFavorite) {
                    saveDoaAsFavorite(doa)
                    Toast.makeText(this, "Ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
                } else {
                    removeDoaFromFavorite(doa)
                    Toast.makeText(this, "Dihapus dari favorit", Toast.LENGTH_SHORT).show()
                }

                setFavoriteIcon(item)
                return true
            }
            R.id.action_jadwal -> {
                val intent = Intent(this, SettingJadwalActivity::class.java)
                intent.putExtra("doa", doa)
                intent.putParcelableArrayListExtra("doaList", intent.getParcelableArrayListExtra("doaList"))
                startActivity(intent)
                true
            }
            R.id.action_setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun isDoaFavorite(doa: Doa): Boolean {
        val sharedPrefs = getSharedPreferences("favorite_doas", Context.MODE_PRIVATE)
        val favoriteDoaIds = sharedPrefs.getStringSet("doa_ids", setOf()) ?: setOf()
        return favoriteDoaIds.contains(doa.no.toString())
    }

    private fun saveDoaAsFavorite(doa: Doa) {
        val sharedPrefs = getSharedPreferences("favorite_doas", Context.MODE_PRIVATE)
        val favoriteDoaIds = sharedPrefs.getStringSet("doa_ids", setOf())?.toMutableSet() ?: mutableSetOf()
        favoriteDoaIds.add(doa.no.toString())
        sharedPrefs.edit().putStringSet("doa_ids", favoriteDoaIds).apply()
    }

    private fun removeDoaFromFavorite(doa: Doa) {
        val sharedPrefs = getSharedPreferences("favorite_doas", Context.MODE_PRIVATE)
        val favoriteDoaIds = sharedPrefs.getStringSet("doa_ids", setOf())?.toMutableSet() ?: mutableSetOf()
        favoriteDoaIds.remove(doa.no.toString())
        sharedPrefs.edit().putStringSet("doa_ids", favoriteDoaIds).apply()
    }

    private fun setFavoriteIcon(menuItem: MenuItem?) {
        if (menuItem != null) {
            menuItem.icon = if (isFavorite) {
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_filled)
            } else {
                ContextCompat.getDrawable(this, R.drawable.ic_favorite)
            }
        }
    }
}
