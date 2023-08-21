package com.reeftankcare.utilits

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.reeftankcare.R
import com.reeftankcare.ui.MainActivity

class NotificationHelper(val context: Context) {
    private val NOTIFICATION_ID = 1
    private val CHANNEL_ID = "reeftankcare_channel_01"

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_ID,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Reminder Channel Description"
        }
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    @SuppressLint("MissingPermission")
    fun createNotification(title: String, message: String) {

        createNotificationChannel()

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val icon = context.vectorToBitmap(R.drawable.ic_change_water)
        //val icon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_schedule_white)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_schedule_24dp)
            .setLargeIcon(icon)
            .setContentTitle(title)
            .setContentText(message)
            .setOngoing(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()


        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)

    }
}