package com.reeftankcare.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.reeftankcare.utilits.NotificationHelper

class NotifyWorker(val context: Context, private val workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        NotificationHelper(context).createNotification(
            inputData.getString("message").toString(),
            inputData.getString("title").toString()
        )
        return Result.success()
    }
}

