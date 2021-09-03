package com.manavtamboli.axion.core.pickers

import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.activity.result.contract.ActivityResultContracts.GetMultipleContents
import androidx.lifecycle.LifecycleOwner
import com.manavtamboli.axion.lifecycle.getLauncher

fun LifecycleOwner.ImagePicker(block: (Uri) -> Unit): Picker {
    val launcher = getLauncher(GetContent()) {
        it ?: return@getLauncher
        block(it)
    }
    return Picker { launcher.launch("image/*") }
}

fun LifecycleOwner.MultipleImagePicker(block: (List<Uri>) -> Unit) : Picker {
    val launcher = getLauncher(GetMultipleContents()) { block(it) }
    return Picker { launcher.launch("image/*") }
}

