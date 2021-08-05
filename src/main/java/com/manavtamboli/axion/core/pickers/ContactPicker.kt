package com.manavtamboli.axion.core.pickers

import android.Manifest.permission.READ_CONTACTS
import android.content.ContentResolver
import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Phone.*
import android.provider.ContactsContract.Contacts._ID
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.manavtamboli.axion.core.Arc
import com.manavtamboli.axion.core.perm.PermissionRequester
import com.manavtamboli.axion.lifecycle.getLauncher
import kotlinx.coroutines.launch

private fun loadContact(uri: Uri, types : List<Int> = listOf(TYPE_MOBILE, TYPE_HOME, TYPE_WORK, TYPE_MAIN, TYPE_CUSTOM)): Pair<String, List<Pair<Int, String>>> {
    val contentResolver : ContentResolver by Arc
    val projection = arrayOf(NUMBER, TYPE)
    val selection = "$CONTACT_ID = ? AND $TYPE IN (${types.joinToString(",")})"

    val (id, name) = contentResolver.query(uri, arrayOf(_ID, DISPLAY_NAME), null, null, null)?.use {
        if (!it.moveToFirst()) throw Exception("Empty Cursor. Cannot find id.")
        it.getString(it.getColumnIndexOrThrow(_ID)) to it.getString(it.getColumnIndexOrThrow(DISPLAY_NAME))
    } ?: throw Exception("Content Provider failed.")

    val numbers = mutableListOf<Pair<Int, String>>()
    contentResolver.query(CONTENT_URI, projection, selection, arrayOf(id), null)?.use {
        val numberIndex = it.getColumnIndexOrThrow(NUMBER)
        val typeIndex = it.getColumnIndexOrThrow(TYPE)
        while (it.moveToNext()){
            val number = it.getString(numberIndex)
            val type = it.getInt(typeIndex)
            if (type in types) numbers.add(type to number)
        }
    } ?: throw Exception("Content Provider failed.")

    return name to numbers
}

fun Fragment.ContactPicker(block : (String, List<Pair<Int, String>>) -> Unit): Picker {
    val launcher = getLauncher(ActivityResultContracts.PickContact()){
        lifecycleScope.launch {
            val (name, numbers) = loadContact(it)
            block(name, numbers)
        }
    }
    val requester = PermissionRequester {
        onPermissionResult { if (it) launcher.launch() }
    }
    return Picker { requester.request(READ_CONTACTS) }
}