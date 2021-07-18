package com.manavtamboli.axion.core


// TODO
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.content.Intent
//import android.net.Uri
//import androidx.fragment.app.Fragment
//import androidx.activity.ComponentActivity
//
//@SuppressLint("QueryPermissionsNeeded")
//private fun dial(activity : Activity, phoneNumber: String) : Boolean {
//    val intent = Intent(Intent.ACTION_DIAL).apply {
//        data = Uri.parse("tel:$phoneNumber")
//    }
//    return (intent.resolveActivity(activity.packageManager) != null)
//        .also { if(it) activity.startActivity(intent) }
//}
//
//private fun dialNew(activity: Activity, phoneNumber: String) {
//    runCatching {
//        val sendIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("tel:$phoneNumber"))
//        activity.startActivity(Intent.createChooser(sendIntent, "Dial"))
//    }
//}
//
//private fun message(activity: Activity, phoneNumber: String, title : String) = runCatching {
//    val sendIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$phoneNumber" ))
//    activity.startActivity(Intent.createChooser(sendIntent, title))
//}.isSuccess
//
//fun ComponentActivity.dial(phoneNumber: String) = dial(this, phoneNumber)
//fun ComponentActivity.message(phoneNumber: String, title: String = "Message") = message(phoneNumber, )
//
//fun Fragment.dial(phoneNumber: String) = dial(requireActivity(), phoneNumber)