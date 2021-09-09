package com.manavtamboli.axion.navigation

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import com.manavtamboli.axion.lifecycle.onStart

fun Fragment.navigateBack() = requireActivity().onBackPressed()

fun Fragment.navigateTo(dirs : NavDirections) = findNavController().navigate(dirs)

fun Fragment.navigateTo(dirs: NavDirections, extras : Navigator.Extras) = findNavController().navigate(dirs, extras)

inline fun Fragment.navigateBackIf(crossinline predicate : () -> Boolean): BackPressedRegistration {
    val callback = object : OnBackPressedCallback(true){

        val registration = BackPressedRegistration { remove() }

        override fun handleOnBackPressed() {
            if (predicate()){
                registration.remove()
                navigateBack()
            }
        }
    }
    onStart {
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
    return callback.registration
}