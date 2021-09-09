package com.manavtamboli.axion.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.launch
import androidx.fragment.app.Fragment

interface VoiceInput {
    fun listen()
}

fun Fragment.voiceInput(prompt: String? = null, callback: (String) -> Unit) : VoiceInput =
    object : VoiceInputImpl(prompt, callback){
        override val launcher = registerForActivityResult(contract, contractCallback)
    }

fun ComponentActivity.voiceInput(prompt: String? = null, callback: (String) -> Unit) : VoiceInput =
    object : VoiceInputImpl(prompt, callback) {
        override val launcher = registerForActivityResult(contract, contractCallback)
    }

private abstract class VoiceInputImpl(
    private val prompt : String?,
    private val callback : (String) -> Unit
) : VoiceInput {

    abstract val launcher : ActivityResultLauncher<Unit>

    protected val contract = object : ActivityResultContract<Unit, String?>() {
        override fun createIntent(context: Context, input: Unit?): Intent {
            return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                .putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                .apply {
                    prompt?.let {
                        putExtra(RecognizerIntent.EXTRA_PROMPT, it)
                    }
                }
        }

        override fun parseResult(resultCode: Int, intent: Intent?): String? {
            return if (resultCode == Activity.RESULT_OK && intent != null) {
                intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    ?.getOrNull(0)
            } else null
        }
    }
    protected val contractCallback = ActivityResultCallback<String?> { it?.let(callback) }

    final override fun listen() {
        launcher.launch()
    }
}