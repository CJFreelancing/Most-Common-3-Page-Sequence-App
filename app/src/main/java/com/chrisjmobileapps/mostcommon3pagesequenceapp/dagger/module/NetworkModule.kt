package com.chrisjmobileapps.mostcommon3pagesequenceapp.dagger.module

import android.content.Context
import com.chrisjmobileapps.mostcommon3pagesequenceapp.dagger.ThreePageSequenceApplicationScope
import com.chrisjmobileapps.mostcommon3pagesequenceapp.network.NetworkRepository
import com.chrisjmobileapps.mostcommon3pagesequenceapp.network.NetworkService
import com.chrisjmobileapps.mostcommon3pagesequenceapp.network.ThreePageSequenceApi
import com.chrisjmobileapps.mostcommon3pagesequenceapp.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    //Create a cache object part 1.
    @ThreePageSequenceApplicationScope
    @Provides
    fun httpCacheDirectory(context: Context): File {
        return File(context.getCacheDir(), Constants.HTTP_CACHE_DIR)
    }

    //Create a cache object part 2.
    @ThreePageSequenceApplicationScope
    @Provides
    fun cache(httpCacheDirectory: File): Cache {
        return Cache(httpCacheDirectory, Constants.HTTP_CACHE_SIZE)
    }

    //Create a network cache interceptor, setting the max age to 1 minute
    @ThreePageSequenceApplicationScope
    @Provides
    fun networkCacheInterceptor(): Interceptor {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val response = chain.proceed(chain.request())

                val cacheControl = CacheControl.Builder()
                        .maxAge(1, TimeUnit.MINUTES)
                        .build()

                return response.newBuilder()
                        .header(Constants.CACHE_CONTROL, cacheControl.toString()).build()
            }
        }
    }

    //Create a logging interceptor
    @ThreePageSequenceApplicationScope
    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        return loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    //Create the httpClient, configure it
    // with cache, network cache interceptor and logging interceptor
    @ThreePageSequenceApplicationScope
    @Provides
    fun okHttpClient(cache: Cache,
                     networkCacheInterceptor: Interceptor,
                     loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(networkCacheInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
    }

    //Create the Retrofit with the httpClient
    @ThreePageSequenceApplicationScope
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://dev.inspiringapps.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @ThreePageSequenceApplicationScope
    @Provides
    fun threePageSequenceApi (retrofit: Retrofit) : ThreePageSequenceApi {
        return retrofit.create(ThreePageSequenceApi ::class.java)
    }

    @ThreePageSequenceApplicationScope
    @Provides
    fun networkRepository(threePageSequenceApi: ThreePageSequenceApi) : NetworkRepository {
        return NetworkRepository(threePageSequenceApi)
    }

    @ThreePageSequenceApplicationScope
    @Provides
    fun networkService(networkRepository: NetworkRepository) : NetworkService {
        return NetworkService(networkRepository)
    }
}