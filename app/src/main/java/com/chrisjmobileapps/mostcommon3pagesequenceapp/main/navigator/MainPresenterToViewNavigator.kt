package com.chrisjmobileapps.mostcommon3pagesequenceapp.main.navigator

import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.PageSequenceFrequency

interface MainPresenterToViewNavigator {
    fun setPageSequenceFrequencyListToAdapter(pageSequenceFrequencyList:  List<PageSequenceFrequency>?)
    fun showProgressDialog()
    fun dismissProgressDialog()
}