package com.pe.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.pe.myapplication.base.BaseActivity
import com.pe.myapplication.router.ActivityRouter

class Activity3 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = "Activity3"

        findViewById<Button>(R.id.btn1).setOnClickListener { view ->
            ActivityRouter.route(this, "/activity4/Data-From-Activity3")
        }

        findViewById<Button>(R.id.btn2).setOnClickListener { view ->
            finish()
        }

        findViewById<TextView>(R.id.tv_data).text = extraData
    }

}