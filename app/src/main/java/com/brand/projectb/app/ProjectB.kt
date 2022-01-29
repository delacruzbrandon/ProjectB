package com.brand.projectb.app

import android.app.Application
import com.brand.projectb.BuildConfig
import com.google.android.libraries.places.api.Places

class ProjectB: Application() {
    override fun onCreate() {
        super.onCreate()
        Places.initialize(this, BuildConfig.BASE_URL)
    }
}