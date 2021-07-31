package com.example.method_channel_test

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import androidx.annotation.NonNull
import androidx.core.app.NotificationCompat
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.graphics.Color
import android.content.Context

class MainActivity: FlutterActivity() {
    companion object {
        private val CHANNEL = "com.example.methodchannel/method"
        private const val METHOD1 = "Method1"
        private const val METHOD2 = "Method2"
        private val NOTIFICATION_CHANNEL = "primary_channel_id"
        private var notificationWorkManager: NotificationManager? = null
        private val NOTIFICATION_ID = 101
        
    }


    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
      super.configureFlutterEngine(flutterEngine)
      MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->

          if (call.method == METHOD1) {
              result.success(printKotlinMessage())
          } else if (call.method == METHOD2) {
            createChannel()
            val flutterTittle = call.argument<String>("flutterTitle")
            result.success(callNotification(flutterTittle!!.toString()))
          } else {
              result.notImplemented()
          }
      }
    }

    private fun printKotlinMessage(): String {
        return "Hi i'm a message from native :D"
    }

    private fun callNotification(flutterTitle: String):String {
        val notificationBuilder: NotificationCompat.Builder =
         getNotificationBuilder(flutterTitle)

        notificationWorkManager?.notify(NOTIFICATION_ID, notificationBuilder.build())

        return "Notification Successfully send";
    }

    private fun getNotificationBuilder(notificationName: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL)
        .setSmallIcon(R.drawable.notification_icon_background)
        .setContentTitle(notificationName)
        .setContentText("Hi, This is a Kotlin notification with flutter title")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }

    private fun createChannel() {
        notificationWorkManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Title"
            val description = "Notification Description"
            val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL,name,NotificationManager.IMPORTANCE_HIGH)

            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.description = description

            notificationWorkManager?.createNotificationChannel(notificationChannel)
        }
    }
}