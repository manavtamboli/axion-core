package com.manavtamboli.axion.extensions

import android.content.ContentResolver
import android.content.Context
import androidx.lifecycle.AndroidViewModel

val AndroidViewModel.context : Context get() = getApplication()

val AndroidViewModel.contentResolver : ContentResolver get() = context.contentResolver