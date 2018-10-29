package com.chrisjmobileapps.mostcommon3pagesequenceapp.network

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface ThreePageSequenceApi {

    @GET("Files/IAChallenge/30E02AAA-B947-4D4B-8FB6-9C57C43872A9/Apache.log")
    fun getApacheLog() : Single<ResponseBody>
}