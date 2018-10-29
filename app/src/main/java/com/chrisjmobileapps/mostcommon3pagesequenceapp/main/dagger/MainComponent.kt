package com.chrisjmobileapps.mostcommon3pagesequenceapp.main.dagger

import com.chrisjmobileapps.mostcommon3pagesequenceapp.MainActivity
import com.chrisjmobileapps.mostcommon3pagesequenceapp.dagger.ThreePageSequenceApplicationComponent
import dagger.Component

@MainScope
@Component(modules = arrayOf(MainModule::class), dependencies = arrayOf(ThreePageSequenceApplicationComponent::class))
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}