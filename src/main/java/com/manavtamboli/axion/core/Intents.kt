package com.manavtamboli.axion.core

import android.content.Intent
import android.content.Intent.*
import android.net.Uri.parse
import androidx.lifecycle.LifecycleOwner
import com.manavtamboli.axion.lifecycle.activity

fun LifecycleOwner.dial(phoneNumber: String, title : String = "Dial"){
    kotlin.runCatching {
        val sendIntent = Intent(ACTION_DIAL, parse("tel:$phoneNumber"))
        activity.startActivity(createChooser(sendIntent, title))
    }
}

fun LifecycleOwner.message(phoneNumber: String, title: String = "Message"){
    kotlin.runCatching {
        val sendIntent = Intent(ACTION_SENDTO, parse("smsto:$phoneNumber" ))
        activity.startActivity(createChooser(sendIntent, title))
    }
}