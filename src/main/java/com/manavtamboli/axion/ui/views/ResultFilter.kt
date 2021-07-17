package com.manavtamboli.axion.ui.views

import android.text.InputFilter
import android.text.InputType
import android.text.Spanned
import android.widget.EditText
import androidx.databinding.BindingAdapter

class ResultFilter(private val predicate : (resultingText : String) -> Boolean) : InputFilter {

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        return if (source != null && dest != null){
            val res = dest.substring(0, dstart) + source.substring(start, end) + dest.substring(dend)
            if (predicate(res)) null else ""
        } else null
    }

    companion object {

        fun EditText.allowIfResult(predicate: (resultingText: String) -> Boolean){
            filters = filters.plus(ResultFilter(predicate))
        }

        fun EditText.allowRange(range : IntRange){
            if (inputType != InputType.TYPE_CLASS_NUMBER) throw Exception("Input type specified in the EditText must be Number")
            allowIfResult{
                it.toIntOrNull()?.let { int ->
                    int in range
                } ?: return@allowIfResult false
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["maxIntValue", "minIntValue"], requireAll = false)
        fun allowRange(editText: EditText, maxIntValue : Int? = null, minIntValue: Int? = null){
            editText.allowRange((minIntValue ?: Int.MIN_VALUE)..(maxIntValue ?: Int.MAX_VALUE))
        }
    }
}