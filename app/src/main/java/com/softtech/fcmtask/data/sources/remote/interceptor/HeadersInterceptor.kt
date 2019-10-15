package com.softtech.fcmtask.data.sources.remote.interceptor

import com.softtech.fcmtask.di.DIConstants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.standalone.KoinComponent
import org.koin.standalone.get

class HeadersInterceptor() : Interceptor, KoinComponent {

    private val keyApiKey = "x-pushbots-appid"
    private val apiKeyValue = "5d258e58b7941208c73fcfb7"

    override fun intercept(chain: Interceptor.Chain): Response = chain.proceed(createNewRequestWithApiKey(chain.request()))

    private fun createNewRequestWithApiKey(oldRequest: Request): Request {

        val requestBuilder = oldRequest.newBuilder()
                .addHeader(keyApiKey, apiKeyValue)
        return requestBuilder.build()
    }
}