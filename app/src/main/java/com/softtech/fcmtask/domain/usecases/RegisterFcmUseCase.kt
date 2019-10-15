package com.softtech.fcmtask.domain.usecases

import androidx.lifecycle.MutableLiveData
import com.softtech.fcmtask.application.MainModel
import com.softtech.fcmtask.data.repositories.FcmRepository
import com.softtech.fcmtask.entities.APIResponse
import com.softtech.fcmtask.entities.RegisterParams
import com.softtech.fcmtask.features.common.CommonState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterFcmUseCase(private val fcmRepository: FcmRepository): MainModel() {

    fun registerFcmToken(sendFeedbackLiveData: MutableLiveData<CommonState<APIResponse>>, registerParams: RegisterParams){


        getDisposable()!!.add(
            fcmRepository.callRegisterFcmTokenAPI(registerParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {sendFeedbackLiveData.value= CommonState.LoadingFinished }
                .subscribe({ sendFeedbackLiveData.value= CommonState.Success(it) },
                    { sendFeedbackLiveData.value=CommonState.Error(it)
                        it.printStackTrace() })
        )
    }

}