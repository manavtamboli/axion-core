package com.manavtamboli.axion.extensions

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

inline fun <reified T> ComponentActivity.openActivity(){
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T> ComponentActivity.openActivity(bundle: Bundle){
    val intent = Intent(this, T::class.java).apply { putExtras(bundle) }
    startActivity(intent)
}

inline fun <reified T> ComponentActivity.toActivity(){
    openActivity<T>()
    finish()
}

inline fun <reified T> ComponentActivity.toActivity(bundle: Bundle){
    openActivity<T>(bundle)
    finish()
}