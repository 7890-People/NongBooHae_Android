package com.konkuk.nongboohae.util

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections

class Extensions {
    companion object {
        fun NavController.safeNavigate(direction: NavDirections) {
            currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
        }

        fun NavController.safeNavigate(
            @IdRes currentDestinationId: Int,
            @IdRes id: Int,
            args: Bundle? = null
        ) {
            if (currentDestinationId == currentDestination?.id) {
                navigate(id, args)
            }
        }
    }
}