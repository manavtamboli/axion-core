package com.manavtamboli.axion.binding

import android.app.Dialog
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.manavtamboli.axion.core.R

open class FloatingBottomSheetFragment<B : ViewBinding>(bindingClass: Class<B>) : BindingBottomSheetFragment<B>(bindingClass){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.ArcBottomSheet_Floating).apply{
            setOnShowListener {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }
}