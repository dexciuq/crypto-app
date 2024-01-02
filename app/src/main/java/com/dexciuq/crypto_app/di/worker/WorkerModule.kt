package com.dexciuq.crypto_app.di.worker

import com.dexciuq.crypto_app.data.worker.ChildWorkerFactory
import com.dexciuq.crypto_app.data.worker.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(factory: RefreshDataWorker.Factory): ChildWorkerFactory
}