package com.konkuk.nongboohae.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SingleLiveData<T>: MutableLiveData<T>() {
    private val livedata : LiveData<T> = this

    override fun getValue(): T? {
        return livedata.value
    }

}