package com.feilinpl.rabbit.utils

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

class GooglePlayServicesUtils {
    companion object {
        fun isGooglePlayServicesAvailable(context: Context): Boolean {
            val googleApiAvailability = GoogleApiAvailability.getInstance()
            val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context)
            return resultCode == ConnectionResult.SUCCESS
        }
    }
}