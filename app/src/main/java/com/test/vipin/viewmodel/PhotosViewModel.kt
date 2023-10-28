package com.test.vipin.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotosViewModel @Inject constructor() : ViewModel() {

    init {
        fetchPhotos()
    }

    private fun fetchPhotos(){
        CoroutineScope(Dispatchers.IO).launch {

        }
    }
}