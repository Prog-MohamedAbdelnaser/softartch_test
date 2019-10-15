package com.softtech.fcmtask.features.home.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import com.google.firebase.iid.FirebaseInstanceId
import com.softtech.fcmtask.R
import com.softtech.fcmtask.entities.APIResponse
import com.softtech.fcmtask.features.common.CommonState
import com.softtech.fcmtask.features.common.showErrorSnackbar
import com.softtech.fcmtask.features.home.vm.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeActivity : AppCompatActivity()  {

    private val homeViewModel:HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            if (it.isSuccessful){
                Timber.i("onNewToken is ${it.result?.token}")
                it.result?.token?.let { it1 -> homeViewModel.sendFcmToken(it1) }
            }
        }
        initObservers()

    }

    private fun initObservers() {
        homeViewModel.liveData.observe(this, Observer {
            handleResponseState(it)
        })

    }

    private fun handleResponseState(state: CommonState<APIResponse>?) {
        when(state){
            is CommonState.Success->{
                tvId.text="Register Id is ${state.data.id}"
            }
            is CommonState.Error->{
                state.exception.message?.let { showErrorSnackbar(toolbar, it) }
            }
        }
    }

}
