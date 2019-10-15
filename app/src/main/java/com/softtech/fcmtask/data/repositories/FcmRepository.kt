package com.softtech.fcmtask.data.repositories

import com.softtech.fcmtask.data.sources.remote.apis.FcmAPI
import com.softtech.fcmtask.entities.APIResponse
import com.softtech.fcmtask.entities.RegisterParams
import io.reactivex.Single

class FcmRepository(private val API: FcmAPI) {
    
    fun callRegisterFcmTokenAPI(registerParams: RegisterParams): Single<APIResponse> {
        return API.registerFcmAPI(registerParams).doOnSuccess { response->
        }.map {response->response}
    }
}