package com.softtech.fcmtask.fcm

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.softtech.fcmtask.application.ApplicationClass
import com.softtech.fcmtask.base.notifications.NotificationsUtil
import com.softtech.fcmtask.di.DIConstants
import com.softtech.fcmtask.domain.usecases.RegisterFcmUseCase
import com.softtech.fcmtask.features.splash.SplashActivity
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import timber.log.Timber


class FirebaseMessagingService : FirebaseMessagingService() {

    private val fcmUseCase:RegisterFcmUseCase by inject()
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.i("remoteMessage $")


        remoteMessage?.notification?.apply {
            val notificationIntent = Intent(ACTION_VIEW)

            notificationIntent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
    Intent.FLAG_ACTIVITY_SINGLE_TOP or
    Intent.FLAG_ACTIVITY_NEW_TASK)


            notificationIntent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            notificationIntent.component = ApplicationClass.currentActivity ?: splashComponentName()
            val pendingIntent = createPendingIntent(notificationIntent)
            showNotification(this.title.toString(),this.body.toString(), pendingIntent)
        }
    }

    private fun showNotification(title:String,message: String, pendingIntent: PendingIntent) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        NotificationsUtil(this,notificationManager).createAlertNotification(id = 1987,title = title,text = message,pendingIntent = pendingIntent)
    }



    private fun createPendingIntent(intent: Intent): PendingIntent =
            PendingIntent.getActivity(this, 0, intent,0)



    private fun splashComponentName() = ComponentName(this, SplashActivity::class.java)

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        Timber.i("onNewToken $newToken")
        newToken.apply {
            Timber.d("FCM token %s", this)
        }

    }

}