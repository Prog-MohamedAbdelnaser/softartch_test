package com.softtech.fcmtask.application

import io.reactivex.disposables.CompositeDisposable
abstract class MainModel {
    private val disposable: CompositeDisposable = CompositeDisposable()
    fun getDisposable(): CompositeDisposable? {
           return disposable
    }

    fun clear() {
        disposable.clear()
    }



}