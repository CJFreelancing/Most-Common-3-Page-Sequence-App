package com.chrisjmobileapps.mostcommon3pagesequenceapp.dagger

import android.content.Context
import com.chrisjmobileapps.mostcommon3pagesequenceapp.ThreePageSequenceApplication
import com.chrisjmobileapps.mostcommon3pagesequenceapp.dagger.module.ContextModule
import com.chrisjmobileapps.mostcommon3pagesequenceapp.dagger.module.NetworkModule
import com.chrisjmobileapps.mostcommon3pagesequenceapp.network.NetworkService
import dagger.Component

@ThreePageSequenceApplicationScope
@Component(modules = arrayOf(ContextModule::class, NetworkModule::class))
interface ThreePageSequenceApplicationComponent {

    fun provideNetworkService(): NetworkService

    fun provideContext(): Context

    fun injectThreePageSequenceApplicationComponent(threePageSequenceApplication: ThreePageSequenceApplication)
}