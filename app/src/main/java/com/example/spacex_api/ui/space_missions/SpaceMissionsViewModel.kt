package com.example.spacex_api.ui.space_missions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacex_api.RxManager
import com.example.spacex_api.model.LaunchRequest
import com.example.spacex_api.ui.ItemLaunch
import kotlin.collections.ArrayList

class SpaceMissionsViewModel : ViewModel() {

    private val rx = RxManager {
        createSuccessLaunchesList(it)
    }

    private val _successLaunches = MutableLiveData<ArrayList<ItemLaunch>>()
    val successLaunches: LiveData<ArrayList<ItemLaunch>> = _successLaunches

    fun init() {
        rx.getLaunchesList()
    }

    private fun createSuccessLaunchesList(list: List<LaunchRequest>) {
        val itemList = ArrayList<ItemLaunch>()
        for(request in list) {
            if (request.success == true &&
                DateLaunch.isIncludedInTheAnnualRange(request.date.toLong(), 2015, 2020))
            itemList.add(
                ItemLaunch(request.name,
                    request.date.toLong(),
                    DateLaunch.format(request.date.toLong()),
                    request.details,
                    request.links.patch.url_img)
            )
        }
        _successLaunches.value = itemList
    }

    override fun onCleared() {
        super.onCleared()
        rx.detach()
    }

}