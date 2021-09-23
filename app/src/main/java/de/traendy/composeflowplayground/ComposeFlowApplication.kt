package de.traendy.composeflowplayground

import android.app.Application
import android.content.Context
import de.traendy.composeflowplayground.data.AppDataBase


var appContext: Context? = null

class ComposeFlowApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        AppDataBase.create(this)
    }
}