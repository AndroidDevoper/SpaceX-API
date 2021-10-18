package com.example.spacex_api

import com.example.spacex_api.model.LaunchRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RxManager(private val callback: (List<LaunchRequest>) -> Unit) {

    private val compositeDisposable = CompositeDisposable()

    fun getLaunchesList() {
        compositeDisposable.add(
            App.API.getLaunches()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.invoke(it)
                }, {

                })
        )
    }

    fun detach() {
        compositeDisposable.clear()
    }
}