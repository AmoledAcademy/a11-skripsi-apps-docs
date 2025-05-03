package com.example.kumpulan

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SettingJadwalActivity : AppCompatActivity() {
    private lateinit var calendarView: CalendarView
    private lateinit var numberPickerJam: NumberPicker
    private lateinit var numberPickerMenit: NumberPicker
    private lateinit var btnSimpan: Button

    private var selectedYear: Int = 0
    private var selectedMonth: Int = 0
    private var selectedDay: Int = 0
    private var selectedHour: Int = 0
    private var selectedMinute: Int = 0

    private lateinit var doa: Doa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_jadwal)

        doa = intent.getParcelableExtra("doa") ?: Doa()

        calendarView = findViewById(R.id.calendar_view_2)
        numberPickerJam = findViewById(R.id.number_picker_jam_2)
        numberPickerMenit = findViewById(R.id.number_picker_menit_2)
        btnSimpan = findViewById(R.id.btn_simpan)

        val sharedPrefs = getSharedPreferences("jadwal_doa", Context.MODE_PRIVATE)
        val doaKey = "doa_${doa.no}"
        val judulDoa = sharedPrefs.getString("$doaKey-judul", null)

        if (judulDoa != null) {
            btnSimpan.text = "UPDATE"
        }


        val calendar = Calendar.getInstance()
        selectedYear = calendar.get(Calendar.YEAR)
        selectedMonth = calendar.get(Calendar.MONTH)
        selectedDay = calendar.get(Calendar.DAY_OF_MONTH)

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedYear = year
            selectedMonth = month
            selectedDay = dayOfMonth
        }

        numberPickerJam.minValue = 0
        numberPickerJam.maxValue = 23
        numberPickerJam.value = calendar.get(Calendar.HOUR_OF_DAY)
        selectedHour = numberPickerJam.value

        numberPickerMenit.minValue = 0
        numberPickerMenit.maxValue = 59
        numberPickerMenit.value = calendar.get(Calendar.MINUTE)
        selectedMinute = numberPickerMenit.value

        numberPickerJam.setOnValueChangedListener { _, _, newVal ->
            selectedHour = newVal
        }

        numberPickerMenit.setOnValueChangedListener { _, _, newVal ->
            selectedMinute = newVal
        }

        btnSimpan.setOnClickListener {
            simpanJadwal()
        }


    }

    private fun simpanJadwal() {
        val sharedPrefs = getSharedPreferences("jadwal_doa", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        val calendar = Calendar.getInstance()
        calendar.set(selectedYear, selectedMonth, selectedDay, selectedHour, selectedMinute)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val currentTimeMillis = System.currentTimeMillis()

        if (calendar.timeInMillis < currentTimeMillis) {
            Toast.makeText(this, "Waktu yang dipilih sudah lewat. Silakan pilih waktu di masa mendatang.", Toast.LENGTH_SHORT).show()
            return
        }

        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val tanggal = dateFormat.format(calendar.time)
        val waktu = timeFormat.format(calendar.time)

        val doaKey = "doa_${doa.no}"
        editor.putString("$doaKey-judul", doa.judulDoa)
        editor.putString("$doaKey-tanggal", tanggal)
        editor.putString("$doaKey-waktu", waktu)
        editor.putString("$doaKey-arab", doa.bArab)
        editor.putString("$doaKey-arti", doa.bIndo)
        editor.apply()

        doa.tanggal = tanggal
        doa.waktu = waktu
        val resultIntent = intent
        resultIntent.putExtra("doa", doa)
        setResult(RESULT_OK, resultIntent)

        if (btnSimpan.text == "UPDATE") {
            Toast.makeText(this, "Jadwal berhasil diperbarui", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Jadwal berhasil disimpan", Toast.LENGTH_SHORT).show()
        }

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, AlarmReceiver::class.java)
        intent.putExtra("doa_judul", doa.judulDoa)
        intent.putExtra("waktuDoa", waktu)
        intent.putExtra("doa_id", doa.no)
        val pendingIntent = PendingIntent.getBroadcast(this, doa.no!!, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)


        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )

        finish()
    }
}