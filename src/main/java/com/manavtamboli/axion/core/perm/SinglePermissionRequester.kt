package com.manavtamboli.axion.core.perm
//
//import android.content.pm.PackageManager.PERMISSION_GRANTED
//import androidx.activity.ComponentActivity
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//
//@Suppress("FunctionName")
//fun Fragment.PermissionRequester(block : SingleRequestScope.() -> Unit) : Requester {
//
//    var onResult : ((Boolean) -> Unit)? = null
//    var shouldShow : (() -> Unit)? = null
//
//    object : SingleRequestScope {
//        override fun onPermissionResult(block: (Boolean) -> Unit) { onResult = block }
//        override fun onShowRequestPermissionRationale(block: () -> Unit) { shouldShow = block }
//    }.apply(block)
//
//    val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { onResult?.invoke(it) }
//
//    return Requester {
//        when {
//            ContextCompat.checkSelfPermission(requireContext(), it) == PERMISSION_GRANTED -> onResult?.invoke(true)
//            shouldShowRequestPermissionRationale(it) -> shouldShow?.invoke()
//            else -> launcher.launch(it)
//        }
//    }
//}
//
//@Suppress("FunctionName")
//fun ComponentActivity.PermissionRequester(block : SingleRequestScope.() -> Unit) : Requester {
//
//    var onResult : ((Boolean) -> Unit)? = null
//    var shouldShow : (() -> Unit)? = null
//
//    object : SingleRequestScope {
//        override fun onPermissionResult(block: (Boolean) -> Unit) { onResult = block }
//        override fun onShowRequestPermissionRationale(block: () -> Unit) { shouldShow = block }
//    }.apply(block)
//
//    val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { onResult?.invoke(it) }
//
//    return Requester {
//        when {
//            ContextCompat.checkSelfPermission(this, it) == PERMISSION_GRANTED -> onResult?.invoke(true)
//            shouldShowRequestPermissionRationale(it) -> shouldShow?.invoke()
//            else -> launcher.launch(it)
//        }
//    }
//}