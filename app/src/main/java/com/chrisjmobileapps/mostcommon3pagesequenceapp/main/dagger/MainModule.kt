package com.chrisjmobileapps.mostcommon3pagesequenceapp.main.dagger

import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.MainPresenter
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.navigator.MainPresenterToViewNavigator
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.view.MainAdapter
import com.chrisjmobileapps.mostcommon3pagesequenceapp.network.NetworkService
import dagger.Module
import dagger.Provides

@Module
class MainModule(val mainPresenterToViewNavigator: MainPresenterToViewNavigator) {

    @MainScope
    @Provides
    fun mainPresenterToViewNavigator() : MainPresenterToViewNavigator {
        return mainPresenterToViewNavigator
    }

    @MainScope
    @Provides
    fun mainPresenter(networkService: NetworkService, mainPresenterToViewNavigator: MainPresenterToViewNavigator) : MainPresenter {
        return MainPresenter(networkService, mainPresenterToViewNavigator)
    }

    @MainScope
    @Provides
    fun mainAdapter(): MainAdapter {
        return MainAdapter()
    }
}