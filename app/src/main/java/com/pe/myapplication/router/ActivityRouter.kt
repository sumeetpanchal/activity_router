package com.pe.myapplication.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Pair
import com.pe.myapplication.util.Utils.Companion.matchUri
import java.util.*

object ActivityRouter {

    private const val tag = "ActivityRouter"

    private val mRouting: MutableList<Pair<String?, Class<out Activity?>?>> =
        ArrayList()

    // TODO validate uri, throw RuntimeException if it's invalid
    fun add(uri: String?, a: Class<out Activity?>?): ActivityRouter {
        mRouting.add(Pair<String?, Class<out Activity?>?>(uri, a))
        return this
    }

    fun route(c: Context, uri: String?): Boolean {
        val intent = findRoute(c, uri)
            ?: return false
        // navigate to the matched activity
        c.startActivity(intent)
        return true
    }

    private fun findRoute(c: Context?, uri: String?): Intent? {
        val properties: MutableMap<String?, String?> = HashMap()
        for (entry in mRouting) {
            properties.clear()
            if (matchUri(uri!!, entry.first!!, properties)) {
                val intent = Intent(c, entry.second)

                // copy parsed uri params into intent extras
                for ((key, value) in properties) {
                    intent.putExtra(key, value)
                }
                return intent
            }
        }
        return null
    }
}