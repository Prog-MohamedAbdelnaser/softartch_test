package com.softtech.fcmtask.application

import android.app.Activity
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.softtech.fcmtask.data.sources.remote.di.remoteModule
import com.softtech.fcmtask.BuildConfig
import com.softtech.fcmtask.di.*
import com.softtech.fcmtask.features.home.di.homeModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class ApplicationClass : Application() {
    companion object {
        var currentActivity: ComponentName? = null
        var isTestVersion:Boolean=true
        lateinit var appContext:Application
    }

    override fun onCreate() {
        super.onCreate()
        appContext=this

        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        startKoin(
                this,
                listOf(
                        applicationModule,
                        remoteModule, homeModule
                )
        )

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }


        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {

            override fun onActivityPaused(activity: Activity?) {
            }

            override fun onActivityResumed(activity: Activity?) {
                currentActivity = activity?.componentName
            }

            override fun onActivityStarted(activity: Activity?) {
            }

            override fun onActivityDestroyed(activity: Activity?) {
                currentActivity = null
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
            }

            override fun onActivityStopped(activity: Activity?) {
            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            }

        })
    }

}