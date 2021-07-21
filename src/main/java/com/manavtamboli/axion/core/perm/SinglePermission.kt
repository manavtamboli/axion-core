package com.manavtamboli.axion.core.perm

import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


private fun createRequester(
    contextProducer : () -> Context,
    launcherProducer : (ActivityResultContract<String, Boolean>,ActivityResultCallback<Boolean>) -> ActivityResultLauncher<String>,
    shouldShowRequestPermissionRationale : (String) -> Boolean,
    block : PermissionRequesterScope.() -> Unit
): PermissionRequester {

    var onResult : ((Boolean) -> Unit)? = null
    var shouldShow : (() -> Unit)? = null

    object : PermissionRequesterScope {
        override fun onPermissionResult(block: (Boolean) -> Unit) { onResult = block }
        override fun onShowRequestPermissionRationale(block: () -> Unit) { shouldShow = block }
    }.apply(block)

    val launcher = launcherProducer(ActivityResultContracts.RequestPermission()) { onResult?.invoke(it) }
    return PermissionRequester {
        when {
            ContextCompat.checkSelfPermission(contextProducer(), it) == PERMISSION_GRANTED -> onResult?.invoke(true)
            shouldShowRequestPermissionRationale(it) -> shouldShow?.invoke()
            else -> launcher.launch(it)
        }
    }
}

@Suppress("FunctionName")
fun Fragment.PermissionRequester(block : PermissionRequesterScope.() -> Unit): PermissionRequester = createRequester(
    this::requireContext,
    this::registerForActivityResult,
    this::shouldShowRequestPermissionRationale,
    block
)

@Suppress("FunctionName")
fun ComponentActivity.PermissionRequester(block : PermissionRequesterScope.() -> Unit): PermissionRequester = createRequester(
    { this },
    this::registerForActivityResult,
    this::shouldShowRequestPermissionRationale,
    block
)