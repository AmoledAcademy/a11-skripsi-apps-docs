package com.example.kumpulan

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val doaId = intent.getIntExtra("doa_id", 0)
        val doaJudul = intent.getStringExtra("doa_judul") ?: "Judul Tidak Ditemukan"

        showAlarmNotification(context, doaId, doaJudul)
    }

    private fun showAlarmNotification(context: Context, doaId: Int, doaJudul: String?) {
        val channelId = "Channel_1"
        val channelName = "AlarmManager channel"

        val notificationIntent = Intent(context, BroadcastReceiverDetailActivity::class.java)
        notificationIntent.putExtra("doa_id", doaId)
        val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_jadwal_doa)
            .setContentTitle(doaJudul)
            .setContentText("Waktunya membaca $doaJudul!")
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(3000, 1000, 1000, 1000, 1000)
            notificationManagerCompat.createNotificationChannel(channel)
        }

        val notification = builder.build()
        notificationManagerCompat.notify(doaId, notification)
    }
}