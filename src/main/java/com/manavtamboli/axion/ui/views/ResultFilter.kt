package com.manavtamboli.axion.ui.views

import android.text.InputFilter
import android.text.Spanned
import android.widget.EditText
import androidx.databinding.BindingAdapter

fun EditText.allowIfResult(predicate: (resultingText: String) -> Boolean){
    filters += ResultFilter(predicate)
}

fun EditText.allowPattern(regex: Regex) = allowIfResult { string ->
    string.matches(regex)
}

fun EditText.allowRange(intRange: IntRange) = allowIfResult { string ->
    string.toIntOrNull() in intRange
}

fun EditText.allowRange(longRange : LongRange) = allowIfResult { string ->
    string.toLongOrNull() in longRange
}

fun EditText.allowRange(charRange : CharRange) = allowIfResult { string ->
    string.all { char ->
        char in charRange
    }
}


@BindingAdapter("regex")
fun allowRegex(editText: EditText, regex : String){
    editText.allowPattern(regex.toRegex())
}

@BindingAdapter(value = ["maxIntValue", "minIntValue"], requireAll = false)
fun allowIntRange(editText: EditText, maxIntValue : Int? = null, minIntValue: Int? = null){
    editText.allowRange((minIntValue ?: Int.MIN_VALUE)..(maxIntValue ?: Int.MAX_VALUE))
}

@BindingAdapter(value = ["maxLongValue", "minLongValue"], requireAll = false)
fun allowLongRange(editText: EditText, maxLongValue : Long? = null, minLongValue: Long? = null){
    editText.allowRange((minLongValue ?: Long.MIN_VALUE)..(maxLongValue ?: Long.MAX_VALUE))
}

@BindingAdapter(value = ["maxCharValue", "minCharValue"], requireAll = true)
fun allowCharRange(editText: EditText, maxCharValue : Char, minCharValue: Char){
    editText.allowRange(minCharValue..maxCharValue)
}


class ResultFilter(private val predicate : (resultingText : String) -> Boolean) : InputFilter {

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        return if (source != null && dest != null){
            val res = dest.substring(0, dstart) + source.substring(start, end) + dest.substring(dend)
            if (predicate(res)) null else ""
        } else null
    }
}