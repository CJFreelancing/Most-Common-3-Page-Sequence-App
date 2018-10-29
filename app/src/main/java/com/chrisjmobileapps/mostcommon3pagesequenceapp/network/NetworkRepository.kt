package com.chrisjmobileapps.mostcommon3pagesequenceapp.network

import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.ApacheLogParser
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.PageListParser
import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.PageSequenceFrequency
import io.reactivex.Single

class NetworkRepository(private val threePageSequenceApi: ThreePageSequenceApi) {

    fun getApacheLog(): Single<List<PageSequenceFrequency>> {
        return threePageSequenceApi.getApacheLog()
                .map { responseBody ->
                    val pageList = ApacheLogParser.getPageList(responseBody.string())
                    val pageSequenceFrequencyList = PageListParser.getPageSequenceFrequencyList(pageList)

                    pageSequenceFrequencyList
                }
    }
}