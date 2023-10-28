package com.test.vipin.viewmodel

import androidx.lifecycle.ViewModel
import com.test.vipin.model.Photos
import com.test.vipin.model.repository.MainRepository
import com.test.vipin.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class PhotosViewModel @Inject constructor(private val mainRepository: MainRepository, private val networkHelper: NetworkHelper) : ViewModel() {

    private val _photosMutableStateFlow = MutableStateFlow<List<Photos>>(listOf())
    val photosStateFlow : StateFlow<List<Photos>>
        get() = _photosMutableStateFlow
    init {
        fetchPhotos()
    }

    //@Named("activity2")
    private fun fetchPhotos(){
        CoroutineScope(Dispatchers.IO).launch {
            if(networkHelper.isNetworkConnected()) {
                mainRepository.getPhotos().let {
                    if(it.isSuccessful){
                        _photosMutableStateFlow.value = it.body()!!
                    }
                }
            }
        }
    }
}