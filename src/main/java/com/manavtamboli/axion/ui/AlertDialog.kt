package com.manavtamboli.axion.ui

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

private fun AlertDialog(context : Context, block : MaterialAlertDialogBuilder.() -> Unit): AlertDialog {
    return MaterialAlertDialogBuilder(context)
        .apply(block)
        .create()
}

fun Fragment.MaterialAlertDialogBuilder(block: MaterialAlertDialogBuilder.() -> Unit) : Lazy<AlertDialog> = lazy { AlertDialog(requireContext(), block) }

fun ComponentActivity.MaterialAlertDialogBuilder(block: MaterialAlertDialogBuilder.() -> Unit) : Lazy<AlertDialog> = lazy { AlertDialog(this, block) }