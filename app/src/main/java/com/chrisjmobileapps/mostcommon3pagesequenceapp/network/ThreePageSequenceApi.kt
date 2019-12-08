package com.chrisjmobileapps.mostcommon3pagesequenceapp.network

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ThreePageSequenceApi {

    @GET("eabc023caaa09ae007ad36ad7a7a723c/raw/9036d6a6aa7d1210f6166fee82c64c71c2121c35/Apache.log")
    fun getApacheLog() : Single<ResponseBody>
}