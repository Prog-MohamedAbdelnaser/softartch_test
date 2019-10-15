package com.softtech.fcmtask.features.home.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softtech.fcmtask.domain.usecases.RegisterFcmUseCase
import com.softtech.fcmtask.entities.APIResponse
import com.softtech.fcmtask.entities.RegisterParams
import com.softtech.fcmtask.features.common.CommonState

class HomeViewModel(private val fcmUseCase: RegisterFcmUseCase):ViewModel() {
     val liveData=MutableLiveData<CommonState<APIResponse>>()
    fun sendFcmToken(token:String) {
        fcmUseCase.registerFcmToken(liveData, RegisterParams(token = token))
    }

}