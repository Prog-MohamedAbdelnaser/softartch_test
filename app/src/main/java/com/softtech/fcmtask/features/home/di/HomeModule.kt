package com.softtech.fcmtask.features.home.di

import com.softtech.fcmtask.data.repositories.FcmRepository
import com.softtech.fcmtask.data.sources.remote.apis.FcmAPI
import com.softtech.fcmtask.domain.usecases.RegisterFcmUseCase
import com.softtech.fcmtask.features.home.vm.HomeViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val homeModule= module {
    factory {  get<Retrofit>().create(FcmAPI::class.java) }
    factory {  FcmRepository(get()) }
    factory {  RegisterFcmUseCase(get()) }
    viewModel { HomeViewModel(get()) }
}