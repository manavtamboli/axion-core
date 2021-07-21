package com.manavtamboli.axion.core.perm

fun interface PermissionRequester {
    fun request(permission : String)
}

interface PermissionRequesterScope {
    fun onPermissionResult(block: (Boolean) -> Unit)
    fun onShowRequestPermissionRationale(block : () -> Unit)
}

fun interface MultiPermissionRequester {
    fun request(permissions : Collection<String>)
}

interface MultiPermissionRequesterScope {
    fun onPermissionsResult(block: (Map<String, Boolean>) -> Unit)
    fun onShowRequestPermissionRationale(block: (List<String>) -> Unit)
}