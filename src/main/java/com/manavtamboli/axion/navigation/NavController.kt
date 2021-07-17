package com.manavtamboli.axion.navigation

import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigator

fun NavController.safeNavigate(dirs: NavDirections) = kotlin.runCatching { navigate(dirs) }.isSuccess

fun NavController.safeNavigate(dirs: NavDirections, extras: Navigator.Extras) = kotlin.runCatching { navigate(dirs, extras) }.isSuccess


fun NavController.attachGraph(@NavigationRes navResId : Int, bundle : Bundle? = null) = kotlin.runCatching { graph }
    .getOrElse { bundle?.let { setGraph(navResId, it) } ?: setGraph(navResId) ; graph }