package com.chrisjmobileapps.mostcommon3pagesequenceapp.dagger.module

import android.content.Context
import com.chrisjmobileapps.mostcommon3pagesequenceapp.dagger.ThreePageSequenceApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(context: Context) {
    private val context = context.applicationContext

    @ThreePageSequenceApplicationScope
    @Provides
    fun context() : Context {
        return context
    }
}