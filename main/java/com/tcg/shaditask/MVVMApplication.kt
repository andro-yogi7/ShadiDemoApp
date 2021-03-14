package com.tcg.shaditask

import android.app.Application
import com.tcg.garageapplication.data.network.NetworkConnectionInterceptor
import com.tcg.shaditask.data.db.AppDatabase
import com.tcg.shaditask.data.network.MyApi
import com.tcg.shaditask.data.repositories.DBModelRepository
import com.tcg.shaditask.ui.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication(): Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { DBModelRepository(instance(),instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
    }
}