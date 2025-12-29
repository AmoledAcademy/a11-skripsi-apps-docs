package com.example.kumpulan

import android.app.NotificationManager
import android.content.Context
import android.graphics.Outline
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BroadcastReceiverDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver_detail)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbarBroadcastReceiver)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = "Doa yang Telah Dijadwalkan"

        val tvJudulDoaBroadcastReceiver: TextView = findViewById(R.id.tvJudulDoaBroadcastReceiver)
        val tvArabicBroadcastReceiver: TextView = findViewById(R.id.tvArabicBroadcastReceiver)
        val tvTranslationBroadcastReceiver: TextView = findViewById(R.id.tvTranslationBroadcastReceiver)

        val doaId = intent.getIntExtra("doa_id", 0)
        val sharedPrefs = getSharedPreferences("jadwal_doa", Context.MODE_PRIVATE)
        val doaKey = "doa_$doaId"

        val judulDoa = sharedPrefs.getString("$doaKey-judul", null)
        val arabic = sharedPrefs.getString("$doaKey-arab", null)
        val artiDoa = sharedPrefs.getString("$doaKey-arti", null)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()


        tvJudulDoaBroadcastReceiver.text = judulDoa
        tvArabicBroadcastReceiver.text = arabic
        tvTranslationBroadcastReceiver.text = artiDoa

        val radius = resources.getDimension(R.dimen.corner_radius)

        tvJudulDoaBroadcastReceiver.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, radius)
            }
        }
        tvJudulDoaBroadcastReceiver.clipToOutline = true

        toolbar.setNavigationOnClickListener {
            hapusDataDoa()
            finish()
        }
    }
    private fun hapusDataDoa() {
        val doaId = intent.getIntExtra("doa_id", 0)
        val sharedPrefs = getSharedPreferences("jadwal_doa", Context.MODE_PRIVATE)
        val doaKey = "doa_$doaId"
        val editor = sharedPrefs.edit()
        editor.remove("$doaKey-judul")
        editor.remove("$doaKey-tanggal")
        editor.remove("$doaKey-waktu")
        editor.remove("$doaKey-arab")
        editor.remove("$doaKey-arti")
        editor.apply()
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