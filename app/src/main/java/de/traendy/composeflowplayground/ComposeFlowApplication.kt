package de.traendy.composeflowplayground

import android.app.Application
import de.traendy.composeflowplayground.data.AppDataBase

class ComposeFlowApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        AppDataBase.create(this)
    }
}