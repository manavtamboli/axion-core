package com.manavtamboli.axion.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BindingBottomSheetFragment<B : ViewBinding>(bindingClass : Class<B>) : BottomSheetDialogFragment(), BindingComponent<B> by BindingComponentImpl(bindingClass) {

    /**
     * Property to access the generated binding.
     *
     * Must be accessed starting from [onCreateView] and valid upto [onDestroyView].
     * */
    final override val binding: B get() = super.binding


    final override fun generateBinding(inflater: LayoutInflater, viewGroup: ViewGroup?, lifecycleOwner: LifecycleOwner) {
        super.generateBinding(inflater, viewGroup, lifecycleOwner)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        generateBinding(inflater, container, viewLifecycleOwner)
        return binding.root
    }
}

