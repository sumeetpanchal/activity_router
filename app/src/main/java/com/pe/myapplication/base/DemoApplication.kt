package com.pe.myapplication.base

import android.app.Application
import com.pe.myapplication.*
import com.pe.myapplication.router.ActivityRouter

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ActivityRouter
            .add("/main", MainActivity::class.java)
            .add("/activity1/:intentExtra", Activity1::class.java)
            .add("/activity2/:intentExtra", Activity2::class.java)
            .add("/activity3/:intentExtra", Activity3::class.java)
            .add("/activity4/:intentExtra", Activity4::class.java)
            .add("/activity5/:intentExtra", Activity5::class.java)
    }

}