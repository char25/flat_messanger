package com.flat.internal

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.flat.internal.constant.FbSing
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class FbService : Service() {

    private val TAG = "FbService"
    private val CHANNEL_ID = "FB MESSAGE ASYNC"

    companion object {

        var Ref : FbService? = null

        fun startService(context: Context, message: String) {
            if (Ref == null) {
                val startIntent = Intent(context, FbService::class.java)
                startIntent.putExtra("inputExtra", message)
                ContextCompat.startForegroundService(context, startIntent)
            }
        }

        fun stopService(context: Context) {
            val stopIntent = Intent(context, FbService::class.java)
            context.stopService(stopIntent)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //do heavy work on a background thread
        val input = intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
                this,
                0, notificationIntent, 0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Flat working in background.")
                .setContentText(input)
                .setSmallIcon(R.drawable.app_icon)
                .setContentIntent(pendingIntent)
                .build()
        startForeground(1, notification)

        Ref = this
        //stopSelf();
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {


        /*var UserRef = FbSing.Instance().FbDb!!.getReference("Users/" + FbSing.Instance().FbAuth!!.uid + "/Messages")
        UserRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()) {


                }
            }

            override fun onCancelled(databaseError: DatabaseError) { Log.d(TAG, databaseError.toString()) }
        })*/

        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }
}