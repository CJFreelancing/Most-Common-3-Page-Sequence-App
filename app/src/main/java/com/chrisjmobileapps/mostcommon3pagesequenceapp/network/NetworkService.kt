package com.chrisjmobileapps.mostcommon3pagesequenceapp.network

import com.chrisjmobileapps.mostcommon3pagesequenceapp.main.model.PageSequenceFrequency
import io.reactivex.Single
import okhttp3.ResponseBody

class NetworkService(private val networkRepository: NetworkRepository) {

    fun getApacheLog() : Single<List<PageSequenceFrequency>> {
        return networkRepository.getApacheLog()
    }
}