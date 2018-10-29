package com.chrisjmobileapps.mostcommon3pagesequenceapp

import android.app.Activity
import android.app.Application
import android.content.Context
import com.chrisjmobileapps.mostcommon3pagesequenceapp.dagger.DaggerThreePageSequenceApplicationComponent
import com.chrisjmobileapps.mostcommon3pagesequenceapp.dagger.ThreePageSequenceApplicationComponent
import com.chrisjmobileapps.mostcommon3pagesequenceapp.dagger.module.ContextModule

class ThreePageSequenceApplication : Application() {

    lateinit var threePageSequenceApplicationComponent: ThreePageSequenceApplicationComponent

    companion object {
        fun get(activity: Activity): ThreePageSequenceApplication {
            return activity.application as ThreePageSequenceApplication
        }

        fun get(context: Context): ThreePageSequenceApplication {
            return context.applicationContext as ThreePageSequenceApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

        threePageSequenceApplicationComponent = DaggerThreePageSequenceApplicationComponent.builder()
                .contextModule(ContextModule(this)).build()

        threePageSequenceApplicationComponent.injectThreePageSequenceApplicationComponent(this)
    }
}