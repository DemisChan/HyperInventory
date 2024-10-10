package com.hyperio.hyperinventory

import android.app.Application
import com.hyperio.hyperinventory.data.repository.RepositoryContainer
import com.hyperio.hyperinventory.data.repository.RepositoryImpl

class InventoryApplication : Application() {

    /**
     * Container instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: RepositoryContainer

    override fun onCreate() {
        super.onCreate()
        container = RepositoryImpl(this)
    }
}
