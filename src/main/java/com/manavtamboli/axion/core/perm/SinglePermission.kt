package com.manavtamboli.axion.core.perm

import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.manavtamboli.axion.lifecycle.context
import com.manavtamboli.axion.lifecycle.getLauncher


private fun LifecycleOwner.createRequester(
    shouldShowRequestPermissionRationale: (String) -> Boolean,
    block: PermissionRequesterScope.() -> Unit
): PermissionRequester {

    var onResult : ((Boolean) -> Unit)? = null
    var shouldShow : (() -> Unit)? = null

    object : PermissionRequesterScope {
        override fun onPermissionResult(block: (Boolean) -> Unit) { onResult = block }
        override fun onShowRequestPermissionRationale(block: () -> Unit) { shouldShow = block }
    }.apply(block)

    val launcher = getLauncher(RequestPermission()) { onResult?.invoke(it) }
    return PermissionRequester {
        when {
            ContextCompat.checkSelfPermission(context, it) == PERMISSION_GRANTED -> onResult?.invoke(true)
            shouldShowRequestPermissionRationale(it) -> shouldShow?.invoke()
            else -> launcher.launch(it)
        }
    }
}

@Suppress("FunctionName")
fun Fragment.PermissionRequester(block : PermissionRequesterScope.() -> Unit): PermissionRequester = createRequester(
    this::shouldShowRequestPermissionRationale,
    block
)

@Suppress("FunctionName")
fun ComponentActivity.PermissionRequester(block : PermissionRequesterScope.() -> Unit): PermissionRequester = createRequester(
    this::shouldShowRequestPermissionRationale,
    block
)