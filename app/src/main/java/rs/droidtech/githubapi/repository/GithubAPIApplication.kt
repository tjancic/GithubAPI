package rs.droidtech.githubapi.repository

import android.app.Application
import rs.droidtech.githubapi.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree


class GithubAPIApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        enableDebuggingForKotlinCoroutines()
        setupHandlerForUncaughtExceptions()
    }

    private fun setupHandlerForUncaughtExceptions() {
        Thread.setDefaultUncaughtExceptionHandler { _, e -> Timber.e(e) }
    }

    private fun enableDebuggingForKotlinCoroutines() {
        System.setProperty("kotlinx.coroutines.debug", if (BuildConfig.DEBUG) "on" else "off")
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}