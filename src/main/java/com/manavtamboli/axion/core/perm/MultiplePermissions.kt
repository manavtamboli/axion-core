package com.manavtamboli.axion.core.perm

import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

private fun createRequester(
    launcherProducer : (ActivityResultContract<Array<String>, Map<String, Boolean>>, ActivityResultCallback<Map<String, Boolean>>) -> ActivityResultLauncher<Array<String>>,
    shouldShowRequestPermissionRationale : (String) -> Boolean,
    block : MultiPermissionRequesterScope.() -> Unit
) : MultiPermissionRequester {

    var onResult : ((Map<String, Boolean>) -> Unit)? = null
    var shouldShow : ((List<String>) -> Unit)? = null

    object : MultiPermissionRequesterScope {
        override fun onPermissionsResult(block: (Map<String, Boolean>) -> Unit) { onResult = block }
        override fun onShowRequestPermissionRationale(block: (List<String>) -> Unit) { shouldShow = block }
    }.apply(block)

    val launcher = launcherProducer(ActivityResultContracts.RequestMultiplePermissions()) { onResult?.invoke(it) }
    return MultiPermissionRequester { perms ->
        val filtered = perms.filter { shouldShowRequestPermissionRationale(it) }
        if (filtered.isNotEmpty()) shouldShow?.let { it(filtered) }
        else launcher.launch(perms.toTypedArray())
    }
}

@Suppress("FunctionName")
fun Fragment.MultiPermissionRequester(block : MultiPermissionRequesterScope.() -> Unit) = createRequester(
    this::registerForActivityResult,
    this::shouldShowRequestPermissionRationale,
    block
)

@Suppress("FunctionName")
fun ComponentActivity.MultiPermissionRequester(block : MultiPermissionRequesterScope.() -> Unit) = createRequester(
    this::registerForActivityResult,
    this::shouldShowRequestPermissionRationale,
    block
)