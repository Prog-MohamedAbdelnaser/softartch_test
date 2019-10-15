package com.softtech.fcmtask.di

import com.softtech.fcmtask.data.sources.resources.AppResources
import com.softtech.fcmtask.data.repositories.StringsRepository
import org.koin.dsl.module.module

val applicationModule = module {

    single { AppResources(get()) }

    single { StringsRepository(get()) }


}