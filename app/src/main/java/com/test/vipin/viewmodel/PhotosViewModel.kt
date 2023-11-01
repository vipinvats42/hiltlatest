package com.test.vipin.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.vipin.model.Photos
import com.test.vipin.model.repository.PhotosRepository
import com.test.vipin.utils.NetworkHelper
import com.test.vipin.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PhotosViewModel @Inject constructor(private val photosRepository: PhotosRepository, private val networkHelper: NetworkHelper) : ViewModel() {

    private val _photosMutableStateFlow : MutableStateFlow<Resource<Photos>?> = MutableStateFlow(null)
    val photosStateFlow : StateFlow<Resource<Photos>?>
        get() = _photosMutableStateFlow
    init {
        fetchPhotos()
    }

    //@Named("activity2")
    private fun fetchPhotos(){
        viewModelScope.launch {
            if(networkHelper.isNetworkConnected()) {
                try {
                        _photosMutableStateFlow.emit(
                            Resource.success(
                                photosRepository.getPhotos()?.body()
                            )
                        )

                }catch (e : Exception){
                    e.printStackTrace()
                }



            }
        }
    }
}