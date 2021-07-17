package com.manavtamboli.axion.ui

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel

fun toast(context: Context, message : Any?, length : Int = LENGTH_SHORT) = Toast.makeText(context, message.toString(), length).show()

fun Fragment.toast(message: Any?, length: Int = LENGTH_SHORT) = toast(requireContext(), message, length)

fun ComponentActivity.toast(message: Any?, length: Int = LENGTH_SHORT) = toast(this, message, length)

fun AndroidViewModel.toast(message: Any?, length: Int = LENGTH_SHORT) = toast(getApplication(), message, length)