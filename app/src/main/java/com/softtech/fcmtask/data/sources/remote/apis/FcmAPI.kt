package com.softtech.fcmtask.data.sources.remote.apis

import com.softtech.fcmtask.entities.APIResponse
import com.softtech.fcmtask.entities.RegisterParams
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface FcmAPI {

    @POST("2/subscriptions")
    fun registerFcmAPI(@Body param: RegisterParams): Single<APIResponse>

}