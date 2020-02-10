package com.ecomp.notifications

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: NotificationCompat.Builder
    private val channelId = "com.ecomp.notifications"
    private val description = "notification"


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        btn_notify.setOnClickListener{
                notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_DEFAULT)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.RED
                notificationManager.createNotificationChannel(notificationChannel)

                builder = NotificationCompat.Builder(this, channelId).setContentTitle("App Name")
                    .setContentText("You Have Been Notified")
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher))
                    .setContentIntent(pendingIntent)

            with(NotificationManagerCompat.from(this)){
                notify(1234, builder.build())
            }

        }


    }
}
