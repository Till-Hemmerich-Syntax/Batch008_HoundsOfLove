package com.syntax.hemmerich.batch008_houndsoflove.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.syntax.hemmerich.batch008_houndsoflove.data.remote.DogApi
import kotlinx.coroutines.delay


class AppRepository(private val api: DogApi) {
    private val _imageList = MutableLiveData<List<String>>()
    val imageList: LiveData<List<String>>
        get() = _imageList


    suspend fun getImages(){
        delay(2000)
        _imageList.value = api.retrofitService.getImages().message
    }

}