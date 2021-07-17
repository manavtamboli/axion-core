package com.manavtamboli.axion.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController

fun Fragment.navigateBack() = requireActivity().onBackPressed()

fun Fragment.navigateTo(dirs : NavDirections) = findNavController().navigate(dirs)

fun Fragment.navigateTo(dirs: NavDirections, extras : Navigator.Extras) = findNavController().navigate(dirs, extras)