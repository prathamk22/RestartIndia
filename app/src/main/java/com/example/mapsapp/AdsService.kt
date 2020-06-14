package com.example.mapsapp

import android.app.Service
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Handler
import android.os.IBinder

class AdsService  : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val handler = Handler()
        val runnableCode = object: Runnable {
            override fun run() {
                val intent = Intent(applicationContext, Coupons::class.java)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                handler.postDelayed(this, 300000)
            }
        }
        handler.postDelayed(runnableCode, 300000)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }
}