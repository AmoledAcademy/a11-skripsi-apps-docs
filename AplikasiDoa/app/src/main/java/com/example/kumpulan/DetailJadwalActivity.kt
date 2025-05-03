package com.example.kumpulan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailJadwalActivity : AppCompatActivity() {

    private lateinit var doa: Doa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_jadwal)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbardetailjadwal)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Doa yang Telah Dijadwalkan"

        val tvJudulDoa: TextView = findViewById(R.id.tv_judul_doa)
        val tvTanggal: TextView = findViewById(R.id.tv_tanggal)
        val tvWaktu: TextView = findViewById(R.id.tv_waktu)
        val btnHapus: Button = findViewById(R.id.btn_hapus)

        doa = intent.getParcelableExtra("doa")!!

        val sharedPrefs = getSharedPreferences("jadwal_doa", Context.MODE_PRIVATE)
        val doaKey = "doa_${doa.no}"
        val judulDoa = sharedPrefs.getString("$doaKey-judul", null)
        val tanggal = sharedPrefs.getString("$doaKey-tanggal", null)
        val waktu = sharedPrefs.getString("$doaKey-waktu", null)

        if (judulDoa != null && tanggal != null && waktu != null) {
            tvJudulDoa.text = judulDoa
            tvTanggal.text = "Tanggal: $tanggal"
            tvWaktu.text = "Waktu: $waktu"
        } else {
            tvJudulDoa.text = "Doa tidak ditemukan"
            tvTanggal.text = "Tanggal: -"
            tvWaktu.text = "Waktu: -"
        }

        btnHapus.setOnClickListener {
            val editor = sharedPrefs.edit()
            editor.remove("$doaKey-judul")
            editor.remove("$doaKey-tanggal")
            editor.remove("$doaKey-waktu")
            editor.remove("$doaKey-arab")
            editor.remove("$doaKey-arti")
            editor.apply()


            doa.tanggal = null
            doa.waktu = null
            val resultIntent = Intent()
            resultIntent.putExtra("doa", doa)
            setResult(RESULT_OK, resultIntent)

            Toast.makeText(this, "Jadwal berhasil dihapus", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}