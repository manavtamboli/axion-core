package com.manavtamboli.axion.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun FragmentManager.findNavController(@IdRes containerResId : Int) : NavController {
    return findFragmentById(containerResId).run {
        require(this is NavHostFragment) { "Given containerResId must be associated to a NavHostFragment, but is $this." }
        return@run navController
    }
}