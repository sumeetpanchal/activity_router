package com.pe.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.pe.myapplication.base.BaseActivity

class Activity5 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = "Activity5"

        findViewById<Button>(R.id.btn1).visibility = View.GONE

        findViewById<Button>(R.id.btn2).setOnClickListener { view ->
            finish()
        }

        findViewById<TextView>(R.id.tv_data).text = extraData
    }

}