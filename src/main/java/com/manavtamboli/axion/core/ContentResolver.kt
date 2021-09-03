package com.manavtamboli.axion.core

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns

fun ContentResolver.getSize(uri: Uri) = query(uri, null, null, null, null)
    ?.use { cursor ->
        cursor.moveToFirst()
        cursor.getLong(cursor.getColumnIndex(OpenableColumns.SIZE))
    } ?: throw IllegalStateException("Cannot open query.")