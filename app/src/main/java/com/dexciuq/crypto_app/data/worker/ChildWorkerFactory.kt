package com.dexciuq.crypto_app.data.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface ChildWorkerFactory {
    fun create(
        context: Context,
        workerParameters: WorkerParameters,
    ): ListenableWorker
}