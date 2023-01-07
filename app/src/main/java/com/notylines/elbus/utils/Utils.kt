package com.notylines.elbus.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.navigation.NavController


fun checkPermissions(
    context: Context,
    onPermissionUpdate: (Boolean) -> Unit,
    onPermissionRequest: () -> Unit
) {

    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.INTERNET
        ) == PERMISSION_GRANTED
    ) {
        onPermissionUpdate(true)
    } else {
        onPermissionRequest()
    }

}
