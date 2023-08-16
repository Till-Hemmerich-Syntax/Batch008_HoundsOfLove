package com.syntax.hemmerich.batch008_houndsoflove.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syntax.hemmerich.batch008_houndsoflove.data.AppRepository
import com.syntax.hemmerich.batch008_houndsoflove.data.remote.DogApi
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ApiStatus{LOADING,ERROR,DONE}
const val TAG = "MainViewModel"
class MainViewModel : ViewModel() {

    private val repository = AppRepository(DogApi)

    private val _loading = MutableLiveData<ApiStatus>()
    val loading : LiveData<ApiStatus>
        get() = _loading

    val imageList = repository.imageList

    init {
        loadImages()
    }
    fun loadImages(){
        viewModelScope.launch {
            _loading.value = ApiStatus.LOADING
            try {
                repository.getImages()
                _loading.value = ApiStatus.DONE
            }catch (e : Exception){
                Log.e(TAG,"ERROR LOADIN DATA : $e")
                _loading.value = ApiStatus.ERROR
            }
        }
    }

}